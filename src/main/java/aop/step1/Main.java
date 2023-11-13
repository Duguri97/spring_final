package aop.step1;

public class Main {
    public static void main(String[] args) {
        ForCalculator forCalculator = new ForCalculator();
        ExeTimeCalculator exeTimeCalculator = new ExeTimeCalculator(forCalculator);
        exeTimeCalculator.factorial(5); // exe 내부에서 for가 대신 주요기능 수행

        RecCalculator recCalculator = new RecCalculator();
        ExeTimeCalculator exeTimeCalculator2 = new ExeTimeCalculator(recCalculator);
        exeTimeCalculator.factorial(5); // exe 내부에서 for가 대신 주요기능 수행

    }
}
