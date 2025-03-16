package view;

import java.util.List;
import model.card.Card;
import model.card.Rank;
import model.card.Suit;
import model.participant.Name;

public abstract class BlackjackView {
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
