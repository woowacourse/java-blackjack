package domain.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BlackjackGameCardGenerator implements CardGenerator {

    @Override
    public List<Card> generate() {
        List<Card> cards = generateAllCards();
        shuffle(cards);
        return cards;
    }

    private List<Card> generateAllCards() {
        return Arrays.stream(CardDenomination.values())
                .flatMap(this::generateCardsByDenomination)
                .collect(Collectors.toList());
    }

    private Stream<Card> generateCardsByDenomination(CardDenomination denomination) {
        return Arrays.stream(CardEmblem.values())
                .map(emblem -> Card.of(denomination, emblem));
    }

    private void shuffle(List<Card> blackjackGameCards) {
        Collections.shuffle(blackjackGameCards);
    }

}
