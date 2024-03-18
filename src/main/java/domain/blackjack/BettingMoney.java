package domain.blackjack;

record BettingMoney(int rawBattingMoney) {
    BettingMoney {
        if (rawBattingMoney < 0) {
            throw new IllegalArgumentException("배팅 금액은 음수일 수 없습니다.");
        }
    }

    EarningMoney calculateEarningMoney(GameResult gameResult) {
        double earnMoneyRate = gameResult.getEarnMoneyRate();
        return new EarningMoney((int) (rawBattingMoney * earnMoneyRate));
    }
}
