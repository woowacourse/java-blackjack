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

    private static List<Card> generateAllCards() {
        List<Card> cards = new ArrayList<>();
        Arrays.stream(CardDenomination.values()).forEach(denomination ->
                Arrays.stream(CardEmblem.values()).forEach(emblem ->
                        cards.add(Card.of(denomination, emblem))
                )
        );
        return cards;
    }

    private void shuffle(List<Card> blackjackGameCards) {
        Collections.shuffle(blackjackGameCards);
    }

}
