package domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Judge {
    private static final int BLACKJACK_NUMBER = 21;
    private static final int BLACKJACK_HAND_SIZE = 2;
    private final Map<Player, WinningStatus> playerResults;

    public Judge(Dealer dealer, List<Player> players){
        playerResults = new HashMap<>();
        for(Player player: players){
            playerResults.put(player, calculateWinningStatus(dealer, player));
        }
    }

    private WinningStatus calculateWinningStatus(Dealer dealer, Player player){
        WinningStatus blackJackResult = resolveBlackJack(player, dealer);
        if(blackJackResult != null){
            return blackJackResult;
        }
        WinningStatus burstResult = resolveBurst(player, dealer);
        if(burstResult != null){
            return burstResult;
        }
        return compareResult(player, dealer);
    }

    private boolean isBlackJack(Player player){
        return player.getHandSize() == BLACKJACK_HAND_SIZE && player.getScore() == BLACKJACK_NUMBER;
    }

    private boolean isBlackJack(Dealer dealer){
        return dealer.getHandSize() == BLACKJACK_HAND_SIZE && dealer.getScore() == BLACKJACK_NUMBER;
    }

    private WinningStatus resolveBlackJack(Player player, Dealer dealer){
        boolean playerBlackJack = isBlackJack(player);
        boolean dealerBlackJack = isBlackJack(dealer);

        if (playerBlackJack && dealerBlackJack) {
            return WinningStatus.DRAW;
        }
        if (playerBlackJack) {
            return WinningStatus.BLACKJACK_WIN;
        }
        return null;
    }

    private WinningStatus resolveBurst(Player player, Dealer dealer){
        if (player.isBurst()) {
            return WinningStatus.LOSE;
        }
        if (dealer.isBurst()) {
            return WinningStatus.WIN;
        }
        return null;
    }

    private WinningStatus compareResult(Player player, Dealer dealer){
        if (player.getScore() > dealer.getScore()) {
            return WinningStatus.WIN;
        }
        if (player.getScore() == dealer.getScore()) {
            return WinningStatus.DRAW;
        }
        return WinningStatus.LOSE;
    }

    public int judgeDealerWinCount(){
        return (int)playerResults.values().stream()
                .filter(status -> status == WinningStatus.LOSE)
                .count();
    }

    public int judgeDealerLoseCount(){
        return (int)playerResults.values().stream()
                .filter(status -> status == WinningStatus.WIN)
                .count();
    }

    public WinningStatus getPlayerResult(Player player){
        return playerResults.get(player);
    }

    public Map<Player, WinningStatus> getPlayerResults() {
        return new HashMap<>(playerResults);
    }
}
