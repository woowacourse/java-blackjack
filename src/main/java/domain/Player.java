package domain;

public class Player extends BlackjackParticipant {

    public Player(String name) {
        super(name);
        validatePlayerName();
    }
}
