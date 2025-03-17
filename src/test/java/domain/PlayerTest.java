package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PlayerTest {

    @Nested
    class ValidCases {

        @DisplayName("플레이어의 손패에서 카드를 가져온다.")
        @Test
        void retrieveCards() {
            // given
            List<TrumpCard> cards = List.of(
                    TrumpCard.ACE_OF_SPADES,
                    TrumpCard.TWO_OF_SPADES
            );
            State state = Started.of(new Hand(cards), Score.TWENTY_ONE);
            Player player = new Player(new Name("머피"), new BettingMoney(1000), state);

            // when
            List<TrumpCard> retrievedCards = player.retrieveCards();

            // then
            assertThat(retrievedCards).isEqualTo(cards);
        }

        @DisplayName("플레이어는 카드를 받아 손패에 추가한다.")
        @Test
        void receiveCard() {
            // given
            State state = Started.of(new Hand(List.of(TrumpCard.FIVE_OF_CLUBS, TrumpCard.JACK_OF_CLUBS)),
                    Score.TWENTY_ONE);
            Player player = new Player(new Name("머피"), new BettingMoney(1000), state);

            // when
            player.receiveCard(TrumpCard.TWO_OF_SPADES);

            // then
            assertThat(player.retrieveCards().size()).isEqualTo(3);
        }

        @Test
        @DisplayName("플레이어는 점수를 계산한다.")
        void calculatePlayerScore() {
            // given
            List<TrumpCard> cards = List.of(TrumpCard.ACE_OF_SPADES, TrumpCard.KING_OF_HEARTS);
            State state = Started.of(new Hand(cards), Score.TWENTY_ONE);
            Player player = new Player(new Name("머피"), new BettingMoney(1000), state);

            // when
            Score score = player.calculateScore();

            // then
            assertThat(score).isEqualTo(Score.BLACKJACK);
        }

        @ParameterizedTest
        @DisplayName("플레이어의 히트 가능 여부를 확인한다")
        @MethodSource("provideHitAllowedCases")
        void isHitAllowed(List<TrumpCard> cards, Score limitScore, boolean expected) {
            // given
            State state = Started.of(new Hand(cards), limitScore);
            Player player = new Player(new Name("머피"), new BettingMoney(1000), state);

            // when
            boolean result = player.isHitAllowed();

            // then
            assertThat(result).isEqualTo(expected);
        }

        static Stream<Arguments> provideHitAllowedCases() {
            return Stream.of(
                    Arguments.of(List.of(TrumpCard.FIVE_OF_CLUBS, TrumpCard.SIX_OF_HEARTS), Score.TWENTY_ONE, true),
                    Arguments.of(List.of(TrumpCard.SEVEN_OF_DIAMONDS, TrumpCard.TWO_OF_SPADES), Score.TWENTY_ONE, true),
                    Arguments.of(List.of(TrumpCard.ACE_OF_SPADES, TrumpCard.KING_OF_HEARTS), Score.TWENTY_ONE, false)
            );
        }
    }

    @Nested
    class InvalidCases {

        @Test
        @DisplayName("플레이어는 이름을 가져야 한다.")
        void validateNotNullName() {
            // given
            Name nullName = null;
            State state = Started.of(new Hand(List.of(TrumpCard.ACE_OF_SPADES, TrumpCard.JACK_OF_CLUBS)),
                    Score.TWENTY_ONE);

            // when & then
            assertThatThrownBy(() -> new Player(nullName, new BettingMoney(1000), state))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("플레이어는 이름과 배팅 금액을 가져야합니다.");
        }

        @Test
        @DisplayName("플레이어는 배팅 금액을 가져야 한다.")
        void validateNotNullBettingMoney() {
            // given
            BettingMoney nullBettingMoney = null;
            State state = Started.of(new Hand(List.of(TrumpCard.ACE_OF_SPADES, TrumpCard.JACK_OF_CLUBS)),
                    Score.TWENTY_ONE);

            // when & then
            assertThatThrownBy(() -> new Player(new Name("머피"), nullBettingMoney, state))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("플레이어는 이름과 배팅 금액을 가져야합니다.");
        }

        @Test
        @DisplayName("플레이어는 상태를 가져야 한다.")
        void validateNotNullState() {
            // given
            State nullState = null;

            // when & then
            assertThatThrownBy(() -> new Player(new Name("머피"), new BettingMoney(1000), nullState))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("참가자는 상태를 가져야합니다.");
        }
    }
}
