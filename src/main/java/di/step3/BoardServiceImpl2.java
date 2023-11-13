package di.step3;

public class BoardServiceImpl2 implements IBoardService {
    IBoardDao boardDao;
    public BoardServiceImpl2(IBoardDao boardDao) {
        this.boardDao = boardDao;
    }

    public void getBoard() {
        boardDao.getBoard();
    }
}
