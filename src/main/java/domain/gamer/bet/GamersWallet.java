package domain.gamer.bet;

import domain.gamer.Player;

import java.util.Collections;
import java.util.List;

public class GamersWallet {

    private final List<GamerWallet> gamerWallets;

    public GamersWallet(List<GamerWallet> gamerWallets) {
        this.gamerWallets = gamerWallets;
    }

    public int findMoneyByPlayer(Player player) {
        return gamerWallets.stream()
                .filter(gamerWallet -> gamerWallet.getPlayer().equals(player))
                .map(GamerWallet::getMoney)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 일치하는 플레이어가 없습니다."));
    }

    public GamerWallet findGamerWalletByPlayer(Player player) {
        return gamerWallets.stream()
                .filter(gamerWallet -> gamerWallet.getPlayer().equals(player))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 일치하는 플레이어가 없습니다."));
    }


    public void applyProfitToGamer(Player player, Money profitAppliedMoney) {
        GamerWallet gamerWallet = findGamerWalletByPlayer(player);
        gamerWallet.applyBetProfit(profitAppliedMoney);
    }

    public int sumAllPlayerProfit() {
        return gamerWallets.stream()
                .mapToInt(GamerWallet::getMoney)
                .sum();
    }

    public List<GamerWallet> getGamerWallets() {
        return Collections.unmodifiableList(gamerWallets);
    }
}
