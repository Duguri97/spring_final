package aop.step2;

public class ForCalculator implements Calculator {
    @Override
    public long factorial(long num) {
        System.out.println();   // System클래스에 out이 public static 이겠죠.
        long result = 1;
        for (int i = 1; i <= num; i++) {
            result *=i;
        }
        return result;
    }
}
