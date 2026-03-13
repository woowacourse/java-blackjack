package domain.result;


import domain.participant.ParticipantName;

public record PlayerWinningResult(
        ParticipantName participantName,
        WinningStatus winningStatus) {
}
