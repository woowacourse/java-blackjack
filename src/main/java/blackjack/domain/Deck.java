package blackjack.domain;

import java.util.*;
import java.util.stream.Collectors;

public class Deck {
    private final List<Card> deck = new ArrayList<>(52);
//            Arrays.stream(CardPattern.values())
//            .flatMap(cardPattern -> Arrays.stream(CardNumber.values())
//                    .map(cardNumber -> new Card(cardPattern, cardNumber)))
//            .collect(Collectors.toList()));

    public Deck() {
        for(CardPattern cardPattern : CardPattern.values()) {
            for(CardNumber cardNumber : CardNumber.values()) {
                deck.add(new Card(cardPattern, cardNumber));
            }
        }
        Collections.shuffle(deck);
    }

    public Card dealCard() {
        return deck.remove(0);
    }

    public Card choiceCard(int index) {
        return deck.get(index);
    }

    public int getSize() {
        return deck.size();
    }
}
