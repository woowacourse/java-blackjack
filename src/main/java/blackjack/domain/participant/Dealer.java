package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.result.PlayerResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dealer extends Participant {

    public static final String DEALER_NAME = "딜러";
    public static final int DEALER_MIN_SCORE = 17;

    private Dealer() {
        super(DEALER_NAME, new ArrayList<>());
    }

    public static Dealer startWithTwoCards(final Deck deck) {
        final Dealer dealer = new Dealer();
        dealer.drawCard(deck);
        dealer.drawCard(deck);
        return dealer;
    }

    public boolean isPossibleToDraw() {
        return !calculateScore().isOver(DEALER_MIN_SCORE);
    }

    public PlayerResult judgeWinner(final Player player) {
        if (player.isBust()) {
            return PlayerResult.LOSS;
        }
        if (this.isBust()) {
            return PlayerResult.WIN;
        }

        return PlayerResult.calculateResult(player.calculateScore(), this.calculateScore());
    }

    @Override
    public List<Card> showFirstCards() {
        return Collections.singletonList(getCards().get(0));
    }
}
