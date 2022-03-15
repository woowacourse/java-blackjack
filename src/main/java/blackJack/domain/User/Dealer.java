package blackJack.domain.User;

import blackJack.domain.Card.CardFactory;

public class Dealer extends User {

    private static final int DEALER_ADD_CARD_LIMIT = 16;

    private int dealerLoseCount = 0;
    private int dealerWinCount = 0;

    public Dealer(CardFactory cardFactory) {
        super("딜러", cardFactory.initCards());
    }

    public boolean canOneMoreCard() {
        return cards.getScore() <= DEALER_ADD_CARD_LIMIT;
    }

    public int getDealerLoseCount() {
        return dealerLoseCount;
    }

    public int getDealerWinCount() {
        return dealerWinCount;
    }

    @Override
    public void hit(CardFactory cardFactory) {
        cards.add(cardFactory.drawOneCard());
    }

    public boolean isLose(Player player) {
        if(isBust() || (player.isGreaterScoreThan(this) && !player.isBust())){
            dealerLoseCount++;
            return true;
        }
        return false;
    }

    public boolean isWin(Player player) {
        if(player.isBust() || (isGreaterScoreThan(player) && !isBust())){
            dealerWinCount++;
            return true;
        }
        return false;
    }
}
