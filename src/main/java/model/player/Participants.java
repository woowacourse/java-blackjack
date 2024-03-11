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
        validateNotDuplicatedPlayer(participants);
        validateParticipantSize(participants);
        this.participants = participants;
    }

    private void validateNotDuplicatedPlayer(List<Participant> players) {
        Set<Player> distinctPlayers = new HashSet<>(players);
        if (distinctPlayers.size() != players.size()) {
            throw new IllegalArgumentException("참가자들의 이름은 중복되면 안됩니다.");
        }
    }

    private void validateParticipantSize(List<Participant> participants) {
        if (participants.isEmpty()) {
            throw new IllegalArgumentException("참가자의 수는 1명 이상이어야 합니다.");
        }
    }

    public void offerCardToPlayers(CardDeck cardDeck, CardSize size) {
        for (Player player : participants) {
            player.addCards(cardDeck.selectRandomCards(size));
        }
    }

    public void offerCardToParticipant(CardDeck cardDeck, Player receiver, CardSize size) {
        Player foundPlayer = participants.stream()
                .filter(player -> player.equals(receiver))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("참가자가 존재하지 않습니다."));
        foundPlayer.addCards(cardDeck.selectRandomCards(size));
    }

    public List<Participant> getParticipants() {
        return Collections.unmodifiableList(participants);
    }
}
