package domain;

import static org.assertj.core.api.Assertions.assertThat;

import fixture.UserFixture;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vo.Money;
import vo.Rank;
import vo.Suit;

public class UserTest {
    @Test
    @DisplayName("유저가 카드를 받는다. - receiveCard(Card card) + getCardsDisplay()")
    void 유저_카드_받기_테스트() {
        // given
        User user = UserFixture.createDefaultUser();

        // when
        user.receiveCard(new Card(Suit.SPADE, Rank.ACE));
        user.receiveCard(new Card(Suit.HEART, Rank.JACK));
        String exceptedResult = "A스페이드, J하트";

        // then
        assertThat(user.getCardsDisplay()).isEqualTo(exceptedResult);
    }

    @Test
    @DisplayName("유저가 가진 카드 점수를 계산한다. - calculateScore() + getTotalScore()")
    void 유저_카드_점수_계산_테스트() {
        // given
        User user = UserFixture.createDefaultUser();
        user.receiveCard(new Card(Suit.SPADE, Rank.ACE));
        user.receiveCard(new Card(Suit.HEART, Rank.JACK));

        // when
        user.calculateScore();

        // then
        assertThat(user.getTotalScore()).isEqualTo(21);
    }

    @Test
    @DisplayName("처음 받은 두 장의 카드 합이 21점인 경우, 블랙잭이다.")
    void 유저_카드_두_장_블랙잭_테스트() {
        // given
        User user = UserFixture.createUserWithCards(
                List.of(new Card(Suit.SPADE, Rank.ACE),
                        new Card(Suit.CLUB, Rank.JACK))
        );

        // when & then
        assertThat(user.isBlackjack()).isTrue();
    }

    @Test
    @DisplayName("카드가 3장 이상이고, 합이 21점인 경우, 블랙잭이 아니다.")
    void 유저_카드_두_장_초과_블랙잭_아님_테스트() {
        // given
        User user = UserFixture.createUserWithCards(
                List.of(new Card(Suit.SPADE, Rank.SEVEN),
                        new Card(Suit.CLUB, Rank.SEVEN),
                        new Card(Suit.HEART, Rank.SEVEN))
        );

        // when & then
        assertThat(user.isBlackjack()).isFalse();
    }

    @Test
    @DisplayName("카드 합이 21점 초과인 경우, 버스트다.")
    void 유저_카드_버스트_테스트() {
        // given
        User user = UserFixture.createUserWithCards(
                List.of(new Card(Suit.SPADE, Rank.SEVEN),
                        new Card(Suit.CLUB, Rank.SEVEN),
                        new Card(Suit.CLUB, Rank.JACK))
        );

        // when & then
        assertThat(user.isBust()).isTrue();
    }

    @Test
    @DisplayName("유저의 이름을 반환한다. - getUserName()")
    void 유저_이름_반환_테스트() {
        // given
        User user = UserFixture.createDefaultUser();

        // when
        String expectedResult = "라이";

        // then
        assertThat(user.getUserName()).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("유저의 베팅 머니를 반환한다. - getBettingMoney()")
    void 유저_베팅_머니_반환_테스트() {
        // given
        User user = UserFixture.createDefaultUser();

        // when
        Money expectedResult = new Money(10000);

        // then
        assertThat(user.getBettingMoney()).isEqualTo(expectedResult);
    }
}
