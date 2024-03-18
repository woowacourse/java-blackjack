package blackjack.domain.participant;

import java.util.ArrayList;
import java.util.List;

class PlayerFactory {

    private PlayerFactory() {
    }

    public static List<Player> createPlayers(List<String> playerNames, List<Money> playersMoney, Dealer dealer) {
        validate(playerNames, playersMoney, dealer);

        return create(playerNames, playersMoney);
    }

    private static void validate(List<String> playerNames, List<Money> playersMoney, Dealer dealer) {
        validatePlayerSize(playerNames);
        validateDuplicatedPlayerNames(playerNames);
        validateSameSize(playerNames, playersMoney);
        validateInvalidPlayerName(playerNames, dealer.getName());
    }

    private static void validatePlayerSize(List<String> playerNames) {
        if (playerNames.isEmpty()) {
            throw new IllegalArgumentException("사용자가 존재하지 않습니다.");
        }
    }

    private static void validateDuplicatedPlayerNames(List<String> playerNames) {
        if (getUniqueSize(playerNames) != playerNames.size()) {
            throw new IllegalArgumentException("사용자 이름이 중복되었습니다.");
        }
    }

    private static long getUniqueSize(List<String> playerNames) {
        return playerNames.stream()
                .distinct()
                .count();
    }

    private static void validateSameSize(List<String> playerNames, List<Money> playersMoney) {
        if (playerNames.size() != playersMoney.size()) {
            throw new IllegalArgumentException("사용자의 이름의 수와 돈의 수가 일치하지 않습니다.");
        }
    }

    private static void validateInvalidPlayerName(List<String> playerNames, String dealerName) {
        if (playerNames.contains(dealerName)) {
            throw new IllegalArgumentException(String.format("사용자 이름은 '%s'가 될 수 없습니다.", dealerName));
        }
    }

    private static List<Player> create(List<String> playerNames, List<Money> playersMoney) {
        List<Player> players = new ArrayList<>();
        int totalPlayerSize = playerNames.size();

        for (int index = 0; index < totalPlayerSize; index++) {
            Player player = new Player(playerNames.get(index), playersMoney.get(index));
            players.add(player);
        }

        return players;
    }
}
