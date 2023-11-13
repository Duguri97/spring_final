package aop.step3;

public class RecCalculator implements Calculator {

    // 재귀함수로 만들어 factorial을
    @Override
    public long factorial(long num) {   // 0보다 작으면 if문 해가지고 에러내야되지만...
        if (num == 1) return 1;
        return num * factorial(num - 1);
    }
}
