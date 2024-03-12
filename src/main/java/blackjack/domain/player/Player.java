package blackjack.domain.player;

import blackjack.domain.Result;
import blackjack.domain.card.Hand;

import java.util.Objects;

public class Player extends Participant {

    private static final int HIT_THRESHOLD = 21;

    private final PlayerName playerName;

    public Player(PlayerName playerName) {
        this(new Hand(), playerName);
    }

    public Player(Hand hand, PlayerName playerName) {
        super(hand);
        this.playerName = playerName;
    }

    @Override
    public boolean canHit() {
        return calculateHandTotal() <= HIT_THRESHOLD;
    }

    public Result judge(Dealer dealer) {
        int playerHandTotal = this.calculateHandTotal();
        int dealerHandTotal = dealer.calculateHandTotal();

        if (playerHandTotal > 21) {
            return Result.createLossResult();
        }
        if (dealerHandTotal > 21) {
            return Result.createWinResult();
        }
        return compare(playerHandTotal, dealerHandTotal);
    }

    private Result compare(int playerHandTotal, int dealerHandTotal) {
        if (playerHandTotal > dealerHandTotal) {
            return Result.createWinResult();
        }
        return Result.createLossResult();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        return Objects.equals(playerName, player.playerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerName);
    }

    public String getPlayerName() {
        return playerName.getValue();
    }
}
