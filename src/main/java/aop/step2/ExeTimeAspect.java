package aop.step2;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.Arrays;

public class ExeTimeAspect {    // 부가기능을 담당하는 클래스, 빈으로 등록해야됨
    public Object measure(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.nanoTime();
        try {
            Object proceed = joinPoint.proceed();
            return proceed;
        } finally {
            long endTime = System.nanoTime();
            System.out.println("실행시간 : " + (endTime - startTime));
            MethodSignature sig = (MethodSignature) joinPoint.getSignature();
            System.out.println("주요기능 메소드 이름 : " + sig.getName());
            System.out.println("주요기능 메소드 파라미터 : " + Arrays.toString(sig.getParameterNames()));
            System.out.println("주요기능 메소드 리턴타입 : " + sig.getReturnType().getSimpleName());
        }
    }
}
