import model.BlackjackGame;

public class Application {

    public static void main(String[] args) {
        BlackjackGame gameManager = BlackjackGame.create();
        gameManager.start();
    }
}
