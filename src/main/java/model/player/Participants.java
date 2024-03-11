package model.player;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import model.card.CardSize;
import model.card.CardDeck;

public class Participants {

    private final List<Participant> participants;

    public Participants(List<Participant> participants) {
        validateNotDuplicatedParticipant(participants);
        validateParticipantSize(participants);
        this.participants = participants;
    }

    private void validateNotDuplicatedParticipant(List<Participant> participants) {
        Set<User> duplicates = participants.stream()
                .filter(n -> Collections.frequency(participants, n) > 1)
                .collect(Collectors.toSet());

        if (!duplicates.isEmpty()) {
            String duplicatedNames = duplicates.stream()
                    .map(User::getName)
                    .collect(Collectors.joining(","));
            throw new IllegalArgumentException("중복된 이름("+ duplicatedNames +")가 있습니다, 참가자들의 이름은 중복되면 안됩니다.");
        }
    }

    private void validateParticipantSize(List<Participant> participants) {
        if (participants.isEmpty()) {
            throw new IllegalArgumentException("참가자의 수는 1명 이상이어야 합니다.");
        }
    }

    public void offerCardToParticipant(CardDeck cardDeck, Participant receiver, CardSize size) {
        User foundUser = participants.stream()
                .filter(player -> player.equals(receiver))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("참가자(" + receiver.getName() + ")가 존재하지 않습니다."));
        foundUser.addCards(cardDeck.selectRandomCards(size));
    }

    public List<String> findParticipantsName() {
        return participants.stream()
                .map(participant -> participant.name).toList();
    }

    public List<Participant> getParticipants() {
        return Collections.unmodifiableList(participants);
    }
}
