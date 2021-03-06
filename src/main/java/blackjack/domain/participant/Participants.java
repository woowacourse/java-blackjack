package blackjack.domain.participant;

import blackjack.domain.card.Deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Participants {
    private static final String PLAYER_COUNT_ERROR_MESSAGE = "플레이어 수는 1명 이상이어야 합니다.";
    private static final int MIN_PLAYERS_NUMBER = 1;

    private final List<Participant> participants = new ArrayList<>();

    public Participants(Dealer dealer, List<String> playerNames) {
        validateNumberOfPlayer(new ArrayList<>(playerNames));
        participants.add(dealer);
        participants.addAll(makePlayers(playerNames));
    }

    private void validateNumberOfPlayer(List<String> playerNames) {
        if (playerNames.size() < MIN_PLAYERS_NUMBER) {
            throw new IllegalArgumentException(PLAYER_COUNT_ERROR_MESSAGE);
        }
    }

    private List<Player> makePlayers(List<String> playerNames) {
        return playerNames.stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }

    public void drawAtFirst(Deck deck) {
        participants.forEach(participant -> participant.drawAtFirst(deck));
    }

    public List<Participant> getParticipant() {
        return Collections.unmodifiableList(new ArrayList<>(this.participants));
    }
}
