package blackjack.model.wallet;

import blackjack.model.gamer.Player;

public class DealerBetWallet {

    private final int playersBetAmount;
    private int payoutAmount;

    public DealerBetWallet(int playersBetAmount) {
        validateNotNegative(playersBetAmount);
        this.playersBetAmount = playersBetAmount;
        this.payoutAmount = 0;
    }

    public void registerPayoutAmount(Player player) {
        payoutAmount += player.profitAmount();
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
