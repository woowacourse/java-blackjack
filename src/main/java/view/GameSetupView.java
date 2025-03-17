package view;

import java.util.List;
import java.util.stream.Collectors;
import participant.Name;
import participant.Participant;

public final class GameSetupView extends BlackjackView {
    public String getInputNameGuide() {
        return String.format("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)%n");
    }

    public String getBettingGuide(final Name playerName) {
        return String.format("%n%s의 배팅 금액은?%n", playerName);
    }

    public String getSetupHeader(final Name dealerName, final List<Name> playerNames) {
        String names = playerNames.stream()
                .map(Name::toString)
                .collect(Collectors.joining(", "));
        return String.format("%n%s, %s에게 2장을 나누었습니다.%n", dealerName, names);
    }

    public String getSetupResult(final Participant participant) {
        String participantCards = getParticipantCards(participant.getName(), participant.openInitialCard());
        return String.format("%s%n", participantCards);
    }
}
