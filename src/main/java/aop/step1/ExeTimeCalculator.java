package aop.step1;

public class ExeTimeCalculator implements Calculator{
    // 가짜객체 ExeTimeCalculator
    // 대리자(delegate, 주요기능)를 대신 실행만 시켜주는 클래스


    private Calculator delegate;
    public ExeTimeCalculator(Calculator delegate) {
        this.delegate = delegate;
    }

    @Override
    public long factorial(long num) {
        long startTime = System.nanoTime(); // 부가기능
        long result = delegate.factorial(num);
        long endTime = System.nanoTime();   // 부가기능
        System.out.println(endTime - startTime);
        return result;
    }
}
