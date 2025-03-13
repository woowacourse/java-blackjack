package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

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
        blackJackDeck = new Deck(Arrays.asList(TrumpCard.values()), new NoShuffle());
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
            Deck originalDeck = new Deck(Arrays.asList(TrumpCard.values()), new NoShuffle());
            Map<String, BettingMoney> playerInfos = Map.of(
                    "Alice", new BettingMoney(1000), "Bob", new BettingMoney(1000));

            // when
            List<Player> players = blackJackGame.createPlayers(playerInfos);

            // then
            assertSoftly(softly -> {
                softly.assertThat(players.getFirst().getName()).isEqualTo("Alice");
                softly.assertThat(players.getFirst().getBettingMoney()).isEqualTo(new BettingMoney(1000));
                softly.assertThat(players.getFirst().retrieveCards()).isEqualTo(
                        originalDeck.drawMultiple(2));
                softly.assertThat(players.getLast().getName()).isEqualTo("Bob");
                softly.assertThat(players.getLast().getBettingMoney()).isEqualTo(new BettingMoney(1000));
                softly.assertThat(players.getLast().retrieveCards()).isEqualTo(
                        originalDeck.drawMultiple(2));
            });
        }

        @ParameterizedTest
        @DisplayName("플레이어의 히트 판단 여부를 판단한다")
        @MethodSource("provideDealerHitAllowedCases")
        void isPlayerHitAllowed(List<TrumpCard> cards) {
            // given
            BlackJackGame blackJackGame = new BlackJackGame(blackJackDeck, dealer, rule);
            Player player = new Player("Alice", new BettingMoney(1000), new Hand(cards));

            // when
            boolean result = blackJackGame.isPlayerHitAllowed(player);

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
            Player player = new Player("Alice", new BettingMoney(1000),
                    new Hand(List.of(TrumpCard.TWO_OF_DIAMONDS, TrumpCard.FIVE_OF_SPADES)));

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
        @DisplayName("플레이어의 점수를 올바르게 계산한다.")
        void calculatePlayerScore() {
            // given
            BlackJackGame blackJackGame = new BlackJackGame(blackJackDeck, dealer, rule);
            List<TrumpCard> cards = List.of(TrumpCard.ACE_OF_SPADES, TrumpCard.KING_OF_HEARTS);
            Player player = new Player("머피", new BettingMoney(1000), new Hand(cards));

            // when
            Score score = blackJackGame.calculatePlayerScore(player);

            // then
            assertThat(score).isEqualTo(Score.BLACKJACK);
        }

        @Test
        @DisplayName("딜러의 카드를 가져온다.")
        void retrieveDealerCards() {
            // given
            List<TrumpCard> cards = List.of(TrumpCard.ACE_OF_SPADES, TrumpCard.KING_OF_HEARTS);
            Dealer dealer = new Dealer(new Hand(cards));

            BlackJackGame blackJackGame = new BlackJackGame(blackJackDeck, dealer, rule);

            // when
            List<TrumpCard> dealerCards = blackJackGame.retrieveDealerCards();

            // then
            assertThat(dealerCards).isEqualTo(cards);
        }

        @Test
        @DisplayName("딜러의 점수를 올바르게 계산한다.")
        void calculateDealerScore() {
            // given
            List<TrumpCard> cards = List.of(TrumpCard.ACE_OF_SPADES, TrumpCard.KING_OF_HEARTS);
            Dealer dealer = new Dealer(new Hand(cards));

            BlackJackGame blackJackGame = new BlackJackGame(blackJackDeck, dealer, rule);

            // when
            Score score = blackJackGame.calculateDealerScore();

            // then
            assertThat(score).isEqualTo(Score.BLACKJACK);
        }

        @Test
        @DisplayName("게임 결과를 올바르게 계산한다.")
        void calculateGameResults() {
            // given
            BlackJackGame blackJackGame = new BlackJackGame(blackJackDeck, dealer, rule);
            Player player1 = new Player("Alice", new BettingMoney(1000),
                    new Hand(List.of(TrumpCard.TEN_OF_DIAMONDS, TrumpCard.JACK_OF_HEARTS)));
            Player player2 = new Player("Bob", new BettingMoney(1000),
                    new Hand(List.of(TrumpCard.NINE_OF_CLUBS, TrumpCard.SEVEN_OF_DIAMONDS)));
            List<Player> players = List.of(player1, player2);

            // when
            Map<String, GameResult> results = blackJackGame.calculateGameResults(players);

            // then
            assertThat(results).containsKeys("Alice", "Bob");
        }

        @Test
        @DisplayName("플레이어들의 수익을 계산한다.")
        void calculatePlayersRevenueAmount() {
            // given
            Dealer dealer = new Dealer(new Hand(List.of(TrumpCard.SEVEN_OF_DIAMONDS, TrumpCard.KING_OF_HEARTS)));
            BlackJackGame blackJackGame = new BlackJackGame(blackJackDeck, dealer, rule);

            Player firstPlayer = new Player("Alice", new BettingMoney(1000),
                    new Hand(List.of(TrumpCard.ACE_OF_SPADES, TrumpCard.JACK_OF_HEARTS)));
            Player secondPlayer = new Player("Bob", new BettingMoney(1500),
                    new Hand(List.of(TrumpCard.NINE_OF_CLUBS, TrumpCard.SEVEN_OF_DIAMONDS)));
            List<Player> players = List.of(firstPlayer, secondPlayer);

            // when
            Map<String, Integer> playersRevenueAmount = blackJackGame.calculatePlayersRevenueAmount(players);

            // then
            assertSoftly(softly -> {
                softly.assertThat(playersRevenueAmount.get(firstPlayer.getName())).isEqualTo(1500);
                softly.assertThat(playersRevenueAmount.get(secondPlayer.getName())).isEqualTo(-1500);
            });
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
            Player player = new Player("Alice", new BettingMoney(1000), new Hand(cards));

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
