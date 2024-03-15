package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.dto.ProfitResult;
import blackjack.util.JudgeUtil;

import java.util.*;

public class Players {

    private static final String NAME_DUPLICATED_EXCEPTION = "플레이어의 이름은 중복될 수 없습니다.";

    private final List<Player> players;

    private Players(final List<Player> players) {
        this.players = players;
    }

    public static Players of(final Map<String, String> playersName, final Dealer dealer) {
        List<Player> players = new ArrayList<>();

        for (String name : playersName.keySet()) {
            players.add(new Player(name, dealer, playersName.get(name)));
        }

        return new Players(players);
    }

    public static void validate(final List<String> names) {
        if (isDuplicated(names)) {
            throw new IllegalArgumentException(NAME_DUPLICATED_EXCEPTION);
        }
    }

    private static boolean isDuplicated(final List<String> names) {
        return names.size() != Set.copyOf(names).size();
    }

    public ProfitResult createProfitResult(final Dealer dealer) {
        ProfitResult profitResult = new ProfitResult();

        for (Player player : players) {
            JudgeUtil.judge(profitResult, dealer, player);
        }

        return profitResult;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
