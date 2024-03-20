package model.participants.dealer;

import java.util.List;
import model.card.Card;
import model.card.CardType;
import model.card.Cards;
import model.participants.Participant;

public class Dealer extends Participant {

    private static final int ADD_CARD_CONDITION = 17;
    private static final String TITLE_FOR_DEALER = "딜러";

    public Dealer() {
        super(TITLE_FOR_DEALER, new Cards(List.of()));
    }

    public Dealer(Cards cards) {
        super(TITLE_FOR_DEALER, cards);
    }

    public boolean isPossibleAddCard() {
        return cards.calculateTotalNumbers() < ADD_CARD_CONDITION;
    }

    @Override
    public Dealer addCard(Card card) {
        Cards addedCards = cards.add(card);
        return new Dealer(addedCards);
    }

    @Override
    public Dealer addCards(List<Card> cardsElement) {
        Cards addedCards = cards.addAll(cardsElement);
        return new Dealer(addedCards);
    }

    public CardType captureFirstCardType() {
         return captureCardType().get(0);
    }
}
