package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class GameTable {

    private final Deck deck;
    private final Dealer dealer;
    private List<Player> players;

    public GameTable(List<Player> players) {
        deck = new Deck(Card.values());
        this.dealer = new Dealer();
        this.players = players;
    }

    public void playGame() {
        start();
        play();
        finish();
    }

    private void start() {
        drawAtFirst();
        OutputView.printShowUsersCardMessage(players);
        OutputView.showCards(dealer, players);
    }

    private void play() {
        players.forEach(player -> {
            while (player.isNotBust()) {
                askHit(player);
            }
        });

        while (dealer.canHit()) {
            dealer.hit(deck.pop());
            OutputView.printDealerHitMessage();
        }
    }

    private void finish() {
        OutputView.printCardsAndScore(dealer, players);
        Result result = new Result();
        OutputView.printResult(result.getResult(dealer, players));
    }

    private void askHit(Player player) {
        OutputView.printHitGuideMessage(player);
        String hitValue = InputView.getHitValue();
        if (hitValue.equals("Y")) {
            player.hit(deck.pop());
        }
    }

    private void drawAtFirst() {
        players.forEach(player -> {
            player.hit(deck.pop());
            player.hit(deck.pop());
        });
        dealer.hit(deck.pop());
        dealer.hit(deck.pop());
    }
}
