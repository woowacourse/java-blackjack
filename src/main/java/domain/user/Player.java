package domain.user;

import domain.result.WinningResult;

public class Player extends User {

    WinningResult winningResult;

    public Player(String name) {
        super(name);
    }

    @Override
    public boolean isAvailableToDraw() {
        return !cards.isBust() && !cards.isBlackJack() && !cards.isBlackJackPoint();
    }

    //todo: 내부 구현 내용 개선
    public WinningResult win(Dealer dealer) {
        if (cards.isBust()) {
            return winningResult = WinningResult.LOSE;
        }
        if (cards.isBlackJack()) {
            if (dealer.cards.isBlackJack()) {
                return winningResult = WinningResult.DRAW;
            }
            return winningResult = WinningResult.WIN;
        }
        if (dealer.cards.isBust()) {
            return winningResult = WinningResult.WIN;
        }
        if (calculatePoint() > dealer.calculatePoint()) {
            return winningResult = WinningResult.WIN;
        }
        if (calculatePoint() == dealer.calculatePoint()) {
            return winningResult = WinningResult.DRAW;
        }
        return winningResult = WinningResult.LOSE;
    }

    @Override
    public String getTotalWinningResult() {
        return name + ": " + winningResult.getResult();
    }

    public WinningResult getWinningResult() {
        return winningResult;
    }
}
