package model;

import constant.CardErrorCode;
import exception.GameException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import dto.Card;

public class BlackJackDeck {
    private Cards cards;

    public BlackJackDeck() {
        this.cards = new Cards();
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
            throw new GameException(CardErrorCode.NO_CARD_IN_DECK);
        }

        Card drawedCard = currentCards.removeFirst();
        this.cards = new Cards(currentCards);

        return drawedCard;
    }

 private void initCards() {
     Arrays.asList(Shape.values()).forEach(shape -> {
         Arrays.asList(CardNumber.values()).forEach(number -> {
             cards.add(new Card(shape, number));
         });
     });
 }
}
