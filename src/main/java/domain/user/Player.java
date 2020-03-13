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

    //todo: 내부 구현 내용 개선
    public WinningResult modifyWinningResult(Dealer dealer) {
        if (cards.isBust()) {
            return WinningResult.LOSE;
        }
        if (cards.isBlackJack()) {
            if (dealer.cards.isBlackJack()) {
                return WinningResult.DRAW;
            }
            return WinningResult.WIN;
        }
        if (dealer.cards.isBust()) {
            return WinningResult.WIN;
        }
        if (calculatePoint() > dealer.calculatePoint()) {
            return WinningResult.WIN;
        }
        if (calculatePoint() == dealer.calculatePoint()) {
            return WinningResult.DRAW;
        }
        return WinningResult.LOSE;
    }
}
