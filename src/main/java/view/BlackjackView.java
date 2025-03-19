package view;

import card.Card;
import card.Rank;
import card.Suit;
import java.util.List;
import participant.Name;

public abstract class BlackjackView {
    protected static final Name DEALER_NAME = new Name("딜러");

    public String getParticipantCards(final Name name, final List<Card> cards) {
        List<String> cardNotations = cards.stream()
                .map(this::getCardNotation)
                .toList();
        return String.format("%s 카드: %s", name, String.join(", ", cardNotations));
    }

    public String getEmptyLine() {
        return System.lineSeparator();
    }

    protected String getCardNotation(final Card card) {
        Rank rank = card.getRank();
        Suit suit = card.getSuit();
        return rank.getName() + suit.getName();
    }
}
