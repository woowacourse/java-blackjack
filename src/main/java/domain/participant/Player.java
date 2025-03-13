package domain.participant;

import domain.card.CardHand;
import domain.game.GameResult;

public class Player extends GameParticipant {
    public Player(String name, CardHand cardHand) {
        super(name, cardHand);
    }

    public GameResult calculateGameResult(Dealer dealer) {
        return GameResult.calculatePlayerGameResult(dealer, this);
    }
}
