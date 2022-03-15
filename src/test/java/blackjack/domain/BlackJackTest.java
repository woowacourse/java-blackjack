package blackjack.domain;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.strategy.ShuffledDeckGenerateStrategy;
import blackjack.domain.user.User;
import blackjack.domain.user.Users;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackTest {

    @DisplayName("플레이어 초기 카드 세팅 테스트")
    @Test
    public void setInitCardsPerPlayer() {
        //given
        BlackJack blackJack = BlackJack.from(List.of("pobi", "jason"), new ShuffledDeckGenerateStrategy());

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
}