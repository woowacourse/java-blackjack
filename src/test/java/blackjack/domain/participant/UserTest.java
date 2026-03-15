package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.BettingAmount;
import blackjack.domain.deck.Card;
import blackjack.domain.deck.CardShape;
import blackjack.domain.deck.CardValue;
import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    @DisplayName("유저가 카드 한 장을 가져오는 테스트")
    void user_draw_one_card() {
        // given
        User user = new User("밀란", new BettingAmount(new BigDecimal("10000")));
        Card card = new Card(CardValue.ACE, CardShape.DIAMOND);

        // when
        int before = user.getCardsName().size();
        user.add(card);

        // then
        assertThat(user.getCardsName().size()).isEqualTo(before + 1);
    }

    @Test
    @DisplayName("가지고있는 카드의 합을 계산하는 기능 테스트")
    void calculate_cards_value() {
        // given
        User user = new User("밀란", new BettingAmount(new BigDecimal("10000")));
        user.add(new Card(CardValue.FOUR, CardShape.DIAMOND));
        user.add(new Card(CardValue.TWO, CardShape.DIAMOND));

        // when
        int sum = user.calculateCardsValue();

        // then
        assertThat(sum).isEqualTo(6);
    }

    @Test
    @DisplayName("ACE의 값이 1이 유리할 때 테스트")
    void ace_is_one_value_is_more_valuable() {
        // given
        User user = new User("밀란", new BettingAmount(new BigDecimal("10000")));
        user.add(new Card(CardValue.ACE, CardShape.DIAMOND));
        user.add(new Card(CardValue.TEN, CardShape.DIAMOND));
        user.add(new Card(CardValue.THREE, CardShape.DIAMOND));

        // when
        int sum = user.calculateCardsValue();

        // then
        assertThat(sum).isEqualTo(14);
    }

    @Test
    @DisplayName("ACE의 값이 11이 유리할 때 테스트")
    void ace_is_eleven_value_is_more_valuable() {
        // given
        User user = new User("밀란", new BettingAmount(new BigDecimal("10000")));
        user.add(new Card(CardValue.ACE, CardShape.DIAMOND));
        user.add(new Card(CardValue.TEN, CardShape.DIAMOND));

        // when
        int sum = user.calculateCardsValue();

        // then
        assertThat(sum).isEqualTo(21);
    }

    @Test
    @DisplayName("카드 합 Burst 판단 테스트")
    void is_burst() {
        // given
        User user = new User("밀란", new BettingAmount(new BigDecimal("10000")));
        user.add(new Card(CardValue.TEN, CardShape.CLOVER));
        user.add(new Card(CardValue.TEN, CardShape.DIAMOND));
        user.add(new Card(CardValue.TEN, CardShape.HEART));

        // when
        boolean isBurst = user.isBurst();

        // then
        assertThat(isBurst).isTrue();
    }

    @Test
    @DisplayName("카드 합 Blackjack 판단 테스트")
    void is_blackjack() {
        // given
        User user = new User("밀란", new BettingAmount(new BigDecimal("10000")));
        user.add(new Card(CardValue.ACE, CardShape.CLOVER));
        user.add(new Card(CardValue.TEN, CardShape.DIAMOND));

        // when
        boolean isBlackjack = user.isFinished();

        // then
        assertThat(isBlackjack).isTrue();
    }

}
