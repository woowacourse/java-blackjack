package blackjack.domain.game;

import blackjack.domain.card.Deck;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;

public class BlackJackGame {

    private final Deck deck;
    private final Dealer dealer;
    private final Players gamblers;

    public BlackJackGame(final Players gamblers) {
        deck = new Deck();
        dealer = new Dealer();
        this.gamblers = gamblers;
    }

    public void initDealerCards() {
        dealer.initializeCards(deck);
    }

    public void initPlayerCards() {
        gamblers.initPlayerCards(deck);
    }

    public void giveGamblerCard(Player gambler) {
        gamblers.players().stream()
                .filter(player -> player == gambler)
                .forEach(player -> player.drawCard(deck));
    }

    public boolean ableToDraw() {
        return dealer.ableToDraw();
    }

    public void giveDealerCard() {
        dealer.drawCard(deck);
    }

    public Result calculateResult() {
        Result result = new Result(dealer);
        for (Player player : gamblers.players()) {
            addGamblerResult(result, player);
        }
        return result;
    }

    private void addGamblerResult(final Result result, final Player player) {
        WinOrLose winOrLose = dealer.calculateGamblerWinOrNot(player);
        result.add(player, winOrLose);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getGamblers() {
        return gamblers;
    }
}
