package card;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CardDeck {

    private List<Card> deck;

    public CardDeck() {
        deck = readyCards();
    }

    private List<Card> readyCards() {
        return Stream.of(CardPattern.values())
                .flatMap(this::createCardsForPattern)
                .collect(Collectors.toList());
    }

    private Stream<Card> createCardsForPattern(CardPattern pattern) {
        return Stream.of(CardNumber.values())
                .map(number -> new Card(number, pattern));
    }

    public Card pickCard(int randomIndex) {
        validateCardIndex(randomIndex);
        return deck.remove(randomIndex);
    }

    private void validateCardIndex(int randomIndex) {
        if (randomIndex < 0 || randomIndex >= deck.size()) {
            throw new IllegalArgumentException(randomIndex + "는 뽑을 수 있는 카드가 아닙니다.");
        }
    }

}
