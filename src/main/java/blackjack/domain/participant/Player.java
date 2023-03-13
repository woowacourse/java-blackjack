package blackjack.domain.participant;

public class Player extends Participant {

    private static final int BLACKJACK_SCORE = 21;

    private final PlayerInfo playerInfo;

    Player(final String name, final int bettingMoney) {
        playerInfo = new PlayerInfo(name, bettingMoney);
    }

    @Override
    public boolean isDrawable() {
        final int currentScore = currentScore();
        return currentScore < BLACKJACK_SCORE;
    }

    public String getName() {
        return playerInfo.getName().getValue();
    }

    boolean hasName(final String playerName) {
        return playerInfo.getName().getValue()
                .equals(playerName);
    }

    public int calculateProfit(final double profit) {
        return playerInfo.getBettingMoney().profit(profit);
    }
}
