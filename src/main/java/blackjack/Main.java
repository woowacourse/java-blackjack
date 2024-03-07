package blackjack;

import blackjack.domain.CardFactory;
import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Game;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<String> playerNames = InputView.readName();
        CardFactory cardFactory = new CardFactory();
        Game game = new Game(new Dealer(new Deck(cardFactory.createBlackJackCard())), Players.convertTo(playerNames));

        game.initializeHand();

        List<Player> players = game.getPlayers();
        Dealer dealer = game.getDealer();
        OutputView.printInitialHand(dealer, players);

        for (Player player : players) {
            while (player.canHit() && InputView.readHitOrStand(player)) {
                player.putCard(dealer.draw());
                if (player.canHit()) {
                    OutputView.printTotalHand(player);
                } else {
                    // TODO: 터졌을 떄 안내
                }
            }
            OutputView.printTotalHand(player);
        }
    }

}
