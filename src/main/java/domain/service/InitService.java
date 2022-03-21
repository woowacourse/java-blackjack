package domain.service;

import domain.card.CardDeck;
import domain.player.Players;

public interface InitService {
    Players initPlayers();

    CardDeck initCardDeck();
}
