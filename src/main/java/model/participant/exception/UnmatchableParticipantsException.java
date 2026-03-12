package model.participant.exception;

public class UnmatchableParticipantsException extends IllegalArgumentException {
    public UnmatchableParticipantsException(String source, String comparisonTarget) {
        super(source + "는 " + comparisonTarget + "와 승패를 판정할 수 없습니다. 플레이어는 딜러와, 딜러는 플레이어와만 가능합니다.");
    }
}
