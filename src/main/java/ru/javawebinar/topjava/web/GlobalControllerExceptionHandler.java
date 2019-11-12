package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.to.UserTo;
import ru.javawebinar.topjava.util.ExceptionUtil;
import ru.javawebinar.topjava.util.ValidationUtil;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

    private ReloadableResourceBundleMessageSource res;

    @Autowired
    public void setRes(ReloadableResourceBundleMessageSource res) {
        this.res = res;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        log.error("Exception at request " + req.getRequestURL(), e);
        Throwable rootCause = ValidationUtil.getRootCause(e);

        if(ExceptionUtil.isExeption(rootCause, SQLException.class)){
            return responseForDuplicateEmail(req);
        }

        ModelAndView mav = new ModelAndView("exception/exception");
        mav.addObject("exception", rootCause);
        mav.addObject("message", rootCause.toString());

        // Interceptor is not invoked, put userTo
        AuthorizedUser authorizedUser = SecurityUtil.safeGet();
        if (authorizedUser != null) {
            mav.addObject("userTo", authorizedUser.getUserTo());
        }
        return mav;
    }

    private ModelAndView responseForDuplicateEmail(HttpServletRequest req){
        UserTo userTo = new UserTo();
        userTo.setName(req.getParameter("name"));
        userTo.setEmail(req.getParameter("email"));
        userTo.setPassword(req.getParameter("password"));
        userTo.setCaloriesPerDay(Integer.parseInt(req.getParameter("caloriesPerDay")));
        ModelAndView model = new ModelAndView("profile");
        model.addObject("emailError", "true");
        model.addObject("emailErrorMessage", res.getMessage("app.duplicateEmail", null, Locale.getDefault()));
        model.addObject("validateEmailError", "email");
        model.addObject("userTo", userTo);
        model.addObject("register", true);
        return model;
    }
}
