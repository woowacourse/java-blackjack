package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import domain.card.Card;
import domain.card.CardSelector;
import domain.card.Deck;
import domain.card.Denomination;
import domain.card.RandomUniqueCardSelector;
import domain.card.Shape;
import domain.game.GameResult;
import domain.helper.StubCardSelector;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class DealerTest {

    private Dealer dealer;

    @BeforeEach
    void beforeEach() {
        dealer = Dealer.create("딜러");
    }

    @Test
    @DisplayName("create()는 호출하면 딜러를 생성한다")
    void create_whenCall_thenSuccess() {
        final Dealer dealer = assertDoesNotThrow(() -> Dealer.create("딜러"));

        assertThat(dealer)
                .isExactlyInstanceOf(Dealer.class);
    }

    @RepeatedTest(value = 10, name = "playDealerTurn() {currentRepetition} / {totalRepetitions}")
    @DisplayName("playDealerTurn()은 호출하면 딜러의 카드 합이 17일 때 까지 카드를 뽑아서 추가한다")
    void addCard_givenCard_thenSuccess() {
        // given
        final CardSelector cardSelector = new RandomUniqueCardSelector();
        final Deck deck = Deck.create(cardSelector);

        // when
        dealer.playDealerTurn(deck);

        // then
        final int score = dealer.calculateScore();

        assertThat(score)
                .isGreaterThan(16);
    }

    @RepeatedTest(value = 10, name = "getStartCard() {currentRepetition} / {totalRepetitions}")
    @DisplayName("getStartCard()는 호출하면 딜러의 첫 번째 카드를 조회한다")
    void getStartCard_whenCall_thenReturnFirstCard() {
        // given
        final List<Integer> cardOrders = List.of(0, 1, 2, 3, 4, 5, 6);
        final CardSelector cardSelector = new StubCardSelector(cardOrders);
        final Deck deck = Deck.create(cardSelector);

        dealer.playDealerTurn(deck);

        // when
        final ParticipantCard dealerCard = dealer.participantCard();
        final List<Card> actual = dealerCard.getCards();

        // then
        final Card actualCard = actual.get(0);
        final Card expectedCard = Card.of(Shape.HEART, Denomination.ACE);

        assertThat(actualCard)
                .isSameAs(expectedCard);
    }

    @Test
    @DisplayName("calculateScore()는 호출하면 현재 손패의 모든 카드의 점수 합을 반환한다.")
    void calculateScore_whenCall_thenReturnScore() {
        // given
        final List<Integer> cardOrders = List.of(1, 2, 3, 4, 5);
        final CardSelector cardSelector = new StubCardSelector(cardOrders);
        final Deck deck = Deck.create(cardSelector);

        dealer.playDealerTurn(deck);

        // when
        final int actual = dealer.calculateScore();

        // then
        assertThat(actual)
                .isEqualTo(20);
    }

    @Nested
    @DisplayName("calculateResult() 테스트")
    class CalculateResultMethodTest {

        private Dealer dealer;
        private Player player;

        @BeforeEach
        void init() {
            dealer = Dealer.create("딜러");
            player = Player.create("a", 1000);
        }

        @ParameterizedTest(name = "플레이어의 카드를 건네주면 점수 차이에 의한 게임 결과를 반환한다")
        @MethodSource(value = "domain.helper.ParticipantArguments#makeParticipantsCards")
        void calculateResult_givenPlayer_thenReturnGameResultByScore(final List<Card> dealerCards,
                final List<Card> playerCards, final GameResult expected) {
            // given
            dealerCards.forEach(dealer::addCard);
            playerCards.forEach(player::addCard);

            // when
            GameResult actual = dealer.calculateResult(player.participantCard());

            // then
            assertThat(actual)
                    .isSameAs(expected);
        }

        @ParameterizedTest(name = "플레이어의 카드를 건네주면 플레이어가 블랙잭인 경우의 게임 결과를 반환한다")
        @MethodSource(value = "domain.helper.ParticipantArguments#makePlayerWinByBlackJack")
        void calculateResult_givenPlayer_thenReturnBlackJackWin(final List<Card> dealerCards,
                final List<Card> playerCards, final GameResult expected) {
            // given
            dealerCards.forEach(dealer::addCard);
            playerCards.forEach(player::addCard);

            // when
            GameResult actual = dealer.calculateResult(player.participantCard());

            // then
            assertThat(actual)
                    .isSameAs(expected);
        }

        @ParameterizedTest(name = "플레이어의 카드를 건네주면 플레이어와 딜러가 모두 블랙잭인 경우의 게임 결과를 반환한다")
        @MethodSource(value = "domain.helper.ParticipantArguments#makeDrawByBlackJack")
        void calculateResult_givenPlayer_thenReturnDrawByBlackJack(final List<Card> dealerCards,
                final List<Card> playerCards, final GameResult expected) {
            // given
            dealerCards.forEach(dealer::addCard);
            playerCards.forEach(player::addCard);

            // when
            GameResult actual = dealer.calculateResult(player.participantCard());

            // then
            assertThat(actual)
                    .isSameAs(expected);
        }

        @ParameterizedTest(name = "플레이어의 카드를 건네주면 플레이어와 딜러가 모두 버스트인 경우의 게임 결과를 반환한다")
        @MethodSource(value = "domain.helper.ParticipantArguments#makeLoseByBust")
        void calculateResult_givenPlayer_thenReturnLoseByBust(final List<Card> dealerCards,
                final List<Card> playerCards, final GameResult expected) {
            // given
            dealerCards.forEach(dealer::addCard);
            playerCards.forEach(player::addCard);

            // when
            GameResult actual = dealer.calculateResult(player.participantCard());

            // then
            assertThat(actual)
                    .isSameAs(expected);
        }

        @ParameterizedTest(name = "플레이어의 카드를 건네주면 플레이어가 버스트인 경우의 게임 결과를 반환한다")
        @MethodSource(value = "domain.helper.ParticipantArguments#makeLoseByPlayerBust")
        void calculateResult_givenPlayer_thenReturnLoseByPlayerBust(final List<Card> dealerCards,
                final List<Card> playerCards, final GameResult expected) {
            // given
            dealerCards.forEach(dealer::addCard);
            playerCards.forEach(player::addCard);

            // when
            GameResult actual = dealer.calculateResult(player.participantCard());

            // then
            assertThat(actual)
                    .isSameAs(expected);
        }
    }

    @Test
    @DisplayName("getName()은 호출하면 딜러의 이름을 호출한다")
    void getName_whenCall_thenReturnDealerName() {
        // given
        final Dealer dealer = Dealer.create("딜러");

        // when
        final String actual = dealer.getName();

        // then
        assertThat(actual)
                .isEqualTo("딜러");
    }
}
