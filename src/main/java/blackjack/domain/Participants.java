package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Participants {

    private final Dealer dealer = new Dealer();
    private final List<Player> players;

    public Participants(List<String> playerNames) {
        validate(playerNames);

        this.players = playerNames.stream()
                .map(Player::new)
                .toList();
    }

    private void validate(List<String> playerNames) {
        validatePlayerSize(playerNames);
        validateDuplicatedPlayerNames(playerNames);
        validateDuplicatedDealerName(playerNames, dealer.getName());
    }

    private void validatePlayerSize(List<String> playerNames) {
        if (playerNames.isEmpty()) {
            throw new IllegalArgumentException("플레이어가 존재하지 않습니다.");
        }
    }

    private void validateDuplicatedPlayerNames(List<String> playerNames) {
        if (getUniqueSize(playerNames) != playerNames.size()) {
            throw new IllegalArgumentException("플레이어 이름이 중복되었습니다.");
        }
    }

    private long getUniqueSize(List<String> playerNames) {
        return playerNames.stream()
                .distinct()
                .count();
    }

    private void validateDuplicatedDealerName(List<String> playerNames, String dealerName) {
        if (playerNames.contains(dealerName)) {
            throw new IllegalArgumentException(String.format("플레이어 이름은 '%s'가 될 수 없습니다.", dealerName));
        }
    }

    public List<Participant> getParticipants() {
        List<Participant> participants = new ArrayList<>();

        participants.add(dealer);
        participants.addAll(players);

        return participants;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
