package di.step4;

import org.springframework.stereotype.Component;


public class BoardDaoOracle implements IBoardDao {

    public void getBoard() {
        System.out.println("dao. DB에서 getBoard했다고 가정합시다.");
    }
}
