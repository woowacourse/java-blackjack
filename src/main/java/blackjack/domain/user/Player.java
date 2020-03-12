package blackjack.domain.user;

import blackjack.domain.Result;

public class Player extends User {
    public Player(String name) {
        super(name);
    }

    @Override
    public String showInitialCardInfo() {
        return super.showCardInfo();
    }

    public Result compareScore(Dealer dealer) {
        if (isDraw(dealer)) {
            return Result.DRAW;
        }
        if (isLose(dealer)) {
            return Result.LOSE;
        }
        if (isWin(dealer)) {
            return Result.WIN;
        }
        throw new RuntimeException("승패를 계산할 수 없습니다.");
    }

    private boolean isDraw(Dealer dealer) {
        if (dealer.isBusted() && this.isBusted()) {
            return true;
        }
        return dealer.getTotalScore() == this.getTotalScore();
    }

    private boolean isLose(Dealer dealer) {
        if (!dealer.isBusted() && this.isBusted()) {
            return true;
        }
        return dealer.getTotalScore() > this.getTotalScore();
    }

    private boolean isWin(Dealer dealer) {
        if (dealer.isBusted() && !this.isBusted()) {
            return true;
        }
        return dealer.getTotalScore() < this.getTotalScore();
    }

}
