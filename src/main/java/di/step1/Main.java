package di.step1;

public class Main {
    public static void main(String[] args) {
        // BoardService는 BoardDao 객체의 메소드를 사용합니다.
        // 이거를 boardService가 boardDao에 의존한다고 합니다.
        // boardService는 의존하는 객체를 직접 생성하고 있습니다.

        // 요구사항 : oracle 대신에 mysql을 사용하기로 결정이 났습니다.
        // ==> 3군데에서 사용하고 있는데... 3군데 수정해야 되네...

        IBoardService boardService1 = new BoardServiceImpl1();
        IBoardService boardService2 = new BoardServiceImpl2();
        IBoardService boardService3 = new BoardServiceImpl3();

        boardService1.getBoard();
        boardService2.getBoard();
        boardService3.getBoard();
    }
}
