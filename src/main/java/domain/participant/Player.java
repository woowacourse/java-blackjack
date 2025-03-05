package domain.participant;

public class Player implements Participant {
    private final String nickname;

    private Player(final String nickname) {
        this.nickname = nickname;
    }

    public static Player from(final String nickname) {
        return new Player(nickname);
    }
}
