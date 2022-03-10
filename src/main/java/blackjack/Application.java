package blackjack;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        BlackjackGame blackjackGame = new BlackjackGame(InputView.inputNames());
        List<Player> players = blackjackGame.getPlayers();
        OutputView.printPlayerCards(players);
    }
}
