package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Deck {
    
    private static final int TOP_CARD_INDEX = 0;
    
    private static final String ERROR_EMPTY_DECK = "덱에 카드가 존재하지 않습니다.";
    
    private final List<Card> deck;
    
    Deck(List<Card> deck) {
        this.deck = new ArrayList<>(deck);
    }
    
    public static Deck createShuffledDeck() {
        List<Card> deck = createDeck();
        Collections.shuffle(deck);
        return new Deck(deck);
    }
    
    private static List<Card> createDeck() {
        return Arrays.stream(Suit.values())
                     .flatMap(suit -> Arrays.stream(Rank.values())
                                            .map(rank -> new Card(suit, rank)))
                     .collect(Collectors.toList());
    }
    
    public Card drawCard() {
        if (deck.isEmpty()) {
            throw new IllegalArgumentException(ERROR_EMPTY_DECK);
        }
        
        return deck.remove(TOP_CARD_INDEX);
    }
}
