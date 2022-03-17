package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.fixture.FixedSequenceDeck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static blackjack.domain.card.Denomination.*;
import static blackjack.domain.card.Symbol.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Player 클래스")
class PlayerTest {

    @Test
    @DisplayName("이름은 공백일 수 없다")
    void throwExceptionWhenNameLengthIsBlank() {
        Deck deck = FixedSequenceDeck.generateDeck(new Card(CLOVER, JACK), new Card(DIAMOND, FIVE));

        assertThatThrownBy(() -> new Player(" ", deck.initialDraw()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("이름은 6자를 초과할 수 없다")
    void throwExceptionWhenNameLengthOverMaxLength() {
        Deck deck = FixedSequenceDeck.generateDeck(new Card(CLOVER, JACK), new Card(DIAMOND, FIVE));
        assertThatThrownBy(() -> new Player("1234567", deck.initialDraw()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("첫 공개 카드는 두 장을 반환한다")
    void testOpenCards() {
        Deck deck = FixedSequenceDeck.generateDeck(new Card(CLOVER, JACK), new Card(DIAMOND, FIVE));
        Player player = new Player("pobi", deck.initialDraw());

        assertThat(player.openCards().size()).isEqualTo(2);
    }

    @DisplayName("isAbleToHit 메서드는")
    @Nested
    class Describe_isAbleToHit {

        @Test
        @DisplayName("21점 미만이라면 참을 반환한다")
        void testIsAbleToHit1() {
            // given
            Deck deck = FixedSequenceDeck.generateDeck(new Card(CLOVER, JACK), new Card(DIAMOND, FIVE));
            Player player = new Player("pobi", deck.initialDraw());

            // when
            boolean actual = player.isAbleToHit();

            // then
            assertThat(actual).isTrue();
        }

        @Test
        @DisplayName("블랙잭이면 거짓을 반환한다")
        void testIsAbleToHit2() {
            // given
            Deck deck = FixedSequenceDeck.generateDeck(new Card(CLOVER, ACE), new Card(DIAMOND, JACK));
            Player player = new Player("pobi", deck.initialDraw());
            // when
            boolean actual = player.isAbleToHit();
            // then
            assertThat(actual).isFalse();
        }

        @Test
        @DisplayName("버스트면 거짓을 반환한다")
        void testIsAbleToHit3() {
            // given
            Deck deck = FixedSequenceDeck.generateDeck(new Card(CLOVER, JACK), new Card(DIAMOND, FIVE), new Card(DIAMOND, SEVEN));
            Player player = new Player("pobi", deck.initialDraw());
            player.addCard(deck.draw());

            // when
            boolean actual = player.isAbleToHit();

            // then
            assertThat(actual).isFalse();
        }

        @Test
        @DisplayName("player가 stay하면 거짓을 반환한다")
        void testIsAbleToHit4() {
            // given
            Deck deck = FixedSequenceDeck.generateDeck(new Card(CLOVER, JACK), new Card(DIAMOND, FIVE));
            Player player = new Player("pobi", deck.initialDraw());

            // when
            player.stay();
            boolean actual = player.isAbleToHit();

            // then
            assertThat(actual).isFalse();
        }

        @Test
        @DisplayName("21점이면 거짓을 반환한다")
        void testIsAbleToHit5() {
            // given
            Deck deck = FixedSequenceDeck.generateDeck(new Card(CLOVER, JACK), new Card(DIAMOND, FIVE),
                    new Card(HEART, SIX));
            Player player = new Player("pobi", deck.initialDraw());
            player.addCard(deck.draw());

            // when
            boolean actual = player.isAbleToHit();

            // then
            assertThat(actual).isFalse();
        }
    }

    @Nested
    @DisplayName("judgeResult 메서드는")
    class Describe_judgeResult {

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

                Result result = player.judgeResult(dealer);

                assertThat(result).isEqualTo(Result.BLACKJACK);
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

                Result result = player.judgeResult(dealer);

                assertThat(result).isEqualTo(Result.WIN);
            }

            @Test
            @DisplayName("플레이어가 딜러보다 점수가 높으면서 블랙잭이 아닌 경우")
            void context_with_player_has_higher_score_and_not_blackjack() {
                Deck deck = FixedSequenceDeck.generateDeck(new Card(CLOVER, JACK), new Card(DIAMOND, EIGHT),
                        new Card(HEART, JACK), new Card(SPADE, SEVEN));

                Player player = new Player("pobi", deck.initialDraw());
                Dealer dealer = new Dealer(deck.initialDraw());

                Result result = player.judgeResult(dealer);

                assertThat(result).isEqualTo(Result.WIN);
            }

            @Test
            @DisplayName("플레이어와 딜러가 점수가 같은 경우")
            void context_with_player_and_dealer_has_same_score() {
                Deck deck = FixedSequenceDeck.generateDeck(new Card(CLOVER, JACK), new Card(DIAMOND, EIGHT),
                        new Card(HEART, JACK), new Card(SPADE, EIGHT));

                Player player = new Player("pobi", deck.initialDraw());
                Dealer dealer = new Dealer(deck.initialDraw());

                Result result = player.judgeResult(dealer);

                assertThat(result).isEqualTo(Result.WIN);
            }

            @Test
            @DisplayName("딜러가 버스트이고 플레이어는 버스트가 아닌 경우")
            void context_with_dealer_is_bust() {
                Deck deck = FixedSequenceDeck.generateDeck(new Card(CLOVER, JACK), new Card(DIAMOND, EIGHT),
                        new Card(HEART, JACK), new Card(SPADE, SIX), new Card(SPADE, SEVEN));

                Player player = new Player("pobi", deck.initialDraw());
                Dealer dealer = new Dealer(deck.initialDraw());
                dealer.addCard(deck.draw());

                Result result = player.judgeResult(dealer);

                assertThat(result).isEqualTo(Result.WIN);
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

                Result result = player.judgeResult(dealer);

                assertThat(result).isEqualTo(Result.LOSE);
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

                Result result = player.judgeResult(dealer);

                assertThat(result).isEqualTo(Result.LOSE);
            }

            @Test
            @DisplayName("딜러가 플레이어보다 점수가 높은 경우")
            void context_with_dealer_score_higher_than_player() {
                Deck deck = FixedSequenceDeck.generateDeck(new Card(CLOVER, JACK), new Card(DIAMOND, EIGHT),
                        new Card(SPADE, ACE), new Card(SPADE, QUEEN));

                Player player = new Player("pobi", deck.initialDraw());
                Dealer dealer = new Dealer(deck.initialDraw());

                Result result = player.judgeResult(dealer);

                assertThat(result).isEqualTo(Result.LOSE);
            }
        }
    }
}
