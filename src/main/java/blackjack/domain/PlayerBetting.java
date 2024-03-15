package blackjack.domain;

import blackjack.domain.participant.ParticipantName;

public class PlayerBetting {
    private final ParticipantName name;
    private final int betting;

    public PlayerBetting(final String name, final int betting) {
        validateBetting(betting);
        this.name = new ParticipantName(name);
        this.betting = betting;
    }

    private void validateBetting(final int betting) {
        if (betting <=0 ) {
            throw new IllegalArgumentException("배팅 금액은 0원보다 커야합니다.");
        }
    }

    public boolean isName(final ParticipantName otherName) {
        return this.name.equals(otherName);
    }

    public ParticipantName getName() {
        return name;
    }

    public int applyWinstatus(final double betMultiplier) {
        return (int) (betting * betMultiplier);
    }
}
