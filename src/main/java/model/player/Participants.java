package model.player;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import model.card.Cards;

public class Participants {

    private final List<Participant> participants;

    public Participants(List<Participant> participants) {
        validateNotDuplicatedPlayer(participants);
        this.participants = participants;
    }

    private void validateNotDuplicatedPlayer(List<Participant> players) {
        Set<Player> distinctPlayers = new HashSet<>(players);
        if (distinctPlayers.size() != players.size()) {
            throw new IllegalArgumentException("참가자들의 이름은 중복되면 안됩니다.");
        }
    }

    public void offerCardToPlayers(int cardCount) {
        for (Player player : participants) {
            player.addCards(Cards.selectRandomCards(cardCount));
        }
    }

    public void offerCardToPlayer(String name, int cardCount) {
        Player foundPlayer = participants.stream()
                .filter(player -> player.isSameName(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("참가자가 존재하지 않습니다."));
        foundPlayer.addCards(Cards.selectRandomCards(cardCount));
    }

    public List<Participant> getParticipants() {
        return participants;
    }
}
