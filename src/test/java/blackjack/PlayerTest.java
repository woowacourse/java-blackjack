package blackjack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("플레이어 테스트")
class PlayerTest {

    @DisplayName("이름을 가진다.")
    @Test
    void createPlayerTest() {
        // given
        String name = "pobi";

        // when, then
        assertThatCode(() -> new Player(name))
                .doesNotThrowAnyException();
    }

    @DisplayName("카드를 받을 수 있다.")
    @Test
    void receiveHandTest() {
        // given
        Card card = new Card(Suit.SPADES, CardValue.ACE);
        Player player = new Player("pobi");

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
        Player player = new Player("pobi");

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
        Player player = new Player("pobi");

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
        Player player = new Player("pobi");

        // when
        player.receiveHand(spadeAce);
        player.receiveHand(spadeTwo);
        player.receiveHand(spadeNine);

        // then
        assertThat(player.getTotal())
                .isEqualTo(12);
    }
}
