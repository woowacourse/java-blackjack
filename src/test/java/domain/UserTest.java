package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import vo.GameResult;
import vo.Money;
import vo.Rank;
import vo.Suit;

public class UserTest {
    @ParameterizedTest
    @DisplayName("게임 결과에 따라 수익을 계산한다.")
    @CsvSource(value = {
            "WIN, 10000",
            "LOSE, -10000",
            "BLACKJACK, 15000",
            "LOSE_BY_BLACKJACK, -15000",
            "DRAW, 0"
    })
    void 수익_계산_테스트(GameResult gameResult, int expectedAmount) {
        // given
        User user = new User("라이", new Money(10000));

        // when
        Money profit = user.updateProfitBy(gameResult);

        // then
        assertThat(profit).isEqualTo(new Money(expectedAmount));
    }

    @Test
    @DisplayName("처음 받은 두 장의 카드 합이 21점인 경우, 블랙잭이다.")
    void isBlackjack_True() {
        // given
        User user = new User("라이", new Money(10000));
        user.receiveCard(new Card(Suit.SPADE, Rank.ACE));
        user.receiveCard(new Card(Suit.HEART, Rank.JACK));
        user.calculateScore();

        // when & then
        assertThat(user.isBlackjack()).isTrue();
    }

    @Test
    @DisplayName("카드가 3장 이상이고, 합이 21점인 경우, 블랙잭이 아니다.")
    void isBlackjack_False_With3Cards() {
        // given
        User user = new User("라이", new Money(10000));
        user.receiveCard(new Card(Suit.SPADE, Rank.SEVEN));
        user.receiveCard(new Card(Suit.HEART, Rank.SEVEN));
        user.receiveCard(new Card(Suit.CLUB, Rank.SEVEN));
        user.calculateScore();

        // when & then
        assertThat(user.isBlackjack()).isFalse();
    }
}
