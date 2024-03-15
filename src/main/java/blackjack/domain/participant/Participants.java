package blackjack.domain.participant;

import java.util.ArrayList;
import java.util.List;
import blackjack.domain.common.Money;

public class Participants {

    private final Dealer dealer = new Dealer();
    private final List<Player> players;

    public Participants(List<String> playerNames, List<Money> playersMoney) {
        validate(playerNames, playersMoney);

        this.players = createPlayers(playerNames, playersMoney);
    }

    private void validate(List<String> playerNames, List<Money> playersMoney) {
        validatePlayerSize(playerNames);
        validateDuplicatedPlayerNames(playerNames);
        validateSameSize(playerNames, playersMoney);
        validateInvalidPlayerName(playerNames, dealer.getName());
    }

    private void validatePlayerSize(List<String> playerNames) {
        if (playerNames.isEmpty()) {
            throw new IllegalArgumentException("사용자가 존재하지 않습니다.");
        }
    }

    private void validateDuplicatedPlayerNames(List<String> playerNames) {
        if (getUniqueSize(playerNames) != playerNames.size()) {
            throw new IllegalArgumentException("사용자 이름이 중복되었습니다.");
        }
    }
    private long getUniqueSize(List<String> playerNames) {
        return playerNames.stream()
                .distinct()
                .count();
    }

    private void validateSameSize(List<String> playerNames, List<Money> playersMoney) {
        if (playerNames.size() != playersMoney.size()) {
            throw new IllegalArgumentException("사용자의 이름의 수와 돈의 수가 일치하지 않습니다.");
        }
    }

    private void validateInvalidPlayerName(List<String> playerNames, String dealerName) {
        if (playerNames.contains(dealerName)) {
            throw new IllegalArgumentException(String.format("사용자 이름은 '%s'가 될 수 없습니다.", dealerName));
        }
    }

    private List<Player> createPlayers(List<String> playerNames, List<Money> playersMoney) {
        List<Player> players = new ArrayList<>();
        int totalPlayerSize = playerNames.size();

        for (int index = 0; index < totalPlayerSize; index++) {
            Player player = new Player(playerNames.get(index), playersMoney.get(index));
            players.add(player);
        }

        return players;
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
