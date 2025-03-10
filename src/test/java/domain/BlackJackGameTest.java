package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BlackJackGameTest {

    private Deck blackJackDeck;
    private Dealer dealer;
    private Rule rule;

    @BeforeEach
    void setUp() {
        blackJackDeck = new Deck(Arrays.stream(TrumpCard.values()).toList());
        dealer = new Dealer(new Hand(List.of(TrumpCard.ACE_OF_SPADES, TrumpCard.TWO_OF_HEARTS)));
        rule = new Rule();
    }

    @Nested
    class ValidCases {


        @ParameterizedTest
        @DisplayName("플레이어의 히트 판단 여부를 판단한다")
        @MethodSource("provideDealerHitAllowedCases")
        void isPlayerHitAllowed(List<TrumpCard> cards) {
            // given
            BlackJackGame blackJackGame = new BlackJackGame(blackJackDeck, dealer, rule);
            Player player = new Player("Alice", new Hand(cards));

            // when
            boolean result = blackJackGame.isPlayerHitAllowed(player.getHand().getCards());

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

        @Test
        @DisplayName("플레이어가 히트하면 카드가 추가된다.")
        void processPlayerHit() {
            // given
            BlackJackGame blackJackGame = new BlackJackGame(blackJackDeck, dealer, rule);
            Player player = new Player("Alice", Hand.of(TrumpCard.TWO_OF_DIAMONDS, TrumpCard.FIVE_OF_SPADES));

            // when
            blackJackGame.processPlayerHit(player);

            // then
            assertThat(player.getHand().getCards()).hasSize(3);
        }

        @Test
        @DisplayName("딜러가 히트하면 적절한 횟수만큼 카드가 추가된다.")
        void processDealerHit() {
            // given
            BlackJackGame blackJackGame = new BlackJackGame(blackJackDeck, dealer, rule);
            int initialCards = dealer.getHand().getCards().size();

            // when
            int hitCount = blackJackGame.processDealerHit();

            // then
            assertThat(dealer.getHand().getCards()).hasSize(initialCards + hitCount);
        }

        @Test
        @DisplayName("주어진 카드 목록의 점수를 올바르게 계산한다.")
        void calculateScore() {
            // given
            BlackJackGame blackJackGame = new BlackJackGame(blackJackDeck, dealer, rule);
            List<TrumpCard> cards = List.of(TrumpCard.ACE_OF_SPADES, TrumpCard.KING_OF_HEARTS);

            // when
            Score score = blackJackGame.caculateScore(cards);

            // then
            assertThat(score).isEqualTo(Score.BLACKJACK);
        }

        @Test
        @DisplayName("게임 결과를 올바르게 계산한다.")
        void calculateGameResults() {
            // given
            BlackJackGame blackJackGame = new BlackJackGame(blackJackDeck, dealer, rule);
            Player player1 = new Player("Alice", Hand.of(TrumpCard.TEN_OF_DIAMONDS, TrumpCard.JACK_OF_HEARTS));
            Player player2 = new Player("Bob", Hand.of(TrumpCard.NINE_OF_CLUBS, TrumpCard.SEVEN_OF_DIAMONDS));
            List<Player> players = List.of(player1, player2);

            // when
            Map<String, GameResult> results = blackJackGame.calculateGameResults(players);

            // then
            assertThat(results).containsKeys("Alice", "Bob");
        }
    }

    @Nested
    class InvalidCases {

        @ParameterizedTest
        @DisplayName("플레이어가 히트 할 수 없다면 히트를 할 수 없다.")
        @MethodSource("provideHitNotAllowedCases")
        void processPlayerHit(List<TrumpCard> cards) {
            // given
            BlackJackGame blackJackGame = new BlackJackGame(blackJackDeck, dealer, rule);
            Player player = new Player("Alice", new Hand(cards));

            // when & then
            assertThatThrownBy(() -> blackJackGame.processPlayerHit(player))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("플레이어는 더이상 히트할 수 없습니다.");
        }

        static Stream<Arguments> provideHitNotAllowedCases() {
            return Stream.of(
                    Arguments.of(List.of(TrumpCard.TEN_OF_SPADES, TrumpCard.ACE_OF_HEARTS)),
                    Arguments.of(
                            List.of(TrumpCard.KING_OF_DIAMONDS, TrumpCard.QUEEN_OF_SPADES, TrumpCard.TWO_OF_HEARTS)),
                    Arguments.of(List.of(TrumpCard.JACK_OF_CLUBS, TrumpCard.KING_OF_HEARTS, TrumpCard.FOUR_OF_SPADES))
            );
        }
    }
}
