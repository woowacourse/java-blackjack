package blackjack.service.dto;

import blackjack.model.cardDeck.CardDeck;
import blackjack.model.participant.Dealer;
import blackjack.model.participant.Players;

public record GameState (
        Players players,
        Dealer dealer,
        CardDeck cardDeck
){
    public GameState updatePlayers(Players players) {
        return new GameState(
                players,
                dealer,
                cardDeck
        );
    }
}
