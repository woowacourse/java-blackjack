package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Player extends Participant{


    private boolean stay = false;

    public Player(String name, Cards cards) {
        super(name, cards);
    }

    @Override
    public void addCard(Card card) {
        super.addCard(card);
        if (super.isBust() || super.isBlackjack()) {
            stay = true;
        }
    }


    public boolean isAbleToHit() {
        return !stay;
    }

    public void stay() {
        stay = true;
    }



    public Outcome compareScoreWith(Dealer dealer) {
        if (dealer.isBust()) {
            return Outcome.WIN;
        }

        if (!dealer.isBust() && isBust()) {
            return Outcome.LOSE;
        }

        if (dealer.getScore() > getScore()) {
            return Outcome.LOSE;
        }

        return Outcome.WIN;
    }
}
