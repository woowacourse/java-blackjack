package view;

import java.util.List;
import model.card.Card;
import model.card.Rank;
import model.card.Suit;
import model.participant.Name;
import model.participant.Participant;
import model.participant.Participants;

public final class GameSetupView {
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
            stringBuilder.append(getParticipantInitialCards(participant));
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

    private String getParticipantInitialCards(final Participant participant) {
        Name name = participant.getName();
        List<Card> cards = participant.openInitialCard();
        List<String> cardNotations = cards.stream()
                .map(this::getCardNotation)
                .toList();
        return String.format("%s: %s%n", name, String.join(", ", cardNotations));
    }

    private String getCardNotation(final Card card) {
        Rank rank = card.getRank();
        Suit suit = card.getSuit();
        return rank.getName() + suit.getName();
    }
}
