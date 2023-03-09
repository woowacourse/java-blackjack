package domain.board;

import domain.PlayerStatus;
import domain.TurnAction;
import domain.user.Player;
import java.util.Objects;

public class PlayerBoard {

    public static final int BLACK_JACK_POINT = 21;
    private final Player player;
    private PlayerStatus status;

    public PlayerBoard(Player player, PlayerStatus status) {
        this.player = player;
        this.status = status;
    }

    public void update(TurnAction turnAction) {
        if (turnAction == TurnAction.STAND) {
            status = PlayerStatus.STAND;
            return;
        }
        int point = player.getPoint();
        status = getResultByScore(point);
    }

    private PlayerStatus getResultByScore(int point) {
        if (point > BLACK_JACK_POINT) {
            return PlayerStatus.BUST;
        }
        if (point == BLACK_JACK_POINT) {
            return PlayerStatus.BLACK_JACK;
        }
        return PlayerStatus.HIT_ABLE;
    }

    public boolean isWaiting() {
        return status == PlayerStatus.HIT_ABLE;
    }

    public Player getPlayer() {
        return player;
    }

    public int getPoint() {
        return player.getPoint();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PlayerBoard that = (PlayerBoard) o;
        return player.equals(that.player) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(player, status);
    }
}
