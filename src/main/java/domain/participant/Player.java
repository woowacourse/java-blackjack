package domain.participant;

public class Player implements Participant {
    private final String nickname;

    public Player(final String nickname) {
        this.nickname = nickname;
    }
}
