package BlackJack.domain.User;

import BlackJack.domain.Card.CardFactory;
import BlackJack.domain.Card.Cards;
import BlackJack.domain.Result;

import static BlackJack.domain.Card.Cards.BUST_LINE;

public class Dealer extends User {

    private static final int DEALER_ADD_CARD_LIMIT = 16;

    private int dealerLoseCount = 0;
    private int dealerDrawCount = 0;

    public Dealer(Cards cards) {
        super("딜러", cards);
    }

    @Override
    public void addCard() {
        cards.add(CardFactory.drawOneCard());
    }

    public boolean checkScore() {
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

}
