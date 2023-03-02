package domain;

public class GameStatus {

    private ParticipantStatus participantStatus;
    private int score;

    public GameStatus(ParticipantStatus participantStatus, int score) {
        this.participantStatus = participantStatus;
        this.score = score;
    }
}
