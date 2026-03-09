package domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GameCardGenerator implements CardGenerator {

    @Override
    public List<Card> generate() {
        List<Card> cards = generateAllCards();
        shuffle(cards);
        return cards;
    }

    private List<Card> generateAllCards() {
        return Arrays.stream(CardDenomination.values())
                .flatMap(denomination -> Arrays.stream(CardEmblem.values())
                        .map(emblem -> Card.of(denomination, emblem)))
                .toList();
    }

    private void shuffle(List<Card> blackjackGameCards) {
        Collections.shuffle(blackjackGameCards);
    }

}
