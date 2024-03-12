package blackjack.model.wallet;

import blackjack.model.gamer.Player;

public class DealerBetInfo {

    private final int playersBetAmount;
    private int payoutAmount;

    private DealerBetInfo(int playersBetAmount) {
        validateNotNegative(playersBetAmount);
        this.playersBetAmount = playersBetAmount;
        this.payoutAmount = 0;
    }

    public static DealerBetInfo from(int playersBetAmount) {
        return new DealerBetInfo(playersBetAmount);
    }

    public void registerPayoutAmount(PlayerBetInfo playerBetInfo, Player player) {
        payoutAmount += playerBetInfo.playerProfitAmount(player);
    }

    public int calculateNetProfit() {
        return playersBetAmount - payoutAmount;
    }

    private void validateNotNegative(int playersBetAmount) {
        if (playersBetAmount < 0) {
            throw new IllegalArgumentException("[ERROR] 플레이어 배팅 금액은 음수일 수 없습니다.");
        }
    }

    public int getPayoutAmount() {
        return payoutAmount;
    }
}
