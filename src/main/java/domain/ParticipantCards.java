package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.card.Card;

public class ParticipantCards {
    public static final String DELIMITER = ", ";
    private final List<Card> cards = new ArrayList<>();

    public void add(Card card) {
        cards.add(card);
    }

    @Override
    public String toString() {
        List<String> cardNames = cards.stream()
            .map(Card::toString)
            .collect(Collectors.toList());
        return String.join(DELIMITER, cardNames);
    }
}
