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
        List<Participant> participants = playerNames.stream()
                .map(Participant::from)
                .toList();

        return new Participants(participants);
    }

    private void validateDuplicate(final List<Participant> participants) {
        if (Set.copyOf(participants).size() != participants.size()) {
            throw new IllegalArgumentException("중복된 이름의 참여자는 참여할 수 없습니다.");
        }
    }

    public Map<ParticipantsName, WinStatus> determineWinStatus(final Score dealerScore) {
        Map<ParticipantsName, WinStatus> playersWinStatus = new LinkedHashMap<>();

        for (Participant participant : participants) {
            playersWinStatus.put(participant.getName(), WinStatus.of(dealerScore, participant.calculate()));
        }
        return playersWinStatus;
    }

    public void divideCard(final List<Card> cards) {
        for (int i = 0; i < cards.size(); i++) {
            // TODO: 과연 이해가 되는가?
            Participant participant = participants.get(i / 2);
            participant.addCard(cards.get(i));
        }
    }

    public int count() {
        return participants.size();
    }

    public Map<ParticipantsName, Hands> getPlayerHands() {
        return participants.stream()
                .collect(toMap(Participant::getName,
                        Participant::getHands,
                        (v1, v2) -> v1,
                        LinkedHashMap::new));
    }

    public Map<ParticipantsName, Score> getPlayerScores() {
        return participants.stream()
                .collect(toMap(Participant::getName,
                        Participant::calculate,
                        (v1, v2) -> v1,
                        LinkedHashMap::new));
    }

    public List<ParticipantsName> getNames() {
        return participants.stream()
                .map(Participant::getName)
                .toList();
    }

    public void addCardTo(final ParticipantsName name, final Card card) {
        Participant findedParticipant = findParticipant(name);

        findedParticipant.addCard(card);
    }

    public boolean isAlive(final ParticipantsName name) {
        Participant findedParticipant = findParticipant(name);

        return findedParticipant.getStatus().isAlive();
    }

    private Participant findParticipant(final ParticipantsName name) {
        return participants.stream()
                .filter(participant -> participant.isName(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("없는 참가자 입니다."));
    }

    public boolean isNotBlackjack(final ParticipantsName name) {
        Participant participant = findParticipant(name);
        return !participant.getStatus().isBlackjack();
    }

    public Hands getCardsOf(final ParticipantsName name) {
        Participant findedParticipant = findParticipant(name);
        return findedParticipant.getHands();
    }
}
