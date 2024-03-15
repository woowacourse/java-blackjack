package blackjack.domain.gamer;

public class Player extends Gamer {

    private static final int PLAYER_HIT_MAX_SCORE = 21;

    private final Name playerName;

    public Player(String playerName) {
        this.playerName = new Name(playerName);
    }

    public void initialDraw(Dealer dealer) {
        for (int i = 0; i < 2; i++) {
            receiveCard(dealer.drawCard());
        }
    }

    public String getPlayerName() {
        return playerName.getName();
    }

    @Override
    public boolean canHit() {
        return calculateTotalScore() <= PLAYER_HIT_MAX_SCORE;
    }
}
