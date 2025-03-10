package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.assertj.core.api.AssertionsForClassTypes;
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

        @Test
        @DisplayName("플레이어가 정상적으로 생성되어야 한다.")
        void createPlayers() {
            // given
            BlackJackGame blackJackGame = new BlackJackGame(blackJackDeck, dealer, rule);
            List<String> playerNames = List.of("Alice", "Bob");
            Deck originalDeck = new Deck(Arrays.stream(TrumpCard.values()).toList());

            // when
            List<Player> players = blackJackGame.createPlayers(playerNames);

            // then
            assertSoftly(softly -> {
                softly.assertThat(players.getFirst().getName()).isEqualTo("Alice");
                softly.assertThat(players.getFirst().retrieveCards()).isEqualTo(
                        originalDeck.drawMultiple(2));
                softly.assertThat(players.getLast().getName()).isEqualTo("Bob");
                softly.assertThat(players.getLast().retrieveCards()).isEqualTo(
                        originalDeck.drawMultiple(2));
            });
        }

        @DisplayName("딜러의 첫번째 카드를 가져온다.")
        @Test
        void retrieveFirstCard() {
            // given
            List<TrumpCard> cards = List.of(
                    TrumpCard.ACE_OF_SPADES,
                    TrumpCard.TWO_OF_SPADES
            );
            Hand hand = new Hand(cards);
            Dealer dealer = new Dealer(hand);

            BlackJackGame blackJackGame = new BlackJackGame(blackJackDeck, dealer, rule);

            // when
            TrumpCard dealerFirstCard = blackJackGame.retrieveDealerFirstCard();

            // then
            AssertionsForClassTypes.assertThat(dealerFirstCard).isEqualTo(TrumpCard.ACE_OF_SPADES);
        }

        @ParameterizedTest
        @DisplayName("플레이어의 히트 판단 여부를 판단한다")
        @MethodSource("provideDealerHitAllowedCases")
        void isPlayerHitAllowed(List<TrumpCard> cards) {
            // given
            BlackJackGame blackJackGame = new BlackJackGame(blackJackDeck, dealer, rule);
            Player player = new Player("Alice", new Hand(cards));

            // when
            boolean result = blackJackGame.isPlayerHitAllowed(player.retrieveCards());

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
            Player player = new Player("Alice", new Hand(List.of(TrumpCard.TWO_OF_DIAMONDS, TrumpCard.FIVE_OF_SPADES)));

            // when
            blackJackGame.processPlayerHit(player);

            // then
            assertThat(player.retrieveCards()).hasSize(3);
        }

        @Test
        @DisplayName("딜러가 히트하면 적절한 횟수만큼 카드가 추가된다.")
        void processDealerHit() {
            // given
            BlackJackGame blackJackGame = new BlackJackGame(blackJackDeck, dealer, rule);
            int initialCards = dealer.retrieveCards().size();

            // when
            int hitCount = blackJackGame.processDealerHit();

            // then
            assertThat(dealer.retrieveCards()).hasSize(initialCards + hitCount);
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
            Player player1 = new Player("Alice",
                    new Hand(List.of(TrumpCard.TEN_OF_DIAMONDS, TrumpCard.JACK_OF_HEARTS)));
            Player player2 = new Player("Bob", new Hand(List.of(TrumpCard.NINE_OF_CLUBS, TrumpCard.SEVEN_OF_DIAMONDS)));
            List<Player> players = List.of(player1, player2);

            // when
            Map<String, GameResult> results = blackJackGame.calculateGameResults(players);

            // then
            assertThat(results).containsKeys("Alice", "Bob");
        }
    }

    @Nested
    class InvalidCases {

        @DisplayName("플레이어의 이름은 중복될 수 없다.")
        @Test
        void createPlayers() {
            // given
            BlackJackGame blackJackGame = new BlackJackGame(blackJackDeck, dealer, rule);
            List<String> playerNames = List.of("머피", "머피");

            // when & then
            assertThatThrownBy(() -> blackJackGame.createPlayers(playerNames))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("플레이어의 이름은 중복될 수 없습니다.");
        }

        @DisplayName("딜러의 첫번째 카드를 가져올 때 딜러는 2장의 카드를 가지고 있어야 한다.")
        @Test
        void retrieveFirstCard() {
            // given
            List<TrumpCard> cards = List.of(
                    TrumpCard.ACE_OF_SPADES
            );
            Hand hand = new Hand(cards);
            Dealer dealer = new Dealer(hand);

            BlackJackGame blackJackGame = new BlackJackGame(blackJackDeck, dealer, rule);

            // when & then
            assertThatThrownBy(blackJackGame::retrieveDealerFirstCard)
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("딜러는 " + BlackJackGame.INITIAL_CARD_COUNT + "장의 카드를 가지고 있어야 합니다.");
        }

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
