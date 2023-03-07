package blackjack.domain.gameplayer;

import blackjack.domain.Score;
import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dealer implements Person {
    private final List<Card> cards;

    public Dealer() {
        this.cards = new ArrayList<>();
    }

    @Override
    public void addCard(Card card) {
        cards.add(card);
    }

    @Override
    public boolean isHit() {
        Score totalScore = calculateScore();
        return totalScore.isLessThan(Score.dealerHitUpperBound);
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
