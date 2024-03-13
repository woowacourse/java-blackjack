package blackjack.model.gamer;

public class Player extends Gamer {

    private static final int PLAYER_HIT_MAX_SCORE = 21;

    private final Name playerName;

    public Player(String playerName) {
        this.playerName = new Name(playerName);
    }

    public String getPlayerName() {
        return playerName.getName();
    }

    @Override
    public boolean canHit() {
        return calculateTotalScore() <= PLAYER_HIT_MAX_SCORE;
    }
}
