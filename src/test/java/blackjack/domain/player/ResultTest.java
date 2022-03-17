package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.fixture.FixedSequenceDeck;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static blackjack.domain.card.Denomination.*;
import static blackjack.domain.card.Denomination.QUEEN;
import static blackjack.domain.card.Symbol.*;
import static blackjack.domain.card.Symbol.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

class ResultTest {

    @Test
    @DisplayName("블랙잭일 경우 입력값의 1.5배를 정수형으로 반환한다")
    void testBlackjack() {
        Assertions.assertThat(Result.BLACKJACK.calculateDividend(10000L)).isEqualTo(15000L);
    }

    @Test
    @DisplayName("승리할 경우 입력값의 1.0배를 정수형으로 반환한다")
    void testWin() {
        Assertions.assertThat(Result.WIN.calculateDividend(10000L)).isEqualTo(10000L);
    }

    @Test
    @DisplayName("블랙잭일 경우 입력값의 -1.0배를 정수형으로 반환한다")
    void testLose() {
        Assertions.assertThat(Result.LOSE.calculateDividend(10000L)).isEqualTo(-10000L);
    }

    @Nested
    @DisplayName("calculateResult 메서드는")
    class Describe_calculateResult {

        @Nested
        @DisplayName("블랙잭을 반환한다")
        class It_returns_blackjack {

            @Test
            @DisplayName("플레이어가 블랙잭이고 딜러는 블랙잭이 아닌 경우")
            void context_with_player_blackjack_only() {
                Deck deck = FixedSequenceDeck.generateDeck(new Card(CLOVER, JACK), new Card(DIAMOND, ACE),
                        new Card(HEART, SEVEN), new Card(SPADE, QUEEN));

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
            void context_with_both_are_blackjack() {
                Deck deck = FixedSequenceDeck.generateDeck(new Card(CLOVER, JACK), new Card(DIAMOND, ACE),
                        new Card(HEART, JACK), new Card(SPADE, ACE));

                Player player = new Player("pobi", deck.initialDraw());
                Dealer dealer = new Dealer(deck.initialDraw());

                assertThat(Result.calculateResult(player, dealer)).isEqualTo(Result.WIN);
            }

            @Test
            @DisplayName("플레이어가 딜러보다 점수가 높으면서 블랙잭이 아닌 경우")
            void context_with_player_has_higher_score_and_not_blackjack() {
                Deck deck = FixedSequenceDeck.generateDeck(new Card(CLOVER, JACK), new Card(DIAMOND, EIGHT),
                        new Card(HEART, JACK), new Card(SPADE, SEVEN));

                Player player = new Player("pobi", deck.initialDraw());
                Dealer dealer = new Dealer(deck.initialDraw());

                assertThat(Result.calculateResult(player, dealer)).isEqualTo(Result.WIN);
            }

            @Test
            @DisplayName("플레이어와 딜러가 점수가 같은 경우")
            void context_with_player_and_dealer_has_same_score() {
                Deck deck = FixedSequenceDeck.generateDeck(new Card(CLOVER, JACK), new Card(DIAMOND, EIGHT),
                        new Card(HEART, JACK), new Card(SPADE, EIGHT));

                Player player = new Player("pobi", deck.initialDraw());
                Dealer dealer = new Dealer(deck.initialDraw());

                assertThat(Result.calculateResult(player, dealer)).isEqualTo(Result.WIN);
            }

            @Test
            @DisplayName("딜러가 버스트이고 플레이어는 버스트가 아닌 경우")
            void context_with_dealer_is_bust() {
                Deck deck = FixedSequenceDeck.generateDeck(new Card(CLOVER, JACK), new Card(DIAMOND, EIGHT),
                        new Card(HEART, JACK), new Card(SPADE, SIX), new Card(SPADE, SEVEN));

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
            void context_with_player_is_bust() {
                Deck deck = FixedSequenceDeck.generateDeck(new Card(CLOVER, JACK), new Card(DIAMOND, EIGHT),
                        new Card(HEART, JACK), new Card(SPADE, SEVEN), new Card(SPADE, QUEEN));

                Player player = new Player("pobi", deck.initialDraw());
                player.addCard(deck.draw());
                Dealer dealer = new Dealer(deck.initialDraw());

                assertThat(Result.calculateResult(player, dealer)).isEqualTo(Result.LOSE);
            }

            @Test
            @DisplayName("플레이어와 딜러 모두 버스트인 경우")
            void context_with_both_are_bust() {
                Deck deck = FixedSequenceDeck.generateDeck(new Card(CLOVER, JACK), new Card(DIAMOND, EIGHT),
                        new Card(HEART, JACK), new Card(SPADE, SEVEN), new Card(SPADE, SIX), new Card(HEART, QUEEN));

                Player player = new Player("pobi", deck.initialDraw());
                player.addCard(deck.draw());
                Dealer dealer = new Dealer(deck.initialDraw());
                dealer.addCard(deck.draw());

                assertThat(Result.calculateResult(player, dealer)).isEqualTo(Result.LOSE);
            }

            @Test
            @DisplayName("딜러가 플레이어보다 점수가 높은 경우")
            void context_with_dealer_score_higher_than_player() {
                Deck deck = FixedSequenceDeck.generateDeck(new Card(CLOVER, JACK), new Card(DIAMOND, EIGHT),
                        new Card(SPADE, ACE), new Card(SPADE, QUEEN));

                Player player = new Player("pobi", deck.initialDraw());
                Dealer dealer = new Dealer(deck.initialDraw());

                assertThat(Result.calculateResult(player, dealer)).isEqualTo(Result.LOSE);
            }
        }
    }
}
