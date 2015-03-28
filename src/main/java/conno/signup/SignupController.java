package conno.signup;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import conno.account.*;
import conno.support.web.*;

@Controller
public class SignupController {

    private static final String SIGNUP_VIEW_NAME = "signup/signup";

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "signup")
    public String signup(Model model) {
        model.addAttribute(new SignupForm());
        return SIGNUP_VIEW_NAME;
    }

    @RequestMapping(value = "signup", method = RequestMethod.POST)
    public String signup(@Valid @ModelAttribute SignupForm signupForm, Errors errors, RedirectAttributes ra) {
        if (errors.hasErrors()) {
            return SIGNUP_VIEW_NAME;
        }
        //проверка, совпадают ли пароли
        if (!signupForm.passwordCheck()) {
            errors.rejectValue("retypePassword", "password.message");
            return SIGNUP_VIEW_NAME;
        }

        //пользователь с таким емайл уже существует и переход на стр

        Account account = accountRepository.findByEmail(signupForm.getEmail());
        if (account != null) {
            errors.rejectValue("email", "exist.email");
            return SIGNUP_VIEW_NAME;
        }

        account = accountRepository.save(signupForm.createAccount());
        userService.signin(account);
        // see /WEB-INF/i18n/messages.properties and /WEB-INF/views/login.html

        MessageHelper.addSuccessAttribute(ra, "signup.success");
        return "redirect:/login";
    }


}
