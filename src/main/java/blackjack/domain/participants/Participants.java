package blackjack.domain.participants;

import blackjack.domain.result.JudgeResult;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Participants {

    private final Dealer dealer;
    private final Players players;

    private Participants(final Dealer dealer, final List<Player> players) {
        this.dealer = dealer;
        this.players = new Players(players);
    }

    public static Participants of(final String dealerName, final List<String> playerNames) {
        validatePlayerNames(dealerName, playerNames);
        final List<Player> players = playerNames.stream()
                .map(Player::new)
                .collect(Collectors.toList());
        return new Participants(new Dealer(dealerName), players);
    }

    private static void validatePlayerNames(final String dealerName, final List<String> playerNames) {
        if (playerNames.size() != new HashSet<>(playerNames).size()) {
            throw new IllegalArgumentException("플레이어 이름은 중복될 수 없습니다.");
        }
        if (playerNames.contains(dealerName)) {
            throw new IllegalArgumentException("플레이어 이름은 딜러 이름(" + dealerName + ")과 중복될 수 없습니다.");
        }
    }

    public Participant findParticipantByName(final String participantName) {
        if (Objects.equals(participantName, dealer.getName())) {
            return dealer;
        }
        return players.findPlayerBy(participantName);
    }

    public Map<String, JudgeResult> collectPlayerJudgeResults() {
        return dealer.judgeAllPlayersResult(players);
    }

    public Dealer dealer() {
        return dealer;
    }

    public List<Player> players() {
        return players.players();
    }
}
