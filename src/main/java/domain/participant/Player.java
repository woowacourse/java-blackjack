package domain.participant;

public class Player extends Participant {
    private final PlayerName playerName;

    Player(final PlayerName playerName, final Hand hand) {
        super(hand);
        this.playerName = playerName;
    }

    public static Player of(final PlayerName playerName) {
        return new Player(playerName, Hand.init());
    }

    @Override
    public boolean isDrawable() {
        return hand.isNotBust() && hand.isNotMaximum();
    }

    public PlayerName getPlayerName() {
        return playerName;
    }

    public boolean isNotBust() {
        return hand.isNotBust();
    }

    public boolean isBlackJack() {
        return hand.isBlackJack();
    }
}
