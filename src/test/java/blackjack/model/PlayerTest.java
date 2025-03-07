package blackjack.model;

import static blackjack.TestFixtures.NO_HIT_STRATEGY;
import static blackjack.TestFixtures.createHitDecisionStrategy;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("플레이어 테스트")
class PlayerTest {

    @DisplayName("이름을 가진다.")
    @Test
    void createPlayerTest() {
        // given
        String name = "pobi";

        // when, then
        assertThatCode(() -> new Player(name, NO_HIT_STRATEGY))
                .doesNotThrowAnyException();
    }

    @DisplayName("카드를 받을 수 있다.")
    @Test
    void receiveHandTest() {
        // given
        Card card = new Card(Suit.SPADES, CardValue.ACE);
        Player player = new Player("pobi", NO_HIT_STRATEGY);

        // when
        player.receiveHand(card);

        // then
        assertThat(player.getHand())
                .contains(card);

    }

    @DisplayName("가진 패의 총합을 계산한다.")
    @Test
    void calculateHandTotalTest() {
        // given
        Card spadeTen = new Card(Suit.SPADES, CardValue.TEN);
        Card spadeFive = new Card(Suit.SPADES, CardValue.FIVE);
        Player player = new Player("pobi", NO_HIT_STRATEGY);

        // when
        player.receiveHand(spadeTen);
        player.receiveHand(spadeFive);

        // then
        assertThat(player.getTotal())
                .isEqualTo(15);
    }

    @DisplayName("ACE를 가진 채, 총합이 11 이하인 경우 ACE를 11로 간주한다.")
    @Test
    void calculateHandTotalWithAceTest() {
        // given
        Card spadeAce = new Card(Suit.SPADES, CardValue.ACE);
        Card spadeTen = new Card(Suit.SPADES, CardValue.TEN);
        Player player = new Player("pobi", NO_HIT_STRATEGY);

        // when
        player.receiveHand(spadeAce);
        player.receiveHand(spadeTen);

        // then
        assertThat(player.getTotal())
                .isEqualTo(21);
    }

    @DisplayName("ACE를 가진 채, 총합이 11 초과인 경우 ACE를 1로 간주한다.")
    @Test
    void calculateHandTotalWithAceTestOver11() {
        // given
        Card spadeAce = new Card(Suit.SPADES, CardValue.ACE);
        Card spadeTwo = new Card(Suit.SPADES, CardValue.TWO);
        Card spadeNine = new Card(Suit.SPADES, CardValue.NINE);
        Player player = new Player("pobi", NO_HIT_STRATEGY);

        // when
        player.receiveHand(spadeAce);
        player.receiveHand(spadeTwo);
        player.receiveHand(spadeNine);

        // then
        assertThat(player.getTotal())
                .isEqualTo(12);
    }

    @DisplayName("패가 2장만 있고, 합이 21이면 블랙잭이다.")
    @ParameterizedTest
    @CsvSource({
            "TEN, ACE, true",
            "TEN, TEN, false",
    })
    void isBlackjackTest(CardValue value1, CardValue value2, boolean expected) {
        // given
        Card spadeTen = new Card(Suit.SPADES, value1);
        Card spadeAce = new Card(Suit.SPADES, value2);
        Player player = new Player("pobi", NO_HIT_STRATEGY);
        player.receiveHand(spadeTen);
        player.receiveHand(spadeAce);

        // when
        boolean isBlackjack = player.isBlackjack();

        // then
        assertThat(isBlackjack)
                .isSameAs(expected);
    }

    @DisplayName("21이 초과하면 버스트이다.")
    @ParameterizedTest
    @CsvSource({
            "TEN, TEN, TEN, true",
            "TWO, TWO, ACE, false",
    })
    void isBustTest(CardValue value1, CardValue value2, CardValue value3, boolean expected) {
        // given
        Card card1 = new Card(Suit.SPADES, value1);
        Card card2 = new Card(Suit.SPADES, value2);
        Card card3 = new Card(Suit.SPADES, value3);
        Player player = new Player("pobi", NO_HIT_STRATEGY);
        player.receiveHand(card1);
        player.receiveHand(card2);
        player.receiveHand(card3);

        // when
        boolean isBust = player.isBust();

        // then
        assertThat(isBust)
                .isSameAs(expected);
    }

    @DisplayName("플레이어가 히트 여부를 결정한다.")
    @Test
    void shouldHitFalseTest() {
        // given
        Player player = new Player("pobi", NO_HIT_STRATEGY);

        // then
        boolean shouldHit = player.shouldHit();

        // when
        assertThat(shouldHit)
                .isFalse();
    }

    @DisplayName("플레이어가 히트 여부를 결정한다.")
    @Test
    void shouldHitTrueTest() {
        // given
        Player player = new Player("pobi", createHitDecisionStrategy(List.of(true)));

        // then
        boolean shouldHit = player.shouldHit();

        // when
        assertThat(shouldHit)
                .isTrue();
    }
}
