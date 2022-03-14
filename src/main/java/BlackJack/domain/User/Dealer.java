package BlackJack.domain.User;

import BlackJack.domain.Card.CardFactory;
import BlackJack.domain.Result;

import static BlackJack.domain.Card.Cards.BUST_LINE;

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
        if (player.getScore() > BUST_LINE || (this.getScore() > player.getScore() && this.getScore() <= BUST_LINE)) {
            return Result.LOSE;
        }
        if (this.getScore() > BUST_LINE || this.getScore() < player.getScore() && player.getScore() <= BUST_LINE) {
            dealerLoseCount++;
            return Result.WIN;
        }
        dealerDrawCount++;
        return Result.DRAW;
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
