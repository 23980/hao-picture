package com.czh.haopicturebackend.aop;

import com.czh.haopicturebackend.annotation.AuthCheck;
import com.czh.haopicturebackend.enums.UserRoleEnum;
import com.czh.haopicturebackend.exception.BusinessException;
import com.czh.haopicturebackend.exception.ErrorCode;
import com.czh.haopicturebackend.model.entity.User;
import com.czh.haopicturebackend.service.UserService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class AuthInterceptor {

    @Resource
    private UserService userService;

     /** 拦截器
     * 该方法会拦截被 @AuthCheck 注解标记的方法，并进行权限检查。
     * 只有拥有指定角色的用户才能执行被拦截的方法。
     *
     * @param joinPoint 连接点，表示被拦截的方法
     * @param authCheck 注解对象，包含了权限检查的相关信息
     * @return 被拦截方法的执行结果
     * @throws Throwable 如果发生异常，会抛出
     */
    @Around("@annotation(authCheck)")
    public Object doInterceptor(ProceedingJoinPoint joinPoint, AuthCheck authCheck) throws Throwable {
        // 从注解中获取必须的角色
        String mustRole = authCheck.mustRole();
        // 获取当前请求的属性
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        // 从请求属性中获取 HttpServletRequest 对象
        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
        // 根据请求获取登录用户信息
        User loginUser = userService.getLoginUser(request);
        // 将 mustRole 转换为 UserRoleEnum 枚举类型
        UserRoleEnum mustRoleEnum = UserRoleEnum.getEnumByValue(mustRole);
        // 如果 mustRoleEnum 为 null，表示没有指定必须的角色，直接执行目标方法
        if (mustRoleEnum == null){
            return joinPoint.proceed();
        }
        // 将登录用户的角色转换为 UserRoleEnum 枚举类型
        UserRoleEnum userRoleEnum = UserRoleEnum.getEnumByValue(loginUser.getUserRole());
        // 如果 userRoleEnum 为 null，表示登录用户没有角色，抛出没有权限的异常
        if (userRoleEnum == null){
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        // 如果必须的角色是 ADMIN，而登录用户的角色不是 ADMIN，抛出没有权限的异常
        if (UserRoleEnum.ADMIN.equals(mustRoleEnum) &&!UserRoleEnum.ADMIN.equals(userRoleEnum)){
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        // 如果通过了所有的权限检查，执行目标方法，并返回其执行结果
        return joinPoint.proceed();
    }
}
