package ru.strebkov.t1_SpringAopExample.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.strebkov.t1_SpringAopExample.annotation.PreInvoke;
import ru.strebkov.t1_SpringAopExample.exception.PlantException;
import ru.strebkov.t1_SpringAopExample.model.RoleType;
import ru.strebkov.t1_SpringAopExample.utils.UserContext;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
@Aspect
//@Order(1) // для установления приоритета 1 выше чем 2,3 ...явно контрол. поряд. выполнения аспектов
public class PreInvokeCheckerAspect { // PlantService
    private static final Map<String, List<RoleType>> USERS = new HashMap<>();

    static {
        USERS.put("admin", List.of(RoleType.ADMIN, RoleType.USER));
        USERS.put("user", List.of(RoleType.USER));
    }

    @Pointcut("@annotation(preInvoke)")
    public void checkRulePointcut(PreInvoke preInvoke) {
    }

    @Before(value = "checkRulePointcut(preInvoke)", argNames = "preInvoke")
    public void before(PreInvoke preInvoke) {
        String user = UserContext.getUsername();

        if (!USERS.containsKey(user)) {
            throw new PlantException("Пользователь не найден " + user);
        }
        var roles = Arrays.stream(preInvoke.roles()).toList();
        var userRoles = USERS.get(user);

        if (roles.stream().noneMatch(userRoles::contains)) {
            throw new PlantException("Доступ запрещён! Роли: " + userRoles);
        }
    }
}
