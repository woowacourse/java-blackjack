package model;

import constant.ErrorMessage;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import model.dto.Card;

public class BlackJackDeck {
    private Cards cards = new Cards();

    public BlackJackDeck() {
        initCards();
    }

    public void shuffle() {
        List<Card> currentCards = cards.get();
        Collections.shuffle(currentCards);
        this.cards = new Cards(currentCards);
    }

    public Card draw() {
        List<Card> currentCards = cards.get();
        if(currentCards.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.NO_CARD_IN_DECK.getMessage());
        }

        Card drawedCard = currentCards.removeFirst();
        this.cards = new Cards(currentCards);

        return drawedCard;
    }

    private void initCards() {
        Arrays.stream(Shape.values()).forEach(shape -> {
            Arrays.stream(CardNumber.values()).forEach(number -> {
                cards.add(new Card(shape, number));
            });
        });
    }
}
