package blackjack.model.wallet;

import blackjack.model.gamer.Player;
import blackjack.model.result.Result;
import java.util.HashMap;
import java.util.Map;

public class PlayerBetInfo {

//    private final Map<Player, PlayerBetWallet> playerBetInfo = new HashMap<>();
//
//    public void registerBetAmount(Player player, int betAmount) {
//        validatePlayerAlreadySet(player);
//        PlayerBetWallet playerBetWallet = PlayerBetWallet.from(betAmount);
//        playerBetInfo.put(player, playerBetWallet);
//    }
//
//    public void registerProfitAmount(Player player, Result result) {
//        PlayerBetWallet playerBetWallet = playerBetInfo.get(player);
//        playerBetWallet.registerProfitAmount(result);
//    }
//
//    public int playerBetAmount(Player player) {
//        PlayerBetWallet playerBetWallet = playerBetInfo.get(player);
//        return playerBetWallet.getBetAmount();
//    }
//
//    public int playerProfitAmount(Player player) {
//        PlayerBetWallet playerBetWallet = playerBetInfo.get(player);
//        return playerBetWallet.getProfitAmount();
//    }
//
//    public int playerNetProfit(Player player) {
//        PlayerBetWallet playerBetWallet = playerBetInfo.get(player);
//        return playerBetWallet.calculateNetProfit();
//    }
//
//    private void validatePlayerAlreadySet(Player player) {
//        if (playerBetInfo.containsKey(player)) {
//            String playerName = player.name();
//            int betAmount = playerBetInfo.get(player).getBetAmount();
//            String errorMessage = String.format("[ERROR] 이미 배팅 금액이 등록된 플레이어 입니다. (%s 배팅 금액 : %d)",
//                    playerName,
//                    betAmount);
//            throw new IllegalArgumentException(errorMessage);
//        }
//    }
}
