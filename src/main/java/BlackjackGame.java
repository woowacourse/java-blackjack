import domain.Dealer;
import domain.GameState;
import domain.Hand;
import domain.Name;
import domain.Player;
import domain.Players;
import domain.card.Card;
import domain.card.Deck;
import java.util.List;
import view.InputView;

public class BlackjackGame {

    public void run() {
        List<String> playerNames = InputView.readPlayerNames();

        Deck deck = new Deck();
        Players players = new Players();
        for (String playerName : playerNames) {
            Player player = new Player(new Name(playerName), new Hand());
            players.add(player);
            for (int i = 0; i < 2; i++) {
                Card card = deck.drawCard();
                player.receiveCard(card);
            }
        }

        Dealer dealer = new Dealer(new Hand());
        for (int i = 0; i < 2; i++) {
            Card card = deck.drawCard();
            dealer.receiveCard(card);
        }

        for (Player player : players.getPlayers()) {
            while (player.getGameState() == GameState.HIT) {
                String gameCommand = InputView.readHitOrStand(player.getName()); // y or n
                // 랜덤 카드 부여
                // 게임 상태 판단
            }
        }

    }
}
