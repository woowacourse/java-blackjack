import domain.Dealer;
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

        System.out.println();
    }
}
