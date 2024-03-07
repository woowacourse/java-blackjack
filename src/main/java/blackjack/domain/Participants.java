package blackjack.domain;

import static java.util.stream.Collectors.toMap;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Participants {

    private final List<Participant> participants;

    public Participants(final List<Participant> participants) {
        validateDuplicate(participants);
        this.participants = participants;
    }

    public static Participants from(final List<String> playerNames) {
        final List<Participant> participants = playerNames.stream()
                .map(Participant::from)
                .toList();

        return new Participants(participants);
    }

    private void validateDuplicate(final List<Participant> participants) {
        if (Set.copyOf(participants).size() != participants.size()) {
            throw new IllegalArgumentException("중복된 이름의 참여자는 참여할 수 없습니다.");
        }
    }

    public void divideCard(final List<Card> cards) {
        for (int i = 0; i < cards.size(); i++) {
            Participant participant = participants.get(i / 2);
            participant.addCard(cards.get(i));
        }
    }

    public void addCardTo(final ParticipantName name, final Card card) {
        final Participant findedParticipant = findParticipant(name);
        findedParticipant.addCard(card);
    }

    public Map<ParticipantName, WinStatus> determineWinStatus(final Score dealerScore) {
        final Map<ParticipantName, WinStatus> playersWinStatus = new LinkedHashMap<>();

        for (Participant participant : participants) {
            playersWinStatus.put(participant.getName(), WinStatus.of(dealerScore, participant.calculate()));
        }

        return playersWinStatus;
    }

    public int count() {
        return participants.size();
    }

    public boolean isNotDead(final ParticipantName name) {
        final Participant participant = findParticipant(name);
        return participant.isNotDead();
    }

    private Participant findParticipant(final ParticipantName name) {
        return participants.stream()
                .filter(participant -> participant.isName(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("없는 참가자 입니다."));
    }

    public Hands getCardsOf(final ParticipantName name) {
        final Participant findedParticipant = findParticipant(name);
        return findedParticipant.getHands();
    }

    public Map<ParticipantName, Hands> getPlayerHands() {
        return participants.stream()
                .collect(toMap(Participant::getName,
                        Participant::getHands,
                        (v1, v2) -> v1,
                        LinkedHashMap::new));
    }

    public Map<ParticipantName, Score> getPlayerScores() {
        return participants.stream()
                .collect(toMap(Participant::getName,
                        Participant::calculate,
                        (v1, v2) -> v1,
                        LinkedHashMap::new));
    }

    public List<ParticipantName> getNames() {
        return participants.stream()
                .map(Participant::getName)
                .toList();
    }
}
