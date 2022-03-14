package BlackJack.domain.User;

import BlackJack.domain.Card.CardFactory;
import BlackJack.domain.Result;

public class Dealer extends User {

    private static final int DEALER_ADD_CARD_LIMIT = 16;

    private int dealerLoseCount = 0;
    private int dealerDrawCount = 0;

    public Dealer(CardFactory cardFactory) {
        super("딜러", cardFactory.initCards());
    }

    public boolean canOneMoreCard() {
        return cards.calculateScore() <= DEALER_ADD_CARD_LIMIT;
    }

    public Result compare(Player player) {
        if (isWin(player)) {
            return Result.LOSE;
        }
        if (isLose(player)) {
            dealerLoseCount++;
            return Result.WIN;
        }
        dealerDrawCount++;
        return Result.DRAW;
    }

    private boolean isWin(Player player) {
        return player.isBust() || (this.isGreaterScoreThan(player) && !this.isBust());
    }

    private boolean isLose(Player player) {
        return this.isBust() || (player.isGreaterScoreThan(this) && !player.isBust());
    }

    public int getDealerLoseCount() {
        return dealerLoseCount;
    }

    public int getDealerDrawCount() {
        return dealerDrawCount;
    }

    @Override
    public void addCard(CardFactory cardFactory) {
        cards.add(cardFactory.drawOneCard());
    }

}
