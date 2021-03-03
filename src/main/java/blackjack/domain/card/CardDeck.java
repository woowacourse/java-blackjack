package blackjack.domain.card;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CardDeck {
    private final List<Card> deck;

    public CardDeck() {
        deck = Arrays.stream(Denomination.values())
                .flatMap(denomination -> Arrays
                        .stream(Shape.values())
                        .map(shape -> new Card(shape, denomination)))
                .collect(Collectors.toList());
    }

    public Card drawCard() {
        if(deck.size() == 0){
            throw new IndexOutOfBoundsException("남은 카드가 없습니다.");
        }
        return deck.remove(0);
    }
}
