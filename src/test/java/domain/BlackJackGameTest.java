package domain;

import static org.assertj.core.api.Assertions.assertThat;

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
        blackJackDeck = Deck.create();
        dealer = new Dealer(
                new Hand(List.of(new TrumpCard(Rank.SIX, Suit.CLUBS), new TrumpCard(Rank.SIX, Suit.SPADES))));
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
                    Arguments.of(List.of(new TrumpCard(Rank.FIVE, Suit.CLUBS), new TrumpCard(Rank.SIX, Suit.HEARTS))),
                    Arguments.of(
                            List.of(new TrumpCard(Rank.SEVEN, Suit.DIAMONDS), new TrumpCard(Rank.TWO, Suit.SPADES))),
                    Arguments.of(
                            List.of(new TrumpCard(Rank.THREE, Suit.HEARTS), new TrumpCard(Rank.THREE, Suit.DIAMONDS),
                                    new TrumpCard(Rank.TWO, Suit.SPADES)))
            );
        }

        @Test
        @DisplayName("플레이어가 히트하면 카드가 추가된다.")
        void processPlayerHit() {
            // given
            BlackJackGame blackJackGame = new BlackJackGame(blackJackDeck, dealer, rule);
            Player player = new Player("Alice",
                    Hand.of(new TrumpCard(Rank.TWO, Suit.DIAMONDS), new TrumpCard(Rank.FIVE, Suit.SPADES)));

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
            List<TrumpCard> cards = List.of(new TrumpCard(Rank.ACE, Suit.SPADES),
                    new TrumpCard(Rank.KING, Suit.HEARTS));

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
                    Hand.of(new TrumpCard(Rank.TEN, Suit.DIAMONDS), new TrumpCard(Rank.JACK, Suit.HEARTS)));
            Player player2 = new Player("Bob",
                    Hand.of(new TrumpCard(Rank.NINE, Suit.CLUBS), new TrumpCard(Rank.SEVEN, Suit.DIAMONDS)));
            List<Player> players = List.of(player1, player2);

            // when
            Map<String, GameResult> results = blackJackGame.calculateGameResults(players);

            // then
            assertThat(results).containsKeys("Alice", "Bob");
        }
    }
}
