package di.step3;

public class Main2 {
    public static void main(String[] args) {
        // 주입하는 객체를 직접 생성하지말고 대신 생성해주는 클래스가 있으면 좋겠다.
        // ==> Assembler
        // Assembler는 생길 때 여러 객체들을 가지고 있고, 관리합니다.

        // boardService를 사용하는 곳이 여러군데 있어도
        // oracle -> mysql의 요구사항에 대해 1군데만 바꾸면 됩니다.
        Assembler assembler = new Assembler();
        IBoardService boardService1 = assembler.getBoardService1();
        IBoardService boardService2 = assembler.getBoardService2();
        IBoardService boardService3 = assembler.getBoardService3();

        boardService1.getBoard();
        boardService2.getBoard();
        boardService3.getBoard();

    }
}
