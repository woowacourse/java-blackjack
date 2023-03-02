package blackjack.domain.service;

import blackjack.domain.model.Card;
import blackjack.domain.vo.Letter;
import blackjack.domain.vo.Shape;

import java.util.ArrayList;
import java.util.List;

public class DeckMakerService {
    public List<Card> makeDeck() {
        List<Card> deck = new ArrayList<>();
        for (Shape shape : Shape.values()) {
            makeCard(deck, shape);
        }
        return deck;
    }

    private void makeCard(List<Card> deck, Shape shape) {
        for (Letter letter : Letter.values()) {
            deck.add(new Card(shape, letter));
        }
    }
}
