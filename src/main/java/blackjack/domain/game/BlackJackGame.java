package blackjack.domain.game;

import blackjack.domain.card.Deck;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Gamblers;

public class BlackJackGame {

    private final Deck deck;
    private final Dealer dealer;
    private final Gamblers gamblers;

    public BlackJackGame(final Gamblers gamblers) {
        deck = new Deck();
        dealer = new Dealer();
        this.gamblers = gamblers;
    }

    public void initDealerCards() {
        dealer.initializeCards(deck);
    }

    public void initGamblersCards() {
        gamblers.initGamblerCards(deck);
    }

    public void giveGamblerCard(Gambler gambler) {
        gamblers.gamblers().stream()
                .filter(player -> player.isSameName(gambler))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 이름의 겜블러가 없습니다."))
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
        for (Gambler gambler : gamblers.gamblers()) {
            addGamblerResult(result, gambler);
        }
        return result;
    }

    private void addGamblerResult(final Result result, final Gambler gambler) {
        WinOrLose winOrLose = WinOrLose.calculateGamblerWinOrNot(dealer, gambler);
        result.add(gambler, winOrLose);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Gamblers getGamblers() {
        return gamblers;
    }
}
