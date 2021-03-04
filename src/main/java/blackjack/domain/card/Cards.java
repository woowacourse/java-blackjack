package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Cards {
    private final List<Card> cards;

    public Cards(List<Card> deck) {
        this.cards = new ArrayList<>(deck);
    }

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public String getCards() {
        return cards.stream()
                .map(Card::getName)
                .collect(Collectors.joining(", "));
    }

    public int getScore() {
        int total = cards.stream().mapToInt(Card::getScore).sum();
        return addIfAceExist(total);
    }

    private int addIfAceExist(int total) {
        int aceCount = countAce();
        while (total + 10 <= 21 && aceCount != 0) {
            total += 10;
            aceCount--;
        }
        return total;
    }

    private int countAce() {
        return Math.toIntExact(cards.stream().filter(Card::isAce).count());
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
