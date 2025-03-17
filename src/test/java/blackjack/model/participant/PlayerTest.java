package blackjack.model.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.model.card.Card;
import blackjack.model.card.CardValue;
import blackjack.model.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("플레이어 테스트")
class PlayerTest {

    private Player makePlayer(String name) {
        return Player.of(name, 1000);
    }

    @DisplayName("이름과 배팅 금액을 가진다.")
    @Test
    void createPlayerTest() {
        // given
        String name = "pobi";

        // when, then
        assertThatCode(() -> Player.of(name, 10000))
                .doesNotThrowAnyException();
    }

    @DisplayName("이름이 비었거나, null이면 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {" ", ""})
    void createPlayerValidateTest(String name) {
        assertThatCode(() -> Player.of(name, 1000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 한글자 이상이어야합니다.");
    }

    @DisplayName("카드를 받을 수 있다.")
    @Test
    void receiveHandTest() {
        // given
        Card card = new Card(Suit.SPADES, CardValue.ACE);
        Player player = makePlayer("pobi");

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
        Player player = makePlayer("pobi");

        // when
        player.receiveHand(spadeTen);
        player.receiveHand(spadeFive);

        // then
        assertThat(player.calculateHandTotal())
                .isEqualTo(15);
    }

    @DisplayName("ACE를 가진 채, 총합이 11 이하인 경우 ACE를 11로 간주한다.")
    @Test
    void calculateHandTotalWithAceTest() {
        // given
        Card spadeAce = new Card(Suit.SPADES, CardValue.ACE);
        Card spadeTen = new Card(Suit.SPADES, CardValue.TEN);
        Player player = makePlayer("pobi");

        // when
        player.receiveHand(spadeAce);
        player.receiveHand(spadeTen);

        // then
        assertThat(player.calculateHandTotal())
                .isEqualTo(21);
    }

    @DisplayName("ACE를 가진 채, 총합이 11 초과인 경우 ACE를 1로 간주한다.")
    @Test
    void calculateHandTotalWithAceTestOver11() {
        // given
        Card spadeAce = new Card(Suit.SPADES, CardValue.ACE);
        Card spadeTwo = new Card(Suit.SPADES, CardValue.TWO);
        Card spadeNine = new Card(Suit.SPADES, CardValue.NINE);
        Player player = makePlayer("pobi");

        // when
        player.receiveHand(spadeAce);
        player.receiveHand(spadeTwo);
        player.receiveHand(spadeNine);

        // then
        assertThat(player.calculateHandTotal())
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
        Player player = makePlayer("pobi");
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
        Player player = makePlayer("pobi");
        player.receiveHand(card1);
        player.receiveHand(card2);
        player.receiveHand(card3);

        // when
        boolean isBust = player.isBust();

        // then
        assertThat(isBust)
                .isSameAs(expected);
    }

    @DisplayName("플레이어가 버스트, 블랙잭이 아니면 히트할 수 있다.")
    @Test
    void canHitTrue_WhenNotBustAndNotBlackJackTest() {
        // given
        Player player = makePlayer("pobi");
        player.receiveHand(new Card(Suit.SPADES, CardValue.TEN));
        player.receiveHand(new Card(Suit.SPADES, CardValue.TEN));

        // then
        boolean canHit = player.canHit();

        // when
        assertThat(canHit)
                .isTrue();
    }

    @DisplayName("플레이어가 버스트면 히트할 수 없다.")
    @Test
    void canHitFalse_WhenBustTest() {
        // given
        Player player = makePlayer("pobi");
        player.receiveHand(new Card(Suit.SPADES, CardValue.TEN));
        player.receiveHand(new Card(Suit.SPADES, CardValue.TEN));
        player.receiveHand(new Card(Suit.SPADES, CardValue.TEN));

        // then
        boolean canHit = player.canHit();

        // when
        assertThat(canHit)
                .isFalse();
    }

    @DisplayName("플레이어가 블랙잭이면 히트할 수 없다.")
    @Test
    void canHitFalse_WhenBlackJackTest() {
        // given
        Player player = makePlayer("pobi");
        player.receiveHand(new Card(Suit.SPADES, CardValue.TEN));
        player.receiveHand(new Card(Suit.SPADES, CardValue.ACE));

        // then
        boolean canHit = player.canHit();

        // when
        assertThat(canHit)
                .isFalse();
    }
}
