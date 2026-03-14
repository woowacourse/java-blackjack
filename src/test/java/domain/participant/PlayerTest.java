package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Nested
    class ConstructorTest {

        @Test
        void 생성하면_이름을_반환할_수_있다() {
            // given
            String name = "jacob";

            // when
            Player actual = new Player(name);

            // then
            assertThat(actual.getName()).isEqualTo(name);
        }
    }

    @Nested
    class SetBetAmountTest {

        @Test
        void 배팅_금액을_설정하면_조회할_수_있다() {
            // given
            Player player = new Player("jacob");
            BetAmount betAmount = new BetAmount("2000");

            // when
            player.setBetAmount(betAmount);

            // then
            assertThat(player.getBetAmount()).isEqualTo(2000);
        }
    }

    @Nested
    class IsBlackjackTest {

        @Test
        void 처음_두_장이_21점이면_블랙잭이다() {
            // given
            Player player = new Player("jacob");
            player.addCard(card(Rank.ACE, Suit.HEART));
            player.addCard(card(Rank.K, Suit.SPADE));

            // when
            boolean actual = player.isBlackjack();

            // then
            assertThat(actual).isTrue();
        }

        @Test
        void 처음_두_장이_21점이_아니면_블랙잭이_아니다() {
            // given
            Player player = new Player("jacob");
            player.addCard(card(Rank.TEN, Suit.HEART));
            player.addCard(card(Rank.NINE, Suit.SPADE));

            // when
            boolean actual = player.isBlackjack();

            // then
            assertThat(actual).isFalse();
        }
    }

    private static Card card(Rank rank, Suit suit) {
        return new Card(rank, suit);
    }
}
