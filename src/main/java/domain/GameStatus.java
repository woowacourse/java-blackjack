package domain;

public class GameStatus {

    private final ParticipantStatus participantStatus;
    private final int score;

    public GameStatus(ParticipantStatus participantStatus, int score) {
        this.participantStatus = participantStatus;
        this.score = score;
    }

    public boolean isAbleToHit() {
        return participantStatus == ParticipantStatus.NOT_BUST;
    }
}
