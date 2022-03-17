package blackjack.domain.card;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class PlayerCards {

    private static final int ACE_ADDITIONAL_VALUE = 10;
    private static final int MAX_SCORE = 21;

    private final List<Card> playerCards;

    public PlayerCards(List<Card> playerCards) {
        this.playerCards = playerCards;
    }

    public void add(Card card) {
        playerCards.add(card);
    }

    public List<Card> get() {
        return Collections.unmodifiableList(playerCards);
    }

    public int getTotalScore() {
        int sum = playerCards.stream()
                .mapToInt(value -> value.getCardNumber().getValue())
                .sum();

        if (hasAce() && canAddAceAdditionalValue(sum)) {
            return sum + ACE_ADDITIONAL_VALUE;
        }
        return sum;
    }

    private boolean hasAce() {
        return playerCards.stream()
                .anyMatch(card -> card.getCardNumber().equals(CardNumber.ACE));
    }

    private boolean canAddAceAdditionalValue(int sum) {
        return sum + ACE_ADDITIONAL_VALUE <= MAX_SCORE;
    }

    public boolean containsCardNumber(CardNumber number) {
        return playerCards.stream()
                .anyMatch(card -> card.getCardNumber() == number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PlayerCards)) {
            return false;
        }
        PlayerCards playerCards1 = (PlayerCards) o;
        return Objects.equals(playerCards, playerCards1.playerCards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerCards);
    }

    @Override
    public String toString() {
        return "Cards{" +
                "playerCards=" + playerCards +
                '}';
    }
}
