package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Score;
import java.util.Collections;
import java.util.List;

public class Dealer implements Participant {
    public static final String DEALER_NAME = "딜러";
    private static final Score MINIMUM_SCORE_OF_TAKING_CARD = Score.of(16);
    private final Cards cards;

    public Dealer() {
        this(new Cards(Collections.emptyList()));
    }

    public Dealer(Cards cards) {
        this.cards = cards;
    }

    @Override
    public boolean isAbleToTake() {
        final Score score = sumCards();
        return score.isEqualAndLessThan(MINIMUM_SCORE_OF_TAKING_CARD);
    }

    @Override
    public String getName() {
        return DEALER_NAME;
    }

    @Override
    public void takeCard(Card card) {
        cards.takeCard(card);
    }

    @Override
    public Score sumCards() {
        return cards.sumCards();
    }

    @Override
    public Score sumCardsForResult() {
        return cards.sumCardsForResult();
    }

    @Override
    public Cards getCards() {
        return cards;
    }

    @Override
    public List<Card> getUnmodifiableCards() {
        return cards.getUnmodifiableList();
    }

}
