package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.List;

public class Player extends Participant{

    private static final int OPEN_CARD_SIZE = 2;
    private boolean stay = false;


    public Player(String name, Cards cards) {
        super(name, cards);
    }

    @Override
    public List<Card> openCards() {
        return getCards().subList(0, OPEN_CARD_SIZE);
    }

    public boolean isAbleToHit() {
        return !stay && !super.isBlackjack() && !super.isMaxScore() && !super.isBust();
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

    public Result judgeResult(Dealer dealer) {
        return Result.calculateResult(this, dealer);
    }
}
