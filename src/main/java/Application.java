import controller.BlackJackGame;

public class Application {
    public static void main(String[] args) {
        BlackJackGame blackJackGame = new BlackJackGame();
        try {
            blackJackGame.play();
        } catch (RuntimeException e) {
            System.out.println(String.format("다음과 같은 이유로 종료합니다 : %s", e.getMessage()));
            System.exit(-1);
        }

    }
}