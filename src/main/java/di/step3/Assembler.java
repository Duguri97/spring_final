package di.step3;

public class Assembler {
    private IBoardService boardService1;
    private IBoardService boardService2;
    private IBoardService boardService3;

    private  IBoardDao boardDao;

    public Assembler() {
        boardDao = new BoardDaoOracle();
        boardService1 = new BoardServiceImpl1(boardDao);
        boardService2 = new BoardServiceImpl2(boardDao);
        boardService3 = new BoardServiceImpl3(boardDao);
    }

    public IBoardService getBoardService1() {
        return boardService1;
    }

    public IBoardService getBoardService2() {
        return boardService2;
    }

    public IBoardService getBoardService3() {
        return boardService3;
    }

    public IBoardDao getBoardDao() {
        return boardDao;
    }
}
