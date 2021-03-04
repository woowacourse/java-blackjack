package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class GameTable {
    private static final String YES = "Y";

    private final Deck deck;
    private final Dealer dealer;
    private final List<Player> players;

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
        players.forEach(this::askHit);
        while (dealer.canHit()) {
            dealer.hit(deck.pop());
            OutputView.printDealerHitMessage();
        }
    }

    private void finish() {
        OutputView.printCardsAndScore(dealer, players);
        Result result = new Result();
        List<Integer> matchResult = dealer.calculateMatchResult(result.getResult(dealer, players));
        OutputView.printResult(matchResult, result.getResult(dealer, players));
    }

    private void askHit(Player player) {
        while (player.isNotBust() && wantCard(player)) {
            player.hit(deck.pop());
            OutputView.printPlayerCards(player);
        }
    }

    private boolean wantCard(Player player) {
        OutputView.printHitGuideMessage(player);
        String hitValue = InputView.getHitValue();
        return hitValue.equals(YES);
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
