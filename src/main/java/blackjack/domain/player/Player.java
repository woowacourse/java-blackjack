package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardPack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public abstract class Player {

    private final Name name;
    private final List<Card> cards;
    private static final int MAX_HAND_VALUE = 21;
    private static final int ACE_ADDITIONAL_VALUE = 10;

    public Player(final String name) {
        this.name = new Name(name);
        cards = new ArrayList<>();
    }

    public void pushDealCard(final CardPack cardPack, final int count) {
        IntStream.range(0, count)
                .mapToObj(i -> cardPack.getDeal())
                .forEach(cards::add);
    }

    public boolean isPlayerBust() {
        return calculateCardNumber() > MAX_HAND_VALUE;
    }

    public boolean isPlayerNotBust() {
        return !isPlayerBust();
    }

    public int calculateCardNumber() {
        int sum = cards.stream()
                .mapToInt(Card::getValue)
                .sum();
        if (canCalculateAceWithEleven(sum)) {
            sum += 10;
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
        return hasAce() && sum + ACE_ADDITIONAL_VALUE <= MAX_HAND_VALUE;
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
}
