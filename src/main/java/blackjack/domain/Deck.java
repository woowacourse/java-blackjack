package blackjack.domain;

import java.util.LinkedList;
import java.util.Queue;

public class Deck {


    private final Queue<Card> cards;


    //TODO: basedeck을 유지하고, temp도 안쓰는 방법이 생각나면 리팩터링
    public Deck(DeckGenerator deckGenerator) {
        this.cards = new LinkedList<>(deckGenerator.generate());
    }

    public Card draw() {
        return cards.poll();
    }
}
