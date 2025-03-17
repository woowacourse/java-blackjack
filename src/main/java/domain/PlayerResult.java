package domain;

import domain.participant.Participant;
import java.util.Objects;

public class PlayerResult {
    private final Participant player;
    private final BlackJackWinningStatus blackJackWinningStatus;

    public PlayerResult(Participant player, BlackJackWinningStatus blackJackWinningStatus) {
        this.player = player;
        this.blackJackWinningStatus = blackJackWinningStatus;
    }

    public String getPlayerName() {
        return player.getName();
    }

    public BlackJackWinningStatus getGameResult() {
        return blackJackWinningStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PlayerResult that = (PlayerResult) o;
        return Objects.equals(player, that.player) && blackJackWinningStatus == that.blackJackWinningStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(player, blackJackWinningStatus);
    }
}
