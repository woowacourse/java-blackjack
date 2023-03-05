package blackjack.domain.participant;

import blackjack.domain.card.Card;

import java.util.List;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(new Name(DEALER_NAME));
    }

    public List<String> getOneCard() {
        final Card firstCard = this.getCards().get(0);

        return List.of(firstCard.getCardName());
    }

    public boolean canHit() {
        return getTotalScore() <= 16;
    }
}
