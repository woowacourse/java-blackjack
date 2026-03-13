package blackjack.domain.participant;

import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.List;

public abstract class Participant {

    private static final int BLACKJACK_VALUE = 21;
    private static final int ACE_ADVANTAGE_VALUE = 10;

    private final ParticipantName name;
    private final List<Card> cards;

    public Participant(String name) {
        this.name = new ParticipantName(name);
        this.cards = new ArrayList<>();
    }

    public final void draw(Card card) {
        cards.add(card);
    }

    public final int calculateCardsValue() {
        int sum = cards.stream()
                .mapToInt(Card::getCardValue)
                .sum();
        return applyBestAceValue(sum);
    }

    public final boolean winsAgainst(Participant other) {
        if (other.isBurst()) {
            return true;
        }
        if (this.isBurst()) {
            return false;
        }
        return other.calculateCardsValue() < this.calculateCardsValue();
    }

    public final boolean isBurst() {
        return calculateCardsValue() > BLACKJACK_VALUE;
    }

    public final boolean isBlackjack() {
        return calculateCardsValue() == BLACKJACK_VALUE;
    }

    public final String getFirstCardName() {
        return cards.getFirst().getName();
    }

    public final String getName() {
        return name.name();
    }

    public final List<Card> getCards() {
        return List.copyOf(cards);
    }

    public final List<String> getCardsName() {
        return cards.stream()
                .map(Card::getName)
                .toList();
    }

    public abstract boolean canDraw();

    private int applyBestAceValue(int sum) {
        if (hasAce() && (sum + ACE_ADVANTAGE_VALUE) <= BLACKJACK_VALUE) {
            return sum + ACE_ADVANTAGE_VALUE;
        }
        return sum;
    }

    private boolean hasAce() {
        return cards.stream().anyMatch(Card::isAce);
    }

}
