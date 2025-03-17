package view;

import participant.Bet;
import participant.Name;
import participant.Participant;
import participant.Participants;

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

    public String getFinalProfitHeader() {
        return String.format("%n## 최종 수익%n");
    }

    public String getFinalProfit(final Name name, final Bet bet) {
        return String.format("%s: %s%n", name, bet);
    }
}
