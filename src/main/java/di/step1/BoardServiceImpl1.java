package di.step1;

public class BoardServiceImpl1 implements IBoardService {
    IBoardDao boardDao = new BoardDaoOracle();

    public void getBoard() {
        boardDao.getBoard();
    }
}
