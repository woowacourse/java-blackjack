package domain;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerOutcomeTest {
    @Nested
    class 플레이어만2장21점이면블랙잭 {
        @Test
        void should_BLACKJACK이다_when_플레이어만21점이면() {
            // given
            final Hand playerHand = new Hand();
            final Hand dealerHand = new Hand();
            playerHand.add(Card.of(Suit.SPADE, Number.ACE));
            playerHand.add(Card.of(Suit.SPADE, Number.TEN));
            dealerHand.add(Card.of(Suit.SPADE, Number.KING));
            dealerHand.add(Card.of(Suit.SPADE, Number.TEN));

            // when
            final PlayerOutcome playerOutcome = PlayerOutcome.of(playerHand, dealerHand);

            // then
            assertThat(playerOutcome).isEqualTo(PlayerOutcome.BLACKJACK);
        }

        @Test
        void should_BLACKJACK이아니다_when_둘다21점이면() {
            // given
            final Hand playerHand = new Hand();
            final Hand dealerHand = new Hand();
            playerHand.add(Card.of(Suit.SPADE, Number.ACE));
            playerHand.add(Card.of(Suit.SPADE, Number.TEN));
            dealerHand.add(Card.of(Suit.SPADE, Number.ACE));
            dealerHand.add(Card.of(Suit.SPADE, Number.TEN));

            // when
            final PlayerOutcome playerOutcome = PlayerOutcome.of(playerHand, dealerHand);

            // then
            assertThat(playerOutcome).isNotEqualTo(PlayerOutcome.BLACKJACK);
        }

        @Test
        void should_BLACKJACK이아니다_when_두장21점이아니면() {
            // given
            final Hand playerHand = new Hand();
            final Hand dealerHand = new Hand();
            playerHand.add(Card.of(Suit.SPADE, Number.SEVEN));
            playerHand.add(Card.of(Suit.SPADE, Number.FOUR));
            playerHand.add(Card.of(Suit.SPADE, Number.TEN));
            dealerHand.add(Card.of(Suit.SPADE, Number.THREE));
            dealerHand.add(Card.of(Suit.SPADE, Number.TEN));

            // when
            final PlayerOutcome playerOutcome = PlayerOutcome.of(playerHand, dealerHand);

            // then
            assertThat(playerOutcome).isNotEqualTo(PlayerOutcome.BLACKJACK);
        }
    }

    @Nested
    class 플레이어가버스트면패배 {
        @Test
        void should_LOSE이다_when_플레이어가버스트면() {
            // given
            final Hand playerHand = new Hand();
            final Hand dealerHand = new Hand();
            playerHand.add(Card.of(Suit.SPADE, Number.TEN));
            playerHand.add(Card.of(Suit.SPADE, Number.KING));
            playerHand.add(Card.of(Suit.SPADE, Number.THREE));
            dealerHand.add(Card.of(Suit.SPADE, Number.TEN));
            dealerHand.add(Card.of(Suit.SPADE, Number.KING));
            dealerHand.add(Card.of(Suit.SPADE, Number.THREE));

            // when
            final PlayerOutcome playerOutcome = PlayerOutcome.of(playerHand, dealerHand);

            // then
            assertThat(playerOutcome).isEqualTo(PlayerOutcome.LOSS);
        }
    }

    @Nested
    class 점수로승패를판단 {
        @Test
        void should_플레이어승리_when_딜러가버스트면() {
            //given
            Hand playerHand = new Hand();
            playerHand.add(Card.of(Suit.SPADE, Number.KING));
            playerHand.add(Card.of(Suit.SPADE, Number.SIX));

            Hand dealerHand = new Hand();
            dealerHand.add(Card.of(Suit.SPADE, Number.KING));
            dealerHand.add(Card.of(Suit.SPADE, Number.JACK));
            dealerHand.add(Card.of(Suit.SPADE, Number.SIX));

            //when
            PlayerOutcome actual = PlayerOutcome.of(playerHand, dealerHand);

            //then
            assertThat(actual).isEqualTo(PlayerOutcome.WIN);
        }

        @Test
        void should_플레이어승리_when_딜러가점수가더높으면() {
            //given
            Hand playerHand = new Hand();
            playerHand.add(Card.of(Suit.SPADE, Number.KING));
            playerHand.add(Card.of(Suit.SPADE, Number.SIX));

            Hand dealerHand = new Hand();
            dealerHand.add(Card.of(Suit.SPADE, Number.KING));
            dealerHand.add(Card.of(Suit.SPADE, Number.FIVE));

            //when
            PlayerOutcome actual = PlayerOutcome.of(playerHand, dealerHand);

            //then
            assertThat(actual).isEqualTo(PlayerOutcome.WIN);
        }

        @Test
        void should_플레이어패배_when_딜러가점수가더낮으면() {
            //given
            Hand playerHand = new Hand();
            playerHand.add(Card.of(Suit.SPADE, Number.KING));
            playerHand.add(Card.of(Suit.SPADE, Number.SIX));

            Hand dealerHand = new Hand();
            dealerHand.add(Card.of(Suit.SPADE, Number.KING));
            dealerHand.add(Card.of(Suit.SPADE, Number.SEVEN));

            //when
            PlayerOutcome actual = PlayerOutcome.of(playerHand, dealerHand);

            //then
            assertThat(actual).isEqualTo(PlayerOutcome.LOSS);
        }

        @Test
        void should_플레이어무승부_when_딜러와플레이어점수가같으면() {
            //given
            Hand playerHand = new Hand();
            playerHand.add(Card.of(Suit.SPADE, Number.KING));
            playerHand.add(Card.of(Suit.SPADE, Number.SIX));

            Hand dealerHand = new Hand();
            dealerHand.add(Card.of(Suit.SPADE, Number.KING));
            dealerHand.add(Card.of(Suit.SPADE, Number.SIX));

            //when
            PlayerOutcome actual = PlayerOutcome.of(playerHand, dealerHand);

            //then
            assertThat(actual).isEqualTo(PlayerOutcome.DRAW);
        }
    }

    @Nested
    class 결과에따라다른수익률 {
        @Test
        void should_250퍼센트수익률_when_BLACKJACK이면() {
            // given
            int bettingAmount = 100;
            int expected = 150;

            // when
            int actual = PlayerOutcome.BLACKJACK.calculateEarning(100);

            // then
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void should_200퍼센트수익률_when_WIN이면() {
            // given
            int bettingAmount = 100;
            int expected = 100;

            // when
            int actual = PlayerOutcome.WIN.calculateEarning(100);

            // then
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void should_돈이그대로이다_when_DRAW이면() {
            // given
            int bettingAmount = 100;
            int expected = 0;

            // when
            int actual = PlayerOutcome.DRAW.calculateEarning(100);

            // then
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void should_모두잃는다_when_LOSS이면() {
            // given
            int bettingAmount = 100;
            int expected = -100;

            // when
            int actual = PlayerOutcome.LOSS.calculateEarning(100);

            // then
            assertThat(actual).isEqualTo(expected);
        }
    }
}
