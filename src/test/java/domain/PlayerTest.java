package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

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
            Hand hand = new Hand(cards);
            Player player = new Player(new Name("머피"), new BettingMoney(1000), hand);

            // when
            List<TrumpCard> retrievedCards = player.retrieveCards();

            // then
            assertThat(retrievedCards).isEqualTo(cards);
        }

        @DisplayName("플레이어는 카드를 받아 손패에 추가한다.")
        @Test
        void receiveCard() {
            // given
            List<TrumpCard> cards = List.of(
                    TrumpCard.ACE_OF_SPADES
            );
            Hand hand = new Hand(cards);
            Player player = new Player(new Name("머피"), new BettingMoney(1000), hand);

            // when
            player.receiveCard(TrumpCard.TWO_OF_SPADES);

            // then
            assertThat(player.retrieveCards()).isEqualTo(
                    List.of(TrumpCard.ACE_OF_SPADES, TrumpCard.TWO_OF_SPADES));
        }

        @Test
        @DisplayName("플레이어는 점수를 계산한다.")
        void calculatePlayerScore() {
            // given
            List<TrumpCard> cards = List.of(TrumpCard.ACE_OF_SPADES, TrumpCard.KING_OF_HEARTS);
            Player player = new Player(new Name("머피"), new BettingMoney(1000), new Hand(cards));
            Rule rule = new Rule();

            // when
            Score score = player.calculateScore(rule);

            // then
            assertThat(score).isEqualTo(Score.BLACKJACK);
        }

        @ParameterizedTest
        @DisplayName("플레이어의 히트 판단 여부를 판단한다")
        @MethodSource("provideDealerHitAllowedCases")
        void isHitAllowed(List<TrumpCard> cards) {
            // given
            Player player = new Player(new Name("머피"), new BettingMoney(1000), new Hand(cards));
            Rule rule = new Rule();

            // when
            boolean result = player.isHitAllowed(rule);

            // then
            assertThat(result).isTrue();
        }

        static Stream<Arguments> provideDealerHitAllowedCases() {
            return Stream.of(
                    Arguments.of(List.of(TrumpCard.FIVE_OF_CLUBS, TrumpCard.SIX_OF_HEARTS)),
                    Arguments.of(List.of(TrumpCard.SEVEN_OF_DIAMONDS, TrumpCard.TWO_OF_SPADES)),
                    Arguments.of(
                            List.of(TrumpCard.THREE_OF_HEARTS, TrumpCard.THREE_OF_DIAMONDS, TrumpCard.TWO_OF_SPADES))
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

            // when & then
            assertThatThrownBy(() -> new Player(nullName, new BettingMoney(1000), new Hand(List.of())))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("플레이어는 이름과 배팅 금액을 가져야합니다.");
        }

        @Test
        @DisplayName("플레이어는 배팅 금액을 가져야 한다.")
        void validateNotNullBettingMoney() {
            // given
            BettingMoney nullBettingMoney = null;

            // when & then
            assertThatThrownBy(() -> new Player(new Name("머피"), nullBettingMoney, new Hand(List.of())))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("플레이어는 이름과 배팅 금액을 가져야합니다.");
        }

        @Test
        @DisplayName("플레이어는 손패를 가져야 한다.")
        void validateNotNullHand() {
            // given
            Hand nullHand = null;

            // when & then
            assertThatThrownBy(() -> new Player(new Name("머피"), new BettingMoney(1000), nullHand))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("참가자는 손패를 가져야합니다.");
        }
    }
}
