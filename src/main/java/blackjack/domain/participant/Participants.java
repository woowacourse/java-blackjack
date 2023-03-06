package blackjack.domain.participant;

import java.util.ArrayList;
import java.util.List;

public class Participants {
    private static final String PLAYER_NAME_DUPLICATE_ERROR_MESSAGE = "플레이어 이름은 중복될 수 없습니다.";

    private final Dealer dealer;
    private final Players players;

    public Participants(List<String> playerNames) {
        dealer = new Dealer();
        players = generatePlayers(playerNames);
    }

    private Players generatePlayers(List<String> playerNames) {
        validatePlayerNameDuplicate(playerNames);

        List<Player> players = new ArrayList<>();
        for (String playerName : playerNames) {
            players.add(new Player(playerName));
        }
        return new Players(players);
    }

    private void validatePlayerNameDuplicate(List<String> playerNames) {
        if (playerNames.size() != playerNames.stream().distinct().count()) {
            throw new IllegalArgumentException(PLAYER_NAME_DUPLICATE_ERROR_MESSAGE);
        }
    }

    public List<Participant> getParticipants() {
        List<Participant> participants = new ArrayList<>(List.of(dealer));
        participants.addAll(players.getPlayers());
        return participants;
    }
}
