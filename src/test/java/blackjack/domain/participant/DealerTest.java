package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Shape;
import blackjack.domain.card.Value;
import blackjack.domain.fixture.CardsFixture;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DealerTest {
    private static final Name DEFAULT_NAME = new Name("name");

    @DisplayName("카드의 총 점수가 16을 넘지 않으면, 카드를 더 뽑을 수 있다")
    @Test
    void isDrawableTest_whenScoreIsUnderBound_returnTrue() {
        Dealer dealer = new Dealer(CardsFixture.CARDS_SCORE_16);

        assertThat(dealer.isDrawable()).isTrue();
    }

    @DisplayName("카드의 총 점수가 17을 넘으면, 카드를 더 뽑을 수 없다")
    @Test
    void isDrawableTest_whenScoreIsOverBound_returnFalse() {
        Dealer dealer = new Dealer(CardsFixture.CARDS_SCORE_17);

        assertThat(dealer.isDrawable()).isFalse();
    }

    @DisplayName("점수를 계산할 수 있다.")
    @ParameterizedTest
    @MethodSource("cardsAndScore")
    void calculateScoreTest(List<Card> cards, int expected) {
        Dealer dealer = new Dealer(cards);

        assertThat(dealer.calculateScore()).isEqualTo(expected);
    }

    static Stream<Arguments> cardsAndScore() {
        return Stream.of(
                Arguments.of(CardsFixture.BLACKJACK, 21),
                Arguments.of(CardsFixture.TWO_ACE, 12),
                Arguments.of(CardsFixture.CARDS_SCORE_16, 16)
        );
    }

    @DisplayName("게임을 시작할 때는 카드를 두 장 뽑는다.")
    @Test
    void drawStartCardsTest() {
        Dealer dealer = new Dealer();
        Deck deck = Deck.createShuffledDeck();

        dealer.drawStartCards(deck);

        assertThat(dealer.getCards()).hasSize(2);
    }

    @DisplayName("이미 카드를 가지고 있는 경우, 시작 카드를 뽑을 수 없다.")
    @Test
    void drawStartCardsTest_whenAlreadyStarted_throwException() {
        Dealer dealer = new Dealer(CardsFixture.CARDS_SCORE_16);
        Deck deck = Deck.createShuffledDeck();

        assertThatThrownBy(() -> dealer.drawStartCards(deck))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이미 시작 카드를 뽑았습니다.");
    }

    @DisplayName("카드의 총 점수가 16을 넘지 않으면, 카드를 한 장 뽑는다")
    @Test
    void addTest_whenScoreIsUnderBound() {
        Dealer dealer = new Dealer(CardsFixture.CARDS_SCORE_16);
        Card additionalCard = new Card(Value.ACE, Shape.HEART);

        dealer.add(additionalCard);

        assertThat(dealer.getCards())
                .containsAll(CardsFixture.CARDS_SCORE_16)
                .contains(additionalCard)
                .hasSize(3);
    }

    @DisplayName("카드의 총 점수가 16을 넘으면, 카드를 뽑을 때 예외가 발생한다.")
    @Test
    void addTest_whenScoreIsOverBound_throwException() {
        Dealer dealer = new Dealer(CardsFixture.CARDS_SCORE_17);
        Card card = new Card(Value.ACE, Shape.HEART);

        assertThatThrownBy(() -> dealer.add(card))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("더 이상 카드를 추가할 수 없습니다.");
    }

    @DisplayName("플레이어와의 승패를 판단할 수 있다.")
    @Nested
    class IsWinTest {

        @DisplayName("플레이어가 21을 넘을 경우, 딜러가 이긴다.")
        @ParameterizedTest
        @MethodSource("dealerCards")
        void whenPlayerBusted_dealerWin(List<Card> cards) {
            Dealer dealer = new Dealer(cards);
            Player player = new Player(CardsFixture.BUSTED, DEFAULT_NAME);

            assertThat(dealer.isWin(player)).isTrue();
        }

        static Stream<List<Card>> dealerCards() {
            return Stream.of(
                    CardsFixture.BLACKJACK,
                    CardsFixture.CARDS_SCORE_4,
                    CardsFixture.CARDS_SCORE_16,
                    CardsFixture.BUSTED
            );
        }

        @DisplayName("딜러만 21을 넘길 경우, 플레이어가 이긴다.")
        @ParameterizedTest
        @MethodSource("playerCards")
        void whenOnlyDealerBusted_playerWin(List<Card> cards) {
            Dealer dealer = new Dealer(CardsFixture.BUSTED);
            Player player = new Player(cards, DEFAULT_NAME);

            assertThat(dealer.isWin(player)).isFalse();
        }

        static Stream<List<Card>> playerCards() {
            return Stream.of(
                    CardsFixture.BLACKJACK,
                    CardsFixture.CARDS_SCORE_4,
                    CardsFixture.CARDS_SCORE_16
            );
        }

        @DisplayName("플레이어만 블랙잭일 경우, 플레이어가 이긴다.")
        @Test
        void whenPlayerOnlyBlackjack_playerWin() {
            Player player = new Player(CardsFixture.BLACKJACK, DEFAULT_NAME);
            Dealer dealer = new Dealer(CardsFixture.CARDS_SCORE_21);

            assertThat(dealer.isWin(player)).isFalse();
        }

        @DisplayName("딜러만 블랙잭일 경우, 딜러가 이긴다.")
        @Test
        void whenDealerOnlyBlackjack_dealerWin() {
            Player player = new Player(CardsFixture.CARDS_SCORE_21, DEFAULT_NAME);
            Dealer dealer = new Dealer(CardsFixture.BLACKJACK);

            assertThat(dealer.isWin(player)).isTrue();
        }

        @DisplayName("둘 다 21을 넘지 않을 경우, 플레이어가 딜러의 숫자보다 같다면 딜러가 이긴다.")
        @ParameterizedTest
        @MethodSource("sameCards")
        void whenPlayerScoreIsEqualToDealerScore_dealerWin(List<Card> cards) {
            Player player = new Player(cards, DEFAULT_NAME);
            Dealer dealer = new Dealer(cards);

            assertThat(dealer.isWin(player)).isTrue();
        }

        static Stream<List<Card>> sameCards() {
            return Stream.of(
                    CardsFixture.BLACKJACK,
                    CardsFixture.CARDS_SCORE_21,
                    CardsFixture.CARDS_SCORE_16
            );
        }

        @DisplayName("둘 다 21보다 작을 경우, 플레이어가 딜러의 숫자보다 크다면 플레이어가 이긴다.")
        @Test
        void whenPlayerScoreIsBiggerThanDealerScore_playerWin() {
            Player player = new Player(CardsFixture.CARDS_SCORE_17, DEFAULT_NAME);
            Dealer dealer = new Dealer(CardsFixture.CARDS_SCORE_16);

            assertThat(dealer.isWin(player)).isFalse();
        }

        @DisplayName("둘 다 21보다 작을 경우, 플레이어가 딜러의 숫자보다 작다면 딜러가 이긴다.")
        @Test
        void whenPlayerScoreIsSmallerThanDealerScore_dealerWin() {
            Player player = new Player(CardsFixture.CARDS_SCORE_16, DEFAULT_NAME);
            Dealer dealer = new Dealer(CardsFixture.CARDS_SCORE_17);

            assertThat(dealer.isWin(player)).isTrue();
        }
    }
}
