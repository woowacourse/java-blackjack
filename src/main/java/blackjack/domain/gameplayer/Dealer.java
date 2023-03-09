package blackjack.domain.gameplayer;

import blackjack.domain.Score;
import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dealer implements Person {
    public static final Score hitUpperBound = Score.of(17);

    private final List<Card> cards;

    public Dealer() {
        this.cards = new ArrayList<>();
    }

    @Override
    public void addCard(Card card) {
        cards.add(card);
    }

    @Override
    public boolean canContinue() {
        Score totalScore = calculateScore();
        return totalScore.isLessThan(hitUpperBound);
    }

    @Override
    public Score calculateScore() {
        return Score.from(cards);
    }

    @Override
    public List<Card> showCards() {
        return List.of(cards.get(0));
    }

    public List<Card> showAllCards() {
        return Collections.unmodifiableList(cards);
    }
}
