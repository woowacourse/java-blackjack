package player;

import card.Card;
import card.Deck;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Participants {
    private static final int MAX_SIZE = 5;

    private final List<Participant> participants;

    public Participants(List<Participant> participants) {
        validateSize(participants);
        validateUniqueNames(participants);
        this.participants = participants;
    }

    public void distributeInitialCards(Deck deck) {
        for (Participant participant : participants) {
            participant.receiveInitialCards(deck);
        }
    }

    public Map<String, List<Card>> openInitialCards() {
        Map<String, List<Card>> initialCards = new LinkedHashMap<>();
        participants.forEach(participant -> initialCards.put(participant.getName(), participant.openInitialCards()));

        return initialCards;
    }

    public Participant getParticipantByName(String name) {
        return participants.stream()
                .filter(participant -> Objects.equals(participant.getName(), name))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(name + ": 존재하지 않는 플레이어 이름입니다."));
    }

    public Map<String, Integer> mapToNameAndSum() {
        Map<String, Integer> sumResult = new LinkedHashMap<>();
        for (Participant participant : participants) {
            sumResult.put(participant.getName(), participant.computeOptimalSum());
        }
        return sumResult;
    }

    private void validateSize(List<Participant> participants) {
        if (participants.isEmpty() || participants.size() > MAX_SIZE) {
            throw new IllegalArgumentException("참여자 수는 딜러 포함 최소 2인 이상 최대 6인 이하여야 합니다.");
        }
    }

    private void validateUniqueNames(List<Participant> participants) {
        Set<String> names = participants.stream()
                .map(Participant::getName)
                .collect(Collectors.toSet());

        if (names.size() != participants.size()) {
            throw new IllegalArgumentException("참여자 이름은 중복될 수 없습니다.");
        }

        if (names.contains("딜러")) {
            throw new IllegalArgumentException("참여자 이름은 딜러가 될 수 없습니다.");
        }
    }

    public List<Participant> getParticipants() {
        return participants;
    }
}
