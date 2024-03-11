package model.player;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
        Set<User> distinctUsers = new HashSet<>(participants);
        if (distinctUsers.size() != participants.size()) {
            throw new IllegalArgumentException("참가자들의 이름은 중복되면 안됩니다.");
        }
    }

    private void validateParticipantSize(List<Participant> participants) {
        if (participants.isEmpty()) {
            throw new IllegalArgumentException("참가자의 수는 1명 이상이어야 합니다.");
        }
    }

    public void offerCardToPlayers(CardDeck cardDeck, CardSize size) {
        for (User user : participants) {
            user.addCards(cardDeck.selectRandomCards(size));
        }
    }

    public void offerCardToParticipant(CardDeck cardDeck, User receiver, CardSize size) {
        User foundUser = participants.stream()
                .filter(player -> player.equals(receiver))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("참가자가 존재하지 않습니다."));
        foundUser.addCards(cardDeck.selectRandomCards(size));
    }

    public List<Participant> getParticipants() {
        return Collections.unmodifiableList(participants);
    }
}
