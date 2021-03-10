package blackjack.domain.card;

import blackjack.domain.Score;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Cards {

    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public void addCards(List<Card> cards) {
        this.cards.addAll(cards);
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public String loadCards() {
        return cards.stream()
            .map(Card::getName)
            .collect(Collectors.joining(", "));
    }

    public Score calculateTotalScore() {
        Score score = sum();
        return addIfAceExist(score, countAce());
    }

    private Score sum() {
        return new Score(cards.stream()
            .map(Card::findScore)
            .mapToInt(Score::toInt)
            .sum());
    }

    private Score addIfAceExist(Score total, int aceCount) {
        for (int count = 0; count < aceCount; count++) {
            total = total.plusTenIfNotBust();
        }
        return total;
    }

    private int countAce() {
        return Math.toIntExact(cards.stream().filter(Card::isAce).count());
    }

    public List<Card> getCards() {
        return cards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cards cards1 = (Cards) o;
        return Objects.equals(cards, cards1.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }
}
