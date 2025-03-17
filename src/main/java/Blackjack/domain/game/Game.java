package Blackjack.domain.game;

import Blackjack.domain.card.Card;
import java.util.List;

public class Game {

    private final CardDeck cardDeck;
    private final Participants participants;

    public Game(final CardDeck cardDeck, final Participants participants) {
        this.cardDeck = cardDeck;
        this.participants = participants;
    }

    public void distributeInitialCards() {
        final List<Card> cards = cardDeck.pickCards(participants.size() * 2);
        participants.addInitialCards(cards);
    }

    public boolean isAbleToAddCard(final String name) {
        return participants.isAbleToAddCard(name);
    }

    public void distributeExtraCardTo(final String name) {
        participants.addCardTo(name, cardDeck.pickCard());
    }

    public boolean isAddedExtraCardToDealer() {
        if (participants.canHitDealer()) {
            participants.hitDealer(cardDeck.pickCard());
            return true;
        }
        return false;
    }
}
