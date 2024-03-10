package blackjack.domain.player;

import blackjack.domain.rule.Score;
import blackjack.domain.rule.ScoreCalculateStrategy;
import java.util.List;

public class Players {

    private static final int MAX_COUNT = 3;

    private final List<Player> players;

    public Players(List<Player> players) {
        validate(players);
        this.players = players;
    }

    public int countPlayer() {
        return players.size();
    }

    public int countPlayerWithScoreAbove(Score target, ScoreCalculateStrategy scoreCalculateStrategy) {
        return (int) players.stream()
                .map(Participant::calculateHandScore)
                .filter(playerScore -> playerScore.isAbove(target))
                .count();
    }

    private void validate(List<Player> participants) {
        validateEachPlayerNameUnique(participants);
        validateEntryNotEmpty(participants);
        validatePlayerCountRange(participants);
    }

    private void validateEachPlayerNameUnique(List<Player> participants) {
        if (countUniquePlayer(participants) != participants.size()) {
            throw new IllegalArgumentException("[ERROR] 중복되는 플레이어의 이름이 존재합니다");
        }
    }

    private int countUniquePlayer(List<Player> participants) {
        return (int) participants.stream()
                .distinct()
                .count();
    }

    private void validateEntryNotEmpty(List<Player> participants) {
        if (participants.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 플레이어가 없습니다");
        }
    }

    private void validatePlayerCountRange(List<Player> participants) {
        if (participants.size() > MAX_COUNT) {
            throw new IllegalArgumentException("[ERROR] 플레이어의 수는 " + MAX_COUNT + "이하여야 합니다");
        }
    }

    public List<Player> getPlayers() {
        return players;
    }
}
