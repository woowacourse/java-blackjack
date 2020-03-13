package domain.user;

import domain.result.WinningResult;

public class Player extends User {

    public Player(String name) {
        super(name);
    }

    @Override
    public boolean isAvailableToDraw() {
        return !cards.isBust() && !cards.isBlackJack() && !cards.isBlackJackPoint();
    }

    public WinningResult modifyWinningResult(Dealer dealer) {
        if (cards.isBust()) {
            return WinningResult.LOSE;
        }
        if (dealer.cards.isBust()) {
            return WinningResult.WIN;
        }
        return bothNotBustCase(dealer);
    }

    private WinningResult bothNotBustCase(Dealer dealer) {
        if (calculatePoint() > dealer.calculatePoint()) {
            return WinningResult.WIN;
        }
        if (calculatePoint() == dealer.calculatePoint()) {
            return checkBlackJackCase(dealer);
        }
        return WinningResult.LOSE;
    }

    private WinningResult checkBlackJackCase(Dealer dealer) {
        if (cards.isBlackJack() && !dealer.cards.isBlackJack()) {
            return WinningResult.WIN;
        }
        if (!cards.isBlackJack() && dealer.cards.isBlackJack()) {
            return WinningResult.LOSE;
        }
        return WinningResult.DRAW;
    }
}
