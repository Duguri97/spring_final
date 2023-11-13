package di.step4;

import di.step3.Assembler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        // spring은 Assembler 처럼 객체를 관리합니다.
        // 근데 그거를 xml이나, @을 통해서 설정합니다. 대전, 세종은 xml
        ApplicationContext context = new GenericXmlApplicationContext("di/step4.xml");

        IBoardService boardService1 = context.getBean(BoardServiceImpl1.class);
        IBoardService boardService2 = context.getBean(BoardServiceImpl2.class);
        IBoardService boardService3 = context.getBean(BoardServiceImpl3.class);

        boardService1.getBoard();
        boardService2.getBoard();
        boardService3.getBoard();


    }
}
