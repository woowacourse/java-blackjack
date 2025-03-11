package blackjack.domain.card;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Cards {
    private final List<Card> cards;
    private final Score score;

    public Cards(List<Card> cards) {
        this.cards = cards;
        this.score = new Score(this);
    }

    public int calculateMaxScore() {
        return score.calculateMaxScore();
    }

    public int calculateMinScore() {
        return score.calculateMinScore();
    }

    public boolean isBlackjack() {
        return score.isBlackjack();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public void take(Card card1, Card card2) {
        this.cards.addAll(Arrays.asList(card1, card2));
    }

    public void additionalTake(Card card) {
        score.additionalTake(card);
    }

    public boolean canTake() {
        return score.canTake();
    }

    public int getSize() {
        return cards.size();
    }

    public Card getCard(int index) {
        return cards.get(index);
    }

    public void add(Card card) {
        cards.add(card);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Cards cards1 = (Cards) object;
        return Objects.equals(getCards(), cards1.getCards()) && Objects.equals(score,
                cards1.score);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getCards());
        result = 31 * result + Objects.hashCode(score);
        return result;
    }
}
