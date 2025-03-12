package domain;

import domain.card.Card;
import java.util.List;

public class Game {

    private final CardDeck cardDeck;
    private final Participants participants;

    public Game(final CardDeck cardDeck, final Participants participants) {
        this.cardDeck = cardDeck;
        this.participants = participants;
    }

    public void distributeInitialCards() {
        List<Card> cards = cardDeck.pickCards(participants.size() * 2);
        participants.addInitialCards(cards);
    }

    public boolean isAbleToAddCard(String name) {
        return participants.isAbleToAddCard(name);
    }

    public void distributeExtraCardTo(String name) {
        participants.addCardTo(name, cardDeck.pickCard());
    }

    public boolean isAddedExtraCardToDealer() {
        Dealer dealer = participants.findDealer();
        if (dealer.ableToAddCard()) {
            dealer.addCard(cardDeck.pickCard());
            return true;
        }
        return false;
    }
}
