package di.step2;

public class BoardServiceImpl3 implements IBoardService {
    IBoardDao boardDao;
    public BoardServiceImpl3(IBoardDao boardDao) {
        this.boardDao = boardDao;
    }

    public void getBoard() {
        boardDao.getBoard();
    }
}
