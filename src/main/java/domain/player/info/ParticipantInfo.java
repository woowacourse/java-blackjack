package domain.player.info;

public class ParticipantInfo {

    private final Name name;
    private final BetAmount betAmount;

    private ParticipantInfo(ParticipantBuilder participantBuilder) {
        this.name = participantBuilder.name;
        this.betAmount = participantBuilder.betAmount;
    }

    public static class ParticipantBuilder {

        private final Name name;
        private BetAmount betAmount;

        public ParticipantBuilder(final String name) {
            this.name = Name.of(name);
        }

        public ParticipantBuilder setBetAmount(int betAmount) {
            this.betAmount = BetAmount.from(betAmount);
            return this;
        }

        public ParticipantInfo build() {
            return new ParticipantInfo(this);
        }

        public String getName() {
            return name.getName();
        }
    }

    public String getName() {
        return name.getName();
    }

    public int winBet(final boolean isBlackjack) {
        return betAmount.winBet(isBlackjack);
    }

    public int loseBet() {
        return betAmount.loseBet();
    }

    public int returnBet() {
        return betAmount.returnBet();
    }
}
