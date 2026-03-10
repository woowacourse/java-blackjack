package blackjack.model.participant;

import blackjack.model.Hands;
import blackjack.model.card.Card;
import blackjack.model.cardDeck.CardDeck;
import blackjack.model.result.Result;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final int DEALER_PICK_THRESHOLD = 16;

    private Dealer(Hands hands) {
        super(DEALER_NAME, hands);
    }

    public static Dealer create() {
        return new Dealer(Hands.empty());
    }

    @Override
    public void pickInitCards(CardDeck cardDeck) {
        hands.addCard(cardDeck.pick());

        Card secondPickedCard = cardDeck.pick();
        secondPickedCard.flip();
        hands.addCard(secondPickedCard);
    }

    public boolean canPick() {
        return !hands.hasScoreHigherThan(DEALER_PICK_THRESHOLD);
    }

    public Result judgePlayerResult(Player player) {
        if (player.isBust()) {
            return Result.LOSE;
        }

        if (this.isBust()) {
            return Result.WIN;
        }

        if (this.hasHigherScoreThan(player)) {
            return Result.LOSE;
        }

        if (player.hasHigherScoreThan(this)) {
            return Result.WIN;
        }

        return Result.DRAW;
    }
}
