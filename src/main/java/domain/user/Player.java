package domain.user;

import domain.WinningResult;

public class Player extends User {

    WinningResult winningResult;

    public Player(String name) {
        super(name);
    }

    @Override
    public boolean isAvailableToDraw() {
        return !isBust() && !isBlackJack() && !isBlackJackPoint();
    }

    //todo: 내부 구현 내용 개선
    public WinningResult win(Dealer dealer) {
        if (isBust()) {
            return winningResult = WinningResult.LOSE;
        }
        if (isBlackJack()) {
            if (dealer.isBlackJack()) {
                return winningResult = WinningResult.DRAW;
            }
            return winningResult = WinningResult.WIN;
        }
        if (dealer.isBust()) {
            return winningResult = WinningResult.WIN;
        }
        if (calculatePointAccordingToHasAce() > dealer.calculatePointAccordingToHasAce()) {
            return winningResult = WinningResult.WIN;
        }
        if (calculatePointAccordingToHasAce() == dealer.calculatePointAccordingToHasAce()) {
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
