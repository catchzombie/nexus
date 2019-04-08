package com.catchzombie.api.controllers.user;

import com.catchzombie.CzSecurityManager;
import com.catchzombie.models.User;
import com.catchzombie.services.user.IUserService;
import com.catchzombie.user.LoginRequestDto;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author shubham
 */

@RestController
@RequestMapping("/v1/serviceprovider")
public class ServiceProvider {

}
