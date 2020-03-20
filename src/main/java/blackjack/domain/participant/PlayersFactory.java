package blackjack.domain.participant;

import blackjack.domain.participant.attribute.Money;
import blackjack.domain.participant.attribute.Name;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

// TODO: 베팅 플레이어 리스트 생성 테스트
public class PlayersFactory {
    static final String NULL_ARGUMENT_ERR_MSG = "Null 인자로 플레이어를 생성할 수 없습니다.";
    static final String EMPTY_ARGUMENT_ERR_MSG = "Empty 리스트 인자로 플레이어 인자를 생성할 수 없습니다.";
    private static final int MAX_PLAYER = 5;
    static final String MAX_PLAYER_ERR_MSG = String.format("플레이어는 최대 %d명입니다.", MAX_PLAYER);
    private static final String UNMATCHED_PARAMETER_NUMBERS_ERR_MSG = "전달받은 이름과 배팅 금액의 수가 일치하지 않습니다.";

    private PlayersFactory() {
    }

    public static List<Player> of(List<Name> names) {
        validateList(names);

        return names.stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }

    public static List<Player> of(List<Name> names, List<Money> moneys) {
        validateList(names);
        validateList(moneys);
        validateSize(names, moneys);

        List<Player> players = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            Player player = new BettingPlayer(names.get(i), moneys.get(i));
            players.add(player);
        }
        return players;
    }

    private static void validateList(List enteredInfo) {
        Objects.requireNonNull(enteredInfo, NULL_ARGUMENT_ERR_MSG);

        if (enteredInfo.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_ARGUMENT_ERR_MSG);
        }

        if (enteredInfo.size() > MAX_PLAYER) {
            throw new IllegalArgumentException(MAX_PLAYER_ERR_MSG);
        }
    }

    private static void validateSize(List<Name> names, List<Money> moneys) {
        if (names.size() != moneys.size()) {
            throw new IllegalArgumentException(UNMATCHED_PARAMETER_NUMBERS_ERR_MSG);
        }
    }
}
