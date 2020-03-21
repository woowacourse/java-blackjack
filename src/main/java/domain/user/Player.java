package domain.user;

import domain.card.Cards;
import domain.result.Result;

public class Player extends User {
    private Name name;
    private int betAmount;

    public Player(String name, int betAmount) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("이름이 비어있습니다.");
        }
        if (betAmount < 0) {
            throw new IllegalArgumentException("배팅 금액이 음수일 수 없습니다.");
        }
        this.name = new Name(name);
        this.betAmount = betAmount;
    }

    @Override
    public boolean canReceiveCard() {
        return this.isSmallerThan(Cards.BLACKJACK_SCORE);
    }

    public int calculateRevenue(Dealer dealer) {
        return this.calculateResult(dealer).revenueOf(this.betAmount);
    }

    public Result calculateResult(Dealer dealer) {
        if (isDraw(dealer)) {
            return Result.DRAW;
        }
        if (isPlayerLose(dealer)) {
            return Result.LOSE;
        }
        if (isBlackJack()) {
            return Result.BLACKJACK_WIN;
        }
        return Result.WIN;
    }

    private boolean isDraw(Dealer dealer) {
        boolean isBothOverBlackJack = dealer.isLargerThan(Cards.BLACKJACK_SCORE)
            && this.isLargerThan(Cards.BLACKJACK_SCORE);

        return isBothOverBlackJack || dealer.isScoreSame(this.getTotalScore());
    }

    private boolean isPlayerLose(Dealer dealer) {
        /*
         * 딜러는 파산이 아닐 때
         * 플레이어가 파산했거나, 딜러가 플레이어보다 점수가 높으면 짐.
         * */
        return !dealer.isLargerThan(Cards.BLACKJACK_SCORE)
            && (this.isLargerThan(Cards.BLACKJACK_SCORE)
            || dealer.isLargerThan(this.getTotalScore()));
    }

    public String getName() {
        return name.getName();
    }
}
