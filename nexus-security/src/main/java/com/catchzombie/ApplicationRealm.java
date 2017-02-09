package com.catchzombie;

import com.catchzombie.models.Permission;
import com.catchzombie.models.Role;
import com.catchzombie.models.User;
import com.catchzombie.repositories.UserRepository;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ashsish on 6/2/17.
 */
@Component
public class ApplicationRealm extends AuthorizingRealm{

    @Autowired
    UserRepository userRepository;

    /**
     * List of roles to shiro of user
     *
     * @param principals This will contain email as we have set same
     * @return Instance of SimpleAuthorizationInfo containing roles
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        String email = (String) principals.getPrimaryPrincipal();
        User user = userRepository.findByEmail(email);
        if(user==null){
            throw new UnknownAccountException("Account doest not exist");
        }
        int totalRoles = user.getRoles().size();
        Set<String> roleNames = new LinkedHashSet<>(totalRoles);
        Set<String> permissionNames = new LinkedHashSet<>();
        if(totalRoles>0){
            List<Role> roles = user.getRoles();
            for(Role role : roles){
                roleNames.add(role.getRoleName());
                List<Permission> permissions = role.getPermissions();
                for (Permission permission: permissions){
                    permissionNames.add(permission.getTitle());
                }
            }
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo(roleNames);
        authorizationInfo.setStringPermissions(permissionNames);
        return authorizationInfo;
    }



    /**
     * This method will be called by Shiro when currentUser.login(token) is called
     *
     * @param token This will contain the username and password
     * @return Instance of SimpleAuthenticationInfo containing principal and credentials
     * @throws AuthenticationException on login failure
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken userNamePassword = (UsernamePasswordToken) token;
        String email = userNamePassword.getUsername();
        if(email==null){
            throw new AuthenticationException("Email Not Provided");
        }
        User user = userRepository.findByEmail(email);
        if(user==null){
            throw new AuthenticationException("No User found, cannot login");
        }
        return new SimpleAuthenticationInfo(email,user.getPassword().toCharArray(), ByteSource.Util.bytes(email),getName());
    }
}
