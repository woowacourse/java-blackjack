package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.dto.BlackjackResult;
import blackjack.dto.DealerResult;
import blackjack.dto.PlayerInfo;
import blackjack.dto.PlayerProfit;
import blackjack.dto.PlayerResult;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Players {

    private static final String NAME_DUPLICATED_EXCEPTION = "플레이어의 이름은 중복될 수 없습니다.";

    private final List<Player> players;

    private Players(final List<Player> players) {
        this.players = players;
    }

    public static Players of(final List<PlayerInfo> playerInfos) {
        validate(playerInfos);

        List<Player> players = playerInfos.stream()
                .map(Player::new)
                .toList();

        return new Players(players);
    }

    private static void validate(final List<PlayerInfo> playerInfos) {
        if (isDuplicated(playerInfos)) {
            throw new IllegalArgumentException(NAME_DUPLICATED_EXCEPTION);
        }
    }

    private static boolean isDuplicated(final List<PlayerInfo> playerInfos) {
        Set<String> names = playerInfos.stream()
                .map(PlayerInfo::name)
                .collect(Collectors.toSet());

        return playerInfos.size() != names.size();
    }

    public BlackjackResult createResult(final Dealer dealer) {
        ResultStatus resultStatus = ResultStatus.init();
        PlayerResult playerResult = new PlayerResult();

        for (Player player : players) {
            DealerResult result = Judge.judge(resultStatus, player, dealer, playerResult);
            resultStatus.updateResultStatus(result);
        }

        return new BlackjackResult(DealerResult.of(resultStatus), playerResult);
    }

    public List<String> getNames() {
        return players.stream()
                .map(Player::getName)
                .toList();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<PlayerProfit> getPlayerProfits() {
        return players.stream()
                .map(player -> new PlayerProfit(player.getName(), player.getProfit()))
                .toList();
    }
}
