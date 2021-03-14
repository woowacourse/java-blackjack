package blackjack.domain.game;

import blackjack.domain.card.Deck;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Gamblers;

public class BlackJackGame {

    private final Deck deck;
    private final Dealer dealer;
    private final Gamblers gamblers;

    public BlackJackGame() {
        deck = new Deck();
        dealer = new Dealer();
        this.gamblers = new Gamblers();
    }

    public BlackJackGame(final Gamblers gamblers) {
        deck = new Deck();
        dealer = new Dealer();
        this.gamblers = gamblers;
    }

    public void addGamblers(final Gamblers gamblers) {
        this.gamblers.addAll(gamblers);
    }

    public void initDealerCards() {
        dealer.initializeCards(deck);
    }

    public void initGamblersCards() {
        gamblers.initGamblerCards(deck);
    }

    public void giveGamblerCard(final Gambler gambler) {
        gamblers.getGamblerByName(gambler)
                .drawCard(deck);
    }

    public boolean ableToDraw() {
        return dealer.ableToDraw();
    }

    public void giveDealerCard() {
        dealer.drawCard(deck);
    }

    public Result calculateResult() {
        Result result = new Result(dealer);
        gamblers.addGamblerResult(result, dealer);
        return result;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Gamblers Gamblers() {
        return gamblers;
    }
}
