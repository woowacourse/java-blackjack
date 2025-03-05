package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


@Nested
public class BlackJackStateTest {

    @Nested
    @DisplayName("참가자 결과 상태 테스트")
    class StateTest {

        @Test
        @DisplayName("카드가 두 장이고 그 합이 21인 경우의 상태는 블랙잭이다.")
        void createBlackJack() {
            Player player = new Player("sana");

            Card card1 = new Card(Suit.SPADE, Denomination.TEN);
            Card card2 = new Card(Suit.CLUB, Denomination.ACE);
            player.addCards(card1, card2);

            assertThat(BlackJackState.of(player)).isEqualTo(BlackJackState.BLACKJACK);
        }

        @Test
        @DisplayName("카드의 합이 21 초과인 경우의 상태는 버스트이다.")
        void createBust() {
            Player player = new Player("sana");

            Card card1 = new Card(Suit.SPADE, Denomination.TEN);
            Card card2 = new Card(Suit.CLUB, Denomination.TEN);
            Card card3 = new Card(Suit.CLUB, Denomination.JACK);
            player.addCards(card1, card2, card3);

            assertThat(BlackJackState.of(player)).isEqualTo(BlackJackState.BUST);
        }

        @Test
        @DisplayName("카드의 합이 21 이하인 경우의 상태는 그 외로 구분한다.")
        void createOthers() {
            Player player = new Player("sana");

            Card card1 = new Card(Suit.SPADE, Denomination.TEN);
            Card card2 = new Card(Suit.CLUB, Denomination.TWO);
            player.addCards(card1, card2);

            assertThat(BlackJackState.of(player)).isEqualTo(BlackJackState.OTHERS);
        }
    }
}
