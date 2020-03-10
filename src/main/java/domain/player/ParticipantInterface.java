package domain.player;

import domain.card.CardDeck;

public interface ParticipantInterface {
    void firstDraw(CardDeck cardDeck);

    void receive(CardDeck cardDeck);

    String getName();

    String toString();
}