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

    private Deck deck;
    private Dealer dealer;
    private Map<Name, Player> players;

    @BeforeEach
    void setUp() {
        deck = new Deck(Arrays.asList(TrumpCard.values()), new NoShuffle());
        dealer = new Dealer(
                Started.of(new Hand(deck.drawMultiple(Started.INITIAL_CARD_COUNT)), Score.SEVENTEEN));
        players = Map.of(
                new Name("머피"), new Player(new Name("머피"), new BettingMoney(1000),
                        Started.of(new Hand(deck.drawMultiple(Started.INITIAL_CARD_COUNT)), Score.TWENTY_ONE)),
                new Name("매트"), new Player(new Name("매트"), new BettingMoney(2000),
                        Started.of(new Hand(deck.drawMultiple(Started.INITIAL_CARD_COUNT)), Score.TWENTY_ONE))
        );
    }

    @Nested
    class ValidCases {

        @Test
        @DisplayName("특정 플레이어의 카드를 가져온다")
        void retrievePlayerCards() {
            // given
            Name playerName = new Name("머피");
            Deck deck = new Deck(List.of(TrumpCard.values()), new NoShuffle());
            Dealer dealer = new Dealer(
                    new Hit(new Hand(List.of(TrumpCard.TWO_OF_DIAMONDS, TrumpCard.FOUR_OF_SPADES)), Score.TWENTY_ONE));
            Player player = new Player(playerName, new BettingMoney(1000),
                    new Hit(new Hand(List.of(TrumpCard.FIVE_OF_CLUBS, TrumpCard.SIX_OF_HEARTS)), Score.TWENTY_ONE));
            BlackJackGame blackJackGame = new BlackJackGame(deck, dealer, Map.of(playerName, player));

            // when
            List<TrumpCard> playerCards = blackJackGame.retrievePlayerCards(playerName);

            // then
            assertThat(playerCards).hasSize(2);
        }

        @Test
        @DisplayName("딜러의 첫 번째 카드를 가져온다")
        void retrieveDealerFirstCard() {
            // given
            Deck deck = new Deck(List.of(TrumpCard.values()), new NoShuffle());
            Dealer dealer = new Dealer(
                    new Hit(new Hand(List.of(TrumpCard.THREE_OF_HEARTS, TrumpCard.NINE_OF_DIAMONDS)),
                            Score.TWENTY_ONE));
            BlackJackGame blackJackGame = new BlackJackGame(deck, dealer, Map.of());

            // when
            TrumpCard firstCard = blackJackGame.retrieveDealerFirstCard();

            // then
            assertThat(firstCard).isEqualTo(TrumpCard.THREE_OF_HEARTS);
        }

        @ParameterizedTest
        @DisplayName("플레이어의 히트 가능 여부를 확인한다")
        @MethodSource("providePlayerHitAllowedCases")
        void isPlayerHitAllowed(State state, boolean expected) {
            // given
            Name playerName = new Name("머피");
            players = Map.of(playerName, new Player(playerName, new BettingMoney(1000), state));
            BlackJackGame blackJackGame = new BlackJackGame(deck, dealer, players);

            // when
            boolean result = blackJackGame.isPlayerHitAllowed(playerName);

            // then
            assertThat(result).isEqualTo(expected);
        }

        static Stream<Arguments> providePlayerHitAllowedCases() {
            return Stream.of(
                    Arguments.of(new Hit(new Hand(List.of(TrumpCard.FIVE_OF_CLUBS, TrumpCard.SIX_OF_HEARTS)),
                            Score.TWENTY_ONE), true),
                    Arguments.of(new Stay(new Hand(List.of(TrumpCard.SEVEN_OF_DIAMONDS, TrumpCard.TWO_OF_SPADES))),
                            false),
                    Arguments.of(new BlackJack(new Hand(List.of(TrumpCard.ACE_OF_SPADES, TrumpCard.JACK_OF_CLUBS))),
                            false),
                    Arguments.of(new Bust(new Hand(
                                    List.of(TrumpCard.KING_OF_DIAMONDS, TrumpCard.QUEEN_OF_SPADES, TrumpCard.TWO_OF_HEARTS))),
                            false)
            );
        }

        @Test
        @DisplayName("플레이어가 히트하면 카드가 추가된다.")
        void processPlayerHit() {
            // given
            Name playerName = new Name("머피");
            players = Map.of(
                    playerName, new Player(playerName, new BettingMoney(1000),
                            Started.of(new Hand(List.of(TrumpCard.TWO_OF_DIAMONDS, TrumpCard.FIVE_OF_SPADES)),
                                    Score.TWENTY_ONE)));
            BlackJackGame blackJackGame = new BlackJackGame(deck, dealer, players);

            // when
            blackJackGame.processPlayerHit(playerName);

            // then
            assertThat(blackJackGame.retrievePlayerCards(playerName)).hasSize(3);
        }

        @Test
        @DisplayName("딜러가 히트하면 적절한 횟수만큼 카드가 추가된다.")
        void processDealerHit() {
            // given
            BlackJackGame blackJackGame = new BlackJackGame(deck, dealer, players);
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
            Name playerName = new Name("머피");
            players = Map.of(
                    playerName, new Player(playerName, new BettingMoney(1000),
                            Started.of(new Hand(List.of(TrumpCard.ACE_OF_SPADES, TrumpCard.JACK_OF_CLUBS)),
                                    Score.TWENTY_ONE)));
            BlackJackGame blackJackGame = new BlackJackGame(deck, dealer, players);

            // when
            Score score = blackJackGame.calculatePlayerScore(playerName);

            // then
            assertThat(score).isEqualTo(Score.BLACKJACK);
        }

        @Test
        @DisplayName("딜러의 카드를 가져온다.")
        void retrieveDealerCards() {
            // given
            List<TrumpCard> cards = List.of(TrumpCard.ACE_OF_SPADES, TrumpCard.KING_OF_HEARTS);
            Dealer dealer = new Dealer(Started.of(new Hand(cards), Score.TWENTY_ONE));

            BlackJackGame blackJackGame = new BlackJackGame(deck, dealer, players);

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
            Dealer dealer = new Dealer(Started.of(new Hand(cards), Score.TWENTY_ONE));

            BlackJackGame blackJackGame = new BlackJackGame(deck, dealer, players);

            // when
            Score score = blackJackGame.calculateDealerScore();

            // then
            assertThat(score).isEqualTo(Score.BLACKJACK);
        }

        @Test
        @DisplayName("플레이어들의 수익을 계산한다.")
        void calculatePlayersRevenueAmount() {
            // given
            Name playerName = new Name("머피");
            players = Map.of(
                    playerName, new Player(playerName, new BettingMoney(1000),
                            Started.of(new Hand(List.of(TrumpCard.ACE_OF_SPADES, TrumpCard.JACK_OF_CLUBS)),
                                    Score.TWENTY_ONE)));
            BlackJackGame blackJackGame = new BlackJackGame(deck, dealer, players);

            // when
            Integer playerRevenueAmount = blackJackGame.calculatePlayerRevenueAmount(playerName);

            // then
            assertThat(playerRevenueAmount).isEqualTo(1500);
        }
    }

    @Nested
    class InvalidCases {

        @Test
        @DisplayName("존재하지 않는 플레이어를 조회하면 예외가 발생한다")
        void validateContain() {
            // given
            Name nonExistentPlayer = new Name("존재하지 않는 플레이어");
            Deck deck = new Deck(List.of(TrumpCard.values()), new NoShuffle());
            Dealer dealer = new Dealer(
                    new Hit(new Hand(List.of(TrumpCard.SEVEN_OF_CLUBS, TrumpCard.FOUR_OF_SPADES)), Score.TWENTY_ONE));
            BlackJackGame blackJackGame = new BlackJackGame(deck, dealer, Map.of());

            // when & then
            assertThatThrownBy(() -> blackJackGame.retrievePlayerCards(nonExistentPlayer))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("플레이어가 존재하지 않습니다.");
        }

        @ParameterizedTest
        @DisplayName("플레이어가 히트 할 수 없다면 히트를 시도하면 예외가 발생한다")
        @MethodSource("provideHitNotAllowedCases")
        void processPlayerHit(State state) {
            // given
            Name playerName = new Name("머피");
            players = Map.of(playerName, new Player(playerName, new BettingMoney(1000), state));
            BlackJackGame blackJackGame = new BlackJackGame(deck, dealer, players);

            // when & then
            assertThatThrownBy(() -> blackJackGame.processPlayerHit(playerName))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("플레이어는 더이상 히트할 수 없습니다.");
        }

        static Stream<Arguments> provideHitNotAllowedCases() {
            return Stream.of(
                    Arguments.of(new Stay(new Hand(List.of(TrumpCard.TEN_OF_SPADES, TrumpCard.ACE_OF_HEARTS)))),
                    Arguments.of(new BlackJack(new Hand(List.of(TrumpCard.ACE_OF_SPADES, TrumpCard.QUEEN_OF_SPADES)))),
                    Arguments.of(new Bust(new Hand(
                            List.of(TrumpCard.JACK_OF_CLUBS, TrumpCard.KING_OF_HEARTS, TrumpCard.FOUR_OF_SPADES))))
            );
        }
    }
}
