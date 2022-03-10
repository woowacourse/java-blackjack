package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.result.ScoreCalculator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dealer {

    private static final int MIN_SUM_STANDARD = 16;

    private final List<Card> cards;

    public Dealer() {
        this.cards = new ArrayList<>();
    }

    public void receiveCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public boolean checkUnderSumStandard() {
        return ScoreCalculator.cardSum(cards) <= MIN_SUM_STANDARD;
    }

    public int getCardSum() {
        return ScoreCalculator.cardSum(cards);
    }
}
