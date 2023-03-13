package blackjackgame.domain.user;

import blackjackgame.domain.card.Card;
import java.util.List;

public class Player extends User {
    private PlayerStatus status = PlayerStatus.HITTABLE;
    private final Profit profit;

    public Player(Name name, Bet bet) {
        super(name);
        this.profit = new Profit(bet);
    }

    @Override
    public void receiveCard(Card card) {
        super.receiveCard(card);
        status = hands.calculatePlayerStatus();
    }

    @Override
    public void receiveCards(List<Card> cards) {
        super.receiveCards(cards);
        status = hands.calculatePlayerStatus();
    }

    public void win() {
        profit.keep();
    }

    public void lose() {
        profit.becomeNegative();
    }

    public void draw() {
        profit.lose();
    }

    public void winWithBlackJack(double blackJackBonusPercentage) {
        profit.applyBonus(blackJackBonusPercentage);
    }

    public void finishDraw() {
        status = PlayerStatus.DRAW_FINISHED;
    }

    public boolean isHittable() {
        return PlayerStatus.HITTABLE == status;
    }

    @Override
    public UserStatus getStatus() {
        return status;
    }

    public Profit getProfit() {
        return profit;
    }

    public int getProfitAmount() {
        return profit.getAmount();
    }
}
