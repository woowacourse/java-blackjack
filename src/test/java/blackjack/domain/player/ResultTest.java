package blackjack.domain.player;

import blackjack.domain.card.Deck;
import blackjack.domain.fixture.FixedSequenceDeck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static blackjack.domain.fixture.CardFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

class ResultTest {

    @Test
    @DisplayName("블랙잭일 경우 입력값의 1.5배를 정수형으로 반환한다")
    void testBlackjack() {
        assertThat(Result.BLACKJACK.calculateDividend(10000L)).isEqualTo(15000L);
    }

    @Test
    @DisplayName("승리할 경우 입력값의 1.0배를 정수형으로 반환한다")
    void testWin() {
        assertThat(Result.WIN.calculateDividend(10000L)).isEqualTo(10000L);
    }

    @Test
    @DisplayName("블랙잭일 경우 입력값의 -1.0배를 정수형으로 반환한다")
    void testLose() {
        assertThat(Result.LOSE.calculateDividend(10000L)).isEqualTo(-10000L);
    }

    @Nested
    @DisplayName("calculateResult 메서드는")
    class DescribeCalculateResult {

        @Nested
        @DisplayName("블랙잭을 반환한다")
        class ItReturnsBlackjack {

            @Test
            @DisplayName("플레이어가 블랙잭이고 딜러는 블랙잭이 아닌 경우")
            void contextWithPlayerBlackjackOnly() {
                Deck deck = FixedSequenceDeck.generateDeck(
                        SPADE_JACK, SPADE_ACE,
                        SPADE_JACK, SPADE_JACK);

                Player player = new Player("pobi", deck.initialDraw());
                Dealer dealer = new Dealer(deck.initialDraw());

                assertThat(Result.calculateResult(player, dealer)).isEqualTo(Result.BLACKJACK);
            }
        }


        @Nested
        @DisplayName("승리를 반환한다")
        class It_returns_win {

            @Test
            @DisplayName("플레이어와 딜러 모두 블랙잭인 경우")
            void contextWithBothAreBlackjack() {
                Deck deck = FixedSequenceDeck.generateDeck(
                        SPADE_JACK, SPADE_ACE,
                        SPADE_JACK, SPADE_ACE);

                Player player = new Player("pobi", deck.initialDraw());
                Dealer dealer = new Dealer(deck.initialDraw());

                assertThat(Result.calculateResult(player, dealer)).isEqualTo(Result.WIN);
            }

            @Test
            @DisplayName("플레이어가 딜러보다 점수가 높으면서 블랙잭이 아닌 경우")
            void contextWithPlayerHasHigherScoreAndNotBlackjack() {
                Deck deck = FixedSequenceDeck.generateDeck(
                        SPADE_JACK, SPADE_JACK,
                        SPADE_JACK, SPADE_NINE);

                Player player = new Player("pobi", deck.initialDraw());
                Dealer dealer = new Dealer(deck.initialDraw());

                assertThat(Result.calculateResult(player, dealer)).isEqualTo(Result.WIN);
            }

            @Test
            @DisplayName("플레이어와 딜러가 점수가 같은 경우")
            void contextWithPlayerAndDealerHasSameScore() {
                Deck deck = FixedSequenceDeck.generateDeck(
                        SPADE_NINE, SPADE_NINE,
                        SPADE_NINE, SPADE_NINE);

                Player player = new Player("pobi", deck.initialDraw());
                Dealer dealer = new Dealer(deck.initialDraw());

                assertThat(Result.calculateResult(player, dealer)).isEqualTo(Result.WIN);
            }

            @Test
            @DisplayName("딜러가 버스트이고 플레이어는 버스트가 아닌 경우")
            void contextWithDealerIsBust() {
                Deck deck = FixedSequenceDeck.generateDeck(
                        SPADE_JACK, SPADE_JACK,
                        SPADE_JACK, SPADE_JACK, SPADE_TWO);

                Player player = new Player("pobi", deck.initialDraw());
                Dealer dealer = new Dealer(deck.initialDraw());
                dealer.addCard(deck.draw());

                assertThat(Result.calculateResult(player, dealer)).isEqualTo(Result.WIN);
            }
        }

        @Nested
        @DisplayName("패배를 반환한다")
        class It_returns_lose {

            @Test
            @DisplayName("플레이어가 버스트인 경우")
            void contextWithPlayerIsBust() {
                Deck deck = FixedSequenceDeck.generateDeck(
                        SPADE_JACK, SPADE_JACK, SPADE_JACK,
                        SPADE_JACK, SPADE_JACK);

                Player player = new Player("pobi", deck.initialDraw());
                player.addCard(deck.draw());
                Dealer dealer = new Dealer(deck.initialDraw());

                assertThat(Result.calculateResult(player, dealer)).isEqualTo(Result.LOSE);
            }

            @Test
            @DisplayName("플레이어와 딜러 모두 버스트인 경우")
            void contextWithBothAreBust() {
                Deck deck = FixedSequenceDeck.generateDeck(
                        SPADE_JACK, SPADE_JACK, SPADE_JACK,
                        SPADE_JACK, SPADE_JACK, SPADE_JACK);

                Player player = new Player("pobi", deck.initialDraw());
                player.addCard(deck.draw());
                Dealer dealer = new Dealer(deck.initialDraw());
                dealer.addCard(deck.draw());

                assertThat(Result.calculateResult(player, dealer)).isEqualTo(Result.LOSE);
            }

            @Test
            @DisplayName("딜러가 플레이어보다 점수가 높은 경우")
            void contextWithDealerScoreHigherThanPlayer() {
                Deck deck = FixedSequenceDeck.generateDeck(
                        SPADE_TWO, SPADE_TWO,
                        SPADE_JACK, SPADE_JACK);

                Player player = new Player("pobi", deck.initialDraw());
                Dealer dealer = new Dealer(deck.initialDraw());

                assertThat(Result.calculateResult(player, dealer)).isEqualTo(Result.LOSE);
            }
        }
    }
}
