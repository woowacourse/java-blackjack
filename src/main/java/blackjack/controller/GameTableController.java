package blackjack.controller;

import blackjack.domain.Result;
import blackjack.domain.card.Deck;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class GameTableController {

    private static final String HIT_CONTINUE = "Y";

    private final Dealer dealer;
    private final Players players;

    public GameTableController(Players players) {
        this.dealer = new Dealer();
        this.players = players;
    }

    public void playGame() {
        Deck deck = new Deck();
        start(deck);
        play(deck);
        finish();
    }

    private void start(Deck deck) {
        drawAtFirst(deck);
        OutputView.printShowUsersCardMessage(players);
        OutputView.showUsersCards(dealer, players);
    }

    private void play(Deck deck) {
        tryHit(deck);
        while (dealer.canHit()) {
            dealer.hit(deck.pop());
            OutputView.printDealerHitMessage();
        }
    }

    private void finish() {
        OutputView.printCardsAndScore(dealer, players);
        Result result = new Result(dealer, players);
        List<Integer> matchResult = dealer.calculateMatchResult(result.getResult());
        OutputView.printResult(matchResult, result.getResult());
    }

    public void tryHit(Deck deck) {
        players.getPlayers()
            .forEach(player -> askHit(player, deck));
    }

    private void askHit(Player player, Deck deck) {
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
        players.drawAtFirst(deck);
        dealer.hitTwoCards(deck);
    }
}
