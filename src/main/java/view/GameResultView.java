package view;

import model.participant.Participant;
import model.participant.Participants;

public final class GameResultView extends BlackjackView {
    public String getFinalScores(final Participants participants) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getEmptyLine());
        for (Participant participant : participants.getParticipants()) {
            stringBuilder.append(getParticipantCards(participant.getName(), participant.getCards()));
            stringBuilder.append(getFinalScore(participant));
            stringBuilder.append(getEmptyLine());
        }
        return stringBuilder.toString();
    }

    private String getFinalScore(final Participant participant) {
        return String.format(" - 결과: %s", participant.getScore());
    }
}
