package domain;

import java.util.Objects;

public class GameStatus implements Comparable<GameStatus> {

    private final ParticipantStatus participantStatus;
    private final int score;

    public GameStatus(ParticipantStatus participantStatus, int score) {
        this.participantStatus = participantStatus;
        this.score = score;
    }

    public boolean isAbleToHit() {
        return participantStatus == ParticipantStatus.NOT_BUST;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GameStatus that = (GameStatus) o;
        return score == that.score && participantStatus == that.participantStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(participantStatus, score);
    }

    @Override
    public int compareTo(GameStatus o) {
        if (this.participantStatus == ParticipantStatus.BUST && o.participantStatus == ParticipantStatus.BUST) {
            return 0;
        }
        if (this.participantStatus == ParticipantStatus.BUST) {
            return -1;
        }
        if (o.participantStatus == ParticipantStatus.BUST) {
            return 1;
        }
        return Integer.compare(this.score, o.score);
    }

    @Override
    public String toString() {
        return "GameStatus{" +
            "participantStatus=" + participantStatus +
            ", score=" + score +
            '}';
    }
}
