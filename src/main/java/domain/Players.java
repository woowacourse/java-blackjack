package domain;

import domain.card.Deck;
import domain.participant.Name;
import domain.participant.Participant;
import domain.participant.Player;
import exception.ExceptionMessage;
import java.util.ArrayList;
import java.util.List;

public class Players {

    private final List<Player> players;

    public Players() {
        this.players = new ArrayList<>();
    }

    public Players(List<String> playerNames, Deck deck) {
        this.players = new ArrayList<>();
        validateUnique(playerNames);
        playerNames.forEach(playerName -> addParticipant(deck, playerName));
    }

    public void validateUnique(List<String> values) {
        if (isDuplicated(values)) {
            throw new IllegalArgumentException(ExceptionMessage.DUPLICATED_PARTICIPANT_NAME.getMessage());
        }
    }

    private boolean isDuplicated(List<String> values) {
        return values.stream()
                .distinct()
                .count() != values.size();
    }

    private void addParticipant(Deck deck, String playerName) {
        Player player = new Player(new Name(playerName), deck.dealInitialCards());
        players.add(player);
    }

    public void add(Player player) {
        players.add(player);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<String> getParticipantNames() {
        return players.stream()
                .map(Participant::getName)
                .toList();
    }
}
