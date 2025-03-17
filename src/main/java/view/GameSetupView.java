package view;

import java.util.List;
import participant.Name;
import participant.Participant;
import participant.Participants;

public final class GameSetupView extends BlackjackView {
    public String getInputNameGuide() {
        return String.format("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)%n");
    }

    public String getBettingGuide(final Name playerName) {
        return String.format("%n%s의 배팅 금액은?%n", playerName);
    }

    public String getSetupResult(final Participants participants) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("%n%s에게 2장을 나누었습니다.%n", getParticipantNames(participants)));
        for (Participant participant : participants.getParticipants()) {
            stringBuilder.append(getParticipantCards(participant.getName(), participant.openInitialCard()));
            stringBuilder.append(getEmptyLine());
        }
        return stringBuilder.toString();
    }

    private String getParticipantNames(final Participants participants) {
        List<String> allPlayerNames = participants.getParticipants().stream()
                .map(Participant::getName)
                .map(Name::toString)
                .toList();
        return String.join(", ", allPlayerNames);
    }
}
