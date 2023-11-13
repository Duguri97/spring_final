package di.step4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BoardServiceImpl3 implements IBoardService {
    IBoardDao boardDao;
    @Autowired
    public BoardServiceImpl3(IBoardDao boardDao) {
        this.boardDao = boardDao;
    }

    public void getBoard() {
        boardDao.getBoard();
    }
}
