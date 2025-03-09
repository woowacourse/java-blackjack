package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardPack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Player implements Comparable<Player> {

    public static final int BUST_THRESHOLD = 21;
    public static final int ACE_PLUS_SCORE = 10;

    private final Name name;
    private final List<Card> cards;

    public Player(final String name) {
        this.name = new Name(name);
        cards = new ArrayList<>();
    }

    public void pushDealCard(final CardPack cardPack, final int count) {
        cards.addAll(cardPack.getDealByCount(count));
    }

    public boolean isPlayerBust() {
        return calculateCardNumber() > BUST_THRESHOLD;
    }

    public boolean isPlayerNotBust() {
        return !isPlayerBust();
    }

    public int calculateCardNumber() {
        int sum = cards.stream()
                .mapToInt(Card::getValue)
                .sum();
        if (canCalculateAceWithEleven(sum)) {
            sum += ACE_PLUS_SCORE;
        }
        return sum;
    }

    public String getName() {
        return name.getName();
    }

    abstract public List<Card> getOpenedCards();

    public List<Card> getCards() {
        return cards;
    }

    private boolean canCalculateAceWithEleven(int sum) {
        return hasAce() && sum + ACE_PLUS_SCORE <= BUST_THRESHOLD;
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
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
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
            return 1;
        }
        return -1;
    }
}
