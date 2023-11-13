package di.step2;

public class Main {
    public static void main(String[] args) {
        // step2에서는 직접생성 대신에 객체를 외부에서 주자.
        // boardService를 만들 때 외부에서 boardDao를 주자.
        // 외부에서 주는 방법은 생성자 or setter

        // 외부에서 의존하는 객체를 주는 거를 주입이라고 합니다. (의존 주입)

        // 요구사항 : Oracle에서 mysql로 변경하세요...
        // 외부에서 boardDao를 생성하는 곳(Main)만 바꾸면 되네...
        IBoardDao boardDao = new BoardDaoOracle();
        IBoardService boardService1 = new BoardServiceImpl1(boardDao);
        IBoardService boardService2 = new BoardServiceImpl2(boardDao);
        IBoardService boardService3 = new BoardServiceImpl3(boardDao);

        boardService1.getBoard();
        boardService2.getBoard();
        boardService3.getBoard();
    }
}
