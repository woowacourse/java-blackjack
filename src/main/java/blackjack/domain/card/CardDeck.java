package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CardDeck {

    private final List<Card> cards;

    public CardDeck() {
        cards = new ArrayList<>();
        Arrays.stream(Pattern.values())
                .map(this::createCardsBy)
                .forEach(cards::addAll);
    }

    private List<Card> createCardsBy(Pattern pattern) {
        return Arrays.stream(Denomination.values())
                .map(denomination -> new Card(pattern, denomination))
                .collect(Collectors.toList());
    }

    public Card draw() {
        return cards.get(0);
    }
}
