package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.painting.Suit;
import blackjack.domain.card.painting.Symbol;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserTest {
    @DisplayName("유저의 카드 뽑기 테스트")
    @Test
    void testDrawCard() {
        //given
        User user = new User("욘", 0);

        //when
        user.drawCard(new Card(Suit.DIAMOND, Symbol.ACE));
        user.drawCard(new Card(Suit.CLOVER, Symbol.FOUR));

        //then
        List<Card> userCards = user.getCards().stream()
                .collect(toList());
        assertThat(userCards).containsExactly(new Card(Suit.DIAMOND, Symbol.ACE), new Card(Suit.CLOVER, Symbol.FOUR));
    }

    @DisplayName("유저의 이름이 딜러와 같을 때 검증")
    @Test
    void whenUserNameEqualsDealer() {
        //given
        String dealerName = "딜러";
        //when
        //then
        assertThatThrownBy(() -> new User(dealerName, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("딜러는 이름으로 사용할 수 없습니다.");
    }
}