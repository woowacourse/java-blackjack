import controller.BlackjackFrontController;
import java.util.List;

public class BlackjackApplication {

    private final BlackjackFrontController blackjackFrontController;

    public BlackjackApplication(BlackjackFrontController blackjackFrontController) {
        this.blackjackFrontController = blackjackFrontController;
    }

    public void run() {
        blackjackFrontController.startBetting();
        blackjackFrontController.initialDeal();
        hitPlayers();
        blackjackFrontController.drawDealer();
        blackjackFrontController.showCardsResult();
        blackjackFrontController.showAllProfit();
    }

    private void hitPlayers() {
        List<String> playerNames = blackjackFrontController.findAllPlayerNames();
        for (String playerName : playerNames) {
            hitPlayer(playerName);
        }
    }

    private void hitPlayer(String playerName) {
        boolean isHitDecided = true;
        while (isHitDecided) {
            try {
                isHitDecided = blackjackFrontController.requestHitDecision(playerName);
                blackjackFrontController.hitPlayer(playerName, isHitDecided);
            } catch (IllegalStateException e) {
                isHitDecided = false;
            }
        }
    }
}
