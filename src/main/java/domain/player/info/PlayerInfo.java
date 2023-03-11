package domain.player.info;

public class PlayerInfo {

    private final Name name;
    private final BetAmount betAmount;

    private PlayerInfo(PlayerInfoBuilder playerInfoBuilder) {
        this.name = playerInfoBuilder.name;
        this.betAmount = playerInfoBuilder.betAmount;
    }

    public static class PlayerInfoBuilder {

        private final Name name;
        private BetAmount betAmount;

        public PlayerInfoBuilder(final String name) {
            this.name = Name.of(name);
        }

        public PlayerInfoBuilder setBetAmount(int betAmount) {
            this.betAmount = BetAmount.from(betAmount);
            return this;
        }

        public PlayerInfo build() {
            return new PlayerInfo(this);
        }

        public String getName() {
            return name.getName();
        }
    }

    public String getName() {
        return name.getName();
    }

    public int getBetAmount() {
        return betAmount.getBetAmount();
    }
}
