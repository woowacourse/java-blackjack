package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.List;

public class Player extends Participant{

    private static final int OPEN_CARD_SIZE = 2;

    private boolean stay = false;
    private long bettingMoney = 0;

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

    public long calculateDividend(Dealer dealer) {
        return judgeResult(dealer).calculateDividend(bettingMoney);
    }

    public void bet(long bettingMoney) {
        this.bettingMoney = bettingMoney;
    }

    public boolean isAbleToBet() {
        return bettingMoney == 0;
    }

    private Result judgeResult(Dealer dealer) {
        return Result.calculateResult(this, dealer);
    }
}
