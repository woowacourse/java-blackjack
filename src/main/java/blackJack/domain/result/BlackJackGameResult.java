package blackJack.domain.result;

import blackJack.domain.participant.Player;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class BlackJackGameResult {

    private static final int DEFAULT_EARNING = 0;

    private final Map<Player, OutCome> outComes = new LinkedHashMap<>();

    public void add(Player player, OutCome outCome) {
        outComes.put(player, outCome);
    }

    public BlackJackGameBoard calculateEarning() {
        final Map<String, Integer> playerEarnings = new LinkedHashMap<>();
        int dealerEarning = DEFAULT_EARNING;

        for (Entry<Player, OutCome> player : outComes.entrySet()) {
            final int playerEarning = player.getValue().calculateEarning(player.getKey().getBet());
            playerEarnings.put(player.getKey().getName(), playerEarning);
            dealerEarning += player.getValue().calculateReverseEarning(playerEarning);
        }

        return new BlackJackGameBoard(dealerEarning, playerEarnings);
    }
}
