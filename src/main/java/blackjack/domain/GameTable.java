package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class GameTable {

    private static final String HIT_CONTINUE = "Y";

    private final Dealer dealer;
    private final List<Player> players;

    public GameTable(List<Player> players) {
        this.dealer = new Dealer();
        this.players = players;
    }

    public void playGame() {
        Deck deck = new Deck(Card.values());
        start(deck);
        play(deck);
        finish();
    }

    private void start(Deck deck) {
        drawAtFirst(deck);
        OutputView.printShowUsersCardMessage(players);
        OutputView.showCards(dealer, players);
    }

    private void play(Deck deck) {
        players.forEach(player -> askHit(player,deck));
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

    private void askHit(Player player,Deck deck) {
        while (player.isNotBust() && wantCard(player)) {
            player.hit(deck.pop());
            OutputView.printUserCards(player);
        }
    }

    private boolean wantCard(Player player) {
        OutputView.printHitGuideMessage(player);
        String hitValue = InputView.getHitValue();
        return hitValue.equals(HIT_CONTINUE);
    }

    private void drawAtFirst(Deck deck) {
        players.forEach(player -> {
            player.hit(deck.pop());
            player.hit(deck.pop());
        });
        dealer.hit(deck.pop());
        dealer.hit(deck.pop());
    }
}
