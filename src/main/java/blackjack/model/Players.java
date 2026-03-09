package blackjack.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Players {

    private final List<Player> players;

    public Players(List<String> names) {
        validateDuplicate(names);
        this.players = names.stream()
                .map(Player::new)
                .toList();
    }

    public List<Player> all() {
        return Collections.unmodifiableList(players);
    }

    public List<GameSummary> calculateGameResult(Dealer dealer) {

        List<GameSummary> gameSummaries = new ArrayList<>();

        GameSummary dealerSummary = dealer.toSummary();
        gameSummaries.add(dealerSummary);

        for (Player player : players) {
            GameSummary playerSummary = player.toSummary();
            gameSummaries.add(playerSummary);

            GameResult result = GameResult.judge(playerSummary, dealerSummary);

            player.mark(result);
            dealer.addResult(result.reverse());
        }

        return gameSummaries;
    }

    private void validateDuplicate(List<String> names) {
        int unique = (int) names.stream().distinct().count();
        if (unique != names.size()) {
            throw new IllegalArgumentException("플레이어 이름은 중복될 수 없습니다.");
        }
    }
}
