/**
 *
 */
package com.emisha.admin.web.controllers.api;

import com.emisha.admin.security.JWTUtil;
import com.emisha.admin.web.utils.WebUtils;
import com.emisha.database.services.UserService;
import com.emisha.entities.User;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;

/**
 * @author Ankush
 */
public abstract class EmishaAdminBaseControllerApi {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected MessageSource messageSource;

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    public User getCurrentUser() {

        Object principal = SecurityUtils.getSubject().getPrincipal();
        String username = JWTUtil.getUsername((String) principal);
        User user = userService.getUserbyUsername(username);
        // User user = WebUtils.getUser();
        return user;
    }

    public User getCurrentUserForAPI() {

        User user = WebUtils.getUser();
        return user;
    }

    public boolean isLoggedIn() {
        return getCurrentUser() != null;
    }

    public String getMessage(String code) {
        return messageSource.getMessage(code, null, null);
    }

    public String getMessage(String code, String defaultMsg) {
        return messageSource.getMessage(code, null, defaultMsg, null);
    }
}
