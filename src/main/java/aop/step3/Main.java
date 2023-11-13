package aop.step3;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("aop/step3.xml");
        Calculator forCal = ctx.getBean(ForCalculator.class);
        Calculator recCal = ctx.getBean(RecCalculator.class);


        forCal.factorial(300);
        recCal.factorial(300);
    }
}
