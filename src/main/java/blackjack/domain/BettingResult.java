package blackjack.domain;

import blackjack.domain.participant.ParticipantName;
import blackjack.domain.result.WinStatus;
import java.util.Objects;

public class BettingResult {

    private final ParticipantName name;
    private final int betting;

    public BettingResult(final ParticipantName name, final int betting) {
        this.name = name;
        this.betting = betting;
    }

    public static BettingResult of(final WinStatus winStatus, final PlayerBetting playerBetting) {
        int betting = applyWinstatus(winStatus, playerBetting);
        return new BettingResult(playerBetting.getName(), betting);
    }

    private static int applyWinstatus(final WinStatus winStatus, final PlayerBetting playerBetting) {
        return playerBetting.applyWinstatus(winStatus.getBetMultiplier());
    }

    public ParticipantName getName() {
        return name;
    }

    public int getBetting() {
        return betting;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final BettingResult that = (BettingResult) o;
        return betting == that.betting && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, betting);
    }
}
