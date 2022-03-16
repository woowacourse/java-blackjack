package blackJack.domain.result;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class BlackJackGameBoard {

    private final int dealerEarning;
    private final Map<String, Integer> playerEarnings;

    public BlackJackGameBoard(int dealerEarning, Map<String, Integer> playerEarnings) {
        this.dealerEarning = dealerEarning;
        this.playerEarnings = new LinkedHashMap<>(playerEarnings);
    }

    public int getDealerEarning() {
        return dealerEarning;
    }

    public Map<String, Integer> getPlayerEarnings() {
        return playerEarnings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BlackJackGameBoard)) {
            return false;
        }
        BlackJackGameBoard that = (BlackJackGameBoard) o;
        return dealerEarning == that.dealerEarning && Objects.equals(playerEarnings, that.playerEarnings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dealerEarning, playerEarnings);
    }
}
