package di.step3;

public class BoardServiceImpl1 implements IBoardService {
    IBoardDao boardDao;
    public BoardServiceImpl1(IBoardDao boardDao) {
        this.boardDao = boardDao;
    }

    public void getBoard() {
        boardDao.getBoard();
    }
}
