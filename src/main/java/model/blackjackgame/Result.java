package model.blackjackgame;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import model.dealer.Dealer;
import model.player.Player;
import model.player.Players;

public class Result {

    private static final String BLACKJACK_WORD = "블랙잭";
    private static final String WIN_WORD = "승";
    private static final String FAIL_WORD = "패";
    private static final String DRAW_WORD = "무";

    private final String dealerResult;
    private final Map<String, String> playerResult;

    public Result(Dealer dealer, Players players, Blackjack blackjack) {
        this.playerResult = new HashMap<>();
        createPlayersResult(dealer, players, blackjack);
        this.dealerResult = createDealerResult();
    }

    private void createPlayersResult(Dealer dealer, Players players, Blackjack blackjack) {
        for (Player player : players.getPlayers()) {
            playerResult.put(player.getName(), calculatePlayerResult(dealer, player, blackjack));
        }
    }

    private String calculatePlayerResult(Dealer dealer, Player player, Blackjack blackjack) {
        if (blackjackCase(player, blackjack) && !blackjack.getDealer()) {
            return BLACKJACK_WORD;
        }
        if (calculatePlayerWin(dealer, player)) {
            return WIN_WORD;
        }
        if (blackjack.getDealer() && !blackjackCase(player, blackjack) || calculatePlayerFail(dealer, player)) {
            return FAIL_WORD;
        }
        return DRAW_WORD;
    }

    private boolean blackjackCase(Player player, Blackjack blackjack) {
        boolean playerBlackjack = false;
        for (Entry<Player, Boolean> entrySet : blackjack.getPlayersStatus().entrySet()) {
            if (entrySet.getKey().getName().equals(player.getName())) {
                playerBlackjack = entrySet.getValue();
            }
        }
        return playerBlackjack;
    }

    private boolean calculatePlayerWin(Dealer dealer, Player player) {
        return dealer.totalNumber() < player.totalNumber() && player.isNotBust();
    }

    private boolean calculatePlayerFail(Dealer dealer, Player player) {
        return dealer.totalNumber() > player.totalNumber() || player.isBust();
    }

    private String createDealerResult() {
        StringBuilder result = new StringBuilder();
        appendResult(result, countOccurrences(FAIL_WORD), WIN_WORD);
        appendResult(result, countOccurrences(WIN_WORD), FAIL_WORD);
        appendResult(result, countOccurrences(DRAW_WORD), DRAW_WORD);

        return result.toString().trim();
    }

    private int countOccurrences(String resultType) {
        return Collections.frequency(playerResult.values(), resultType);
    }

    private void appendResult(StringBuilder result, int count, String resultType) {
        if (count > 0) {
            result.append(count).append(resultType).append(" ");
        }
    }

    public Map<String, String> getPlayerResult() {
        return playerResult;
    }

    public String getDealerResult() {
        return dealerResult;
    }
}
