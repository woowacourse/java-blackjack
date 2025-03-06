package blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class Player implements Comparable<Player> {

    private final String name;
    private final List<Card> cards;

    public Player(final String name) {
        this.name = name;
        cards = new ArrayList<>();
    }

    public Player() {
        this("딜러");
    }

    public void pushDealCard(final CardPack cardPack, final int count) {
        IntStream.range(0, count)
                .mapToObj(i -> cardPack.getDeal())
                .forEach(cards::add);
    }

    public boolean isPlayerBust() {
        return calculateCardNumber() > 21;
    }

    public boolean isPlayerNotBust() {
        return !isPlayerBust();
    }

    public int calculateCardNumber() {
        int sum = cards.stream()
                .mapToInt(Card::getNumber)
                .sum();
        if (canCalculateAceWithEleven(sum)) {
            sum += 10;
        }
        return sum;
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }

    private boolean canCalculateAceWithEleven(int sum) {
        return hasAce() && sum + 10 <= 21;
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name) && Objects.equals(cards, player.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cards);
    }

    @Override
    public int compareTo(Player o) {
        if (this.isPlayerBust() && o.isPlayerBust()) {
            return 0;
        }
        if (this.isPlayerNotBust() && o.isPlayerNotBust()) {
            return Integer.compare(this.calculateCardNumber(), o.calculateCardNumber());
        }
        if (this.isPlayerNotBust() && o.isPlayerBust()) {
            return -1;
        }
        return 1;
    }
}
