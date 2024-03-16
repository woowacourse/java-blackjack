package blackjack.domain.betting;

import blackjack.domain.participant.ParticipantName;
import blackjack.domain.result.WinStatus;

public class PlayerBetting {
    private final ParticipantName name;
    private final BettingMoney bettingMoney;

    private PlayerBetting(final ParticipantName name, final BettingMoney bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    PlayerBetting(final String name, final int bettingMoney) {
        this(new ParticipantName(name), new BettingMoney(bettingMoney));
    }

    public static PlayerBetting create(final String name, final int betting) {
        validateInitialBetting(betting);
        return new PlayerBetting(name, betting);
    }

    private static void validateInitialBetting(final int betting) {
        if (betting <= 0) {
            throw new IllegalArgumentException("배팅 금액은 0원보다 커야합니다.");
        }
    }

    public PlayerBetting applyWinStatus(final WinStatus winStatus) {
        BettingMoney bettingMoney = this.bettingMoney.multiplyWith(winStatus.getBetMultiplier());
        return new PlayerBetting(this.name, bettingMoney);
    }

    public boolean equalsName(final ParticipantName otherName) {
        return this.name.equals(otherName);
    }

    public ParticipantName getName() {
        return name;
    }

    public int getBetting() {
        return bettingMoney.getMoeney();
    }
}
