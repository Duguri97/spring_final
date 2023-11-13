package aop.step2;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("aop/step2.xml");
        Calculator forCal = ctx.getBean( ForCalculator.class);
        Calculator recCal = ctx.getBean( RecCalculator.class);

        forCal.factorial(5);
        recCal.factorial(5);
    }
}
