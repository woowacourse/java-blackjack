package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Suit;
import blackjack.domain.card.Value;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserTest {

    @DisplayName("유저의 카드 뽑기 테스트")
    @Test
    void testDrawCard() {
        //given
        User user = new User(Name.from("욘"));

        //when
        user.drawCard(new Card(Suit.DIAMOND, Value.ACE));
        user.drawCard(new Card(Suit.CLOVER, Value.FOUR));

        //then
        assertThat(user.getCards()).containsExactly(new Card(Suit.DIAMOND, Value.ACE), new Card(Suit.CLOVER, Value.FOUR));
    }

    @DisplayName("딜러와 이름이 같은 경우 예외를 발생한다.")
    @Test
    void testSameNameWithDealer() {
        assertThatThrownBy(() -> new User(Name.from("딜러"))).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유저의 이름으로 딜러는 사용할 수 없습니다.");

    }
}