package blackjack.domain.player;

import blackjack.domain.rule.Score;
import blackjack.domain.rule.ScoreCalculateStrategy;
import java.util.List;

public class Players {

    private static final int MAX_COUNT = 3;

    private final List<Participant> participants;

    public Players(List<Participant> participants) {
        validate(participants);
        this.participants = participants;
    }

    public int countPlayer() {
        return participants.size();
    }

    public int countPlayerWithScoreAbove(Score target, ScoreCalculateStrategy scoreCalculateStrategy) {
        return (int) participants.stream()
                .map(Participant::calculateHandScore)
                .filter(playerScore -> playerScore.isAbove(target))
                .count();
    }

    private void validate(List<Participant> participants) {
        validateEachPlayerNameUnique(participants);
        validateEntryNotEmpty(participants);
        validatePlayerCountRange(participants);
    }

    private void validateEachPlayerNameUnique(List<Participant> participants) {
        if (countUniquePlayer(participants) != participants.size()) {
            throw new IllegalArgumentException("[ERROR] 중복되는 플레이어의 이름이 존재합니다");
        }
    }

    private int countUniquePlayer(List<Participant> participants) {
        return (int) participants.stream()
                .distinct()
                .count();
    }

    private void validateEntryNotEmpty(List<Participant> participants) {
        if (participants.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 플레이어가 없습니다");
        }
    }

    private void validatePlayerCountRange(List<Participant> participants) {
        if (participants.size() > MAX_COUNT) {
            throw new IllegalArgumentException("[ERROR] 플레이어의 수는 " + MAX_COUNT + "이하여야 합니다");
        }
    }

    public List<Participant> getPlayers() {
        return participants;
    }
}
