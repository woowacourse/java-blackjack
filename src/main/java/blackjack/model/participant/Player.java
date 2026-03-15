package blackjack.model.participant;

import blackjack.model.cardDeck.CardDeck;
import blackjack.model.Hands;

public class Player extends Participant {

    private final int prize;

    private Player(String name, Hands hands, int prize) {
        super(name, hands);
        this.prize = prize;
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

    public Player lose() {
        int negativeMultiplier = -1;

        return new Player(
                name,
                hands,
                negativeMultiplier * prize
        );
    }

    public Player blackjack() {
        double blackjackProfitRate = 1.5;

        return new Player(
                name,
                hands,
                (int) (prize * blackjackProfitRate)
        );
    }

    public int getPrize() {
        return prize;
    }
}
