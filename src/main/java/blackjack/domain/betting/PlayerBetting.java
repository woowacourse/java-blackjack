package blackjack.domain.betting;

import blackjack.domain.participant.ParticipantName;
import blackjack.domain.result.WinStatus;

public class PlayerBetting {
    private final ParticipantName name;
    private final int betting;

    private PlayerBetting(final ParticipantName name, final int betting) {
        this.name = name;
        this.betting = betting;
    }

    PlayerBetting(final String name, final int betting) {
        this(new ParticipantName(name), betting);
    }

    public static PlayerBetting create(final String name, final int betting) {
        validateInitialBetting(betting);
        return new PlayerBetting(new ParticipantName(name), betting);
    }

    private static void validateInitialBetting(final int betting) {
        if (betting <= 0) {
            throw new IllegalArgumentException("배팅 금액은 0원보다 커야합니다.");
        }
    }

    public PlayerBetting applyWinStatus(final WinStatus winStatus) {
        int bettingResult = (int) (this.betting * winStatus.getBetMultiplier());
        return new PlayerBetting(this.name, bettingResult);
    }

    public boolean isName(final ParticipantName otherName) {
        return this.name.equals(otherName);
    }

    public ParticipantName getName() {
        return name;
    }

    public int getBetting() {
        return betting;
    }
}
