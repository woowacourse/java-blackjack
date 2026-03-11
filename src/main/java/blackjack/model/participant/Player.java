package blackjack.model.participant;

import blackjack.model.cardDeck.CardDeck;
import blackjack.model.Hands;

public class Player extends Participant {

    private final int betAmount;

    private Player(String name, Hands hands, int betAmount) {
        super(name, hands);
        this.betAmount = betAmount;
    }

    public static Player of(String name, int betAmount) {
        return new Player(
                name,
                Hands.empty(),
                betAmount
        );
    }

    @Override
    public void pickInitialCards(CardDeck cardDeck) {
        hands.addCard(cardDeck.pick());
        hands.addCard(cardDeck.pick());
    }

    @Override
    public boolean canPick() {
        return !hands.isTotalScoreOver(BLACKJACK_SCORE);
    }
}
