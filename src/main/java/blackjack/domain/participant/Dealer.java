package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.result.WinningResult;

import java.util.Collections;
import java.util.List;

public class Dealer extends Participant {

    public static final String DEALER_NAME = "딜러";
    public static final int DEALER_MIN_SCORE = 17;

    private Dealer() {
        this.name = DEALER_NAME;
    }

    public static Dealer startWithTwoCards(final Deck deck) {
        final Dealer dealer = new Dealer();
        dealer.drawCard(deck);
        dealer.drawCard(deck);
        return dealer;
    }

    public boolean isPossibleToDraw() {
        return calculateScore() < DEALER_MIN_SCORE;
    }

    public WinningResult judgeWinner(final Player player) {
        if (player.isBust()) {
            return WinningResult.LOSS;
        }
        if (this.isBust()) {
            return WinningResult.WIN;
        }

        return WinningResult.calculateResult(player.calculateScore(), this.calculateScore());
    }

    @Override
    public List<Card> showFirstCards() {
        return Collections.singletonList(cards.get(0));
    }

}
