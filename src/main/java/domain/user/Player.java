package domain.user;

import domain.WinningResult;

public class Player extends User {

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
            return WinningResult.LOSE;
        }
        if (isBlackJack()) {
            if (dealer.isBlackJack()) {
                return WinningResult.DRAW;
            }
            return WinningResult.WIN;
        }
        if (dealer.isBust()) {
            return WinningResult.WIN;
        }
        if (calculatePointAccordingToHasAce() > dealer.calculatePointAccordingToHasAce()) {
            return WinningResult.WIN;
        }
        if (calculatePointAccordingToHasAce() == dealer.calculatePointAccordingToHasAce()) {
            return WinningResult.DRAW;
        }
        return WinningResult.LOSE;
    }
}
