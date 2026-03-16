package domain.card;

import java.util.List;

public record CurrentHand(
        String name,
        List<Card> cards
) {
    public String getCardsToString() {
        final StringBuilder cardsToString = new StringBuilder();
        for (final Card card : cards) {
            cardsToString.append(card.getCardDescription()).append(", ");
        }
        cardsToString.delete(cardsToString.length() - 2, cardsToString.length());

        return cardsToString.toString();
    }
}
