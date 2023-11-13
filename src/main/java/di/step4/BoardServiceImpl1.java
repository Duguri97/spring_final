package di.step4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BoardServiceImpl1 implements IBoardService {
    IBoardDao boardDao;

    @Autowired
    public BoardServiceImpl1(IBoardDao boardDao) {
        this.boardDao = boardDao;
    }

    public void getBoard() {
        boardDao.getBoard();
    }
}
