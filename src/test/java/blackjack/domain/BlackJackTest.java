package blackjack.domain;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.strategy.ShuffledDeckGenerateStrategy;
import blackjack.domain.user.BettingMoney;
import blackjack.domain.user.User;
import blackjack.domain.user.Users;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackTest {

    private static final int MINIMUM_BETTING_AMOUNT = 1;

    @DisplayName("플레이어 초기 카드 세팅 테스트")
    @Test
    public void setInitCardsPerPlayer() {
        //given
        Map<String, BettingMoney> playerInfo = createPlayerInfo(List.of("pobi", "jason"));

        BlackJack blackJack = BlackJack.from(playerInfo, new ShuffledDeckGenerateStrategy());

        //when
        blackJack.setInitCardsPerPlayer();

        //then
        Users users = blackJack.getUsers();

        List<Integer> counts = users.getPlayers().stream()
                .map(user -> user.showCards().size())
                .collect(toList());

        User dealer = users.getDealer();

        int size = dealer.showCards().size();

        assertThat(counts.get(0)).isEqualTo(2);
        assertThat(counts.get(1)).isEqualTo(2);
        assertThat(size).isEqualTo(2);
    }

    private Map<String, BettingMoney> createPlayerInfo(List<String> names) {
        Map<String, BettingMoney> playerInfo = new HashMap<>();

        names.stream()
                .forEach(name -> playerInfo.put(name, new BettingMoney(MINIMUM_BETTING_AMOUNT)));

        return playerInfo;
    }
}