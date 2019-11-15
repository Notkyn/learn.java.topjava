package ru.javawebinar.topjava.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.util.exception.ErrorInfo;
import ru.javawebinar.topjava.util.exception.ErrorType;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class ExceptionUtil {
    private static Logger log = LoggerFactory.getLogger(ExceptionUtil.class);

    //    https://stackoverflow.com/questions/538870/should-private-helper-methods-be-static-if-they-can-be-static
    public static ErrorInfo logAndGetErrorInfo(HttpServletRequest req, Exception e, boolean logException, ErrorType errorType, String... details) {
        Throwable rootCause = ValidationUtil.getRootCause(e);

        String msgError = isExeption(rootCause, SQLException.class)
                ? getCorrectMessageError(req) : rootCause.toString();
        if (logException) {
            log.error(errorType + " at request " + req.getRequestURL(), rootCause);
        } else {
            log.warn("{} at request  {}: {}", errorType, req.getRequestURL(), rootCause.toString());
        }
        return new ErrorInfo(req.getRequestURL(), errorType, msgError, details);
    }

    private static String getCorrectMessageError(HttpServletRequest req) {
        if(req.getServletPath().contains("meal")){
            return "Meal with this date already exists";
        }
        return "User with this email already exists";
    }

    public static boolean isExeption(Throwable actual, Class<? extends Throwable> expected){
        return actual.getClass().equals(expected)
                || actual.getClass().getSuperclass().equals(expected);
    }
}
