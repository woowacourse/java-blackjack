package blackjack.domain.participant;

import blackjack.domain.participant.attribute.Money;
import blackjack.domain.participant.attribute.Name;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static blackjack.domain.participant.Players.*;

// TODO: 베팅 플레이어 리스트 생성 테스트, 새로운 Player가 생기면 여기가 변경됨. 개선 가능성 있으까?
public class PlayersFactory {
    private static final String UNMATCHED_PARAMETER_NUMBERS_ERR_MSG = "전달받은 이름과 배팅 금액의 수가 일치하지 않습니다.";

    private PlayersFactory() {
    }

    public static Players<Player> createPlayers(List<Name> names) {
        validateAttributes(names);

        return names.stream()
                .map(Player::new)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Players::new));
    }

    public static Players<BettingPlayer> createBettingPlayers(List<Name> names, List<Money> moneys) {
        validateAttributes(names);
        validateAttributes(moneys);
        validateSize(names, moneys);

        List<BettingPlayer> players = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            BettingPlayer player = new BettingPlayer(names.get(i), moneys.get(i));
            players.add(player);
        }
        return new Players<>(players);
    }

    private static void validateAttributes(List<?> attributes) {
        Objects.requireNonNull(attributes, NULL_ARGUMENT_ERR_MSG);

        if (attributes.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_ARGUMENT_ERR_MSG);
        }

        if (attributes.size() > MAX_PLAYER) {
            throw new IllegalArgumentException(MAX_PLAYER_ERR_MSG);
        }
    }

    private static void validateSize(List<Name> names, List<Money> moneys) {
        if (names.size() != moneys.size()) {
            throw new IllegalArgumentException(UNMATCHED_PARAMETER_NUMBERS_ERR_MSG);
        }
    }
}
