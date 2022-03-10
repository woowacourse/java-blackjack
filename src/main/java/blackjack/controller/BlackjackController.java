package blackjack.controller;

import blackjack.domain.card.CardDeck;
import blackjack.domain.game.BlackjackGame;
import java.util.List;

public class BlackjackController {
    public BlackjackGame initializeGame(List<String> playerNames) {
        return new BlackjackGame(new CardDeck(), playerNames);
    }
}
