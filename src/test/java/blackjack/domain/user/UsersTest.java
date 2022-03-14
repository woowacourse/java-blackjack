package blackjack.domain.user;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Deck;
import blackjack.domain.strategy.ShuffledDeckGenerateStrategy;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UsersTest {

    @DisplayName("문자열 기반 Users 생성 검증")
    @Test
    public void testCreateUsers() {
        //given
        List<String> names = List.of("pobi", "jason");

        //when
        Users users = Users.of(names, new Dealer());

        //then
        Assertions.assertAll(
                () -> assertThat(users).isNotNull(),
                () -> assertThat(users.getPlayers().size()).isEqualTo(2)
        );
    }

    @DisplayName("추가 카드 뽀는 로직 검증")
    @Test
    public void testDrawAdditionalCard() {
        //given
        List<String> names = List.of("pobi", "jason");
        Users users = Users.of(names, new Dealer());
        Deck deck = new Deck(new ShuffledDeckGenerateStrategy());

        //when
        users.drawAdditionalCard(user -> user.drawCard(deck),
                dealer -> dealer.drawCard(deck));

        //then
        List<Integer> count = users.getPlayers()
                .stream()
                .map(user -> user.showCards().size())
                .collect(toList());

        int size = users.getDealer().showCards().size();

        assertThat(count.get(0)).isEqualTo(1);
        assertThat(count.get(1)).isEqualTo(1);
        assertThat(size).isEqualTo(1);
    }
}