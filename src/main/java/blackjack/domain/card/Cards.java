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
        Score score = new Score(cards.stream()
            .mapToInt(Card::findScore)
            .sum());
        return addIfAceExist(score);
    }

    private Score addIfAceExist(Score total) {
        int aceCount = countAce();
        while (total.isLessThanEleven() && aceCount != 0) {
            total = total.plus();
            aceCount--;
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
