package blackjack.model.participant;

import blackjack.model.cardDeck.CardDeck;
import blackjack.model.Hands;

public class Player extends Participant {

    private final Prize prize;

    private Player(String name, Hands hands, Prize prize) {
        super(name, hands);

        if (prize == null) {
            throw new IllegalArgumentException("prize가 null입니다.");
        }

        this.prize = prize;
    }

    public static Player of(String name, int betAmount) {
        return new Player(
                name,
                Hands.createEmptyHand(),
                Prize.of(betAmount)
        );
    }

    @Override
    public void pickInitialCards(CardDeck cardDeck) {
        validateCardDeck(cardDeck);
        hands.addCard(cardDeck.pick());
        hands.addCard(cardDeck.pick());
    }

    public Player lose() {
        return new Player(
                name,
                hands,
                prize.lose()
        );
    }

    public Player blackjack() {
        return new Player(
                name,
                hands,
                prize.blackjack()
        );
    }

    public int getPrize() {
        return prize.getAmount();
    }
}
