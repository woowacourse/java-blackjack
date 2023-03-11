package domain.participant;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Shape;
import domain.game.GameResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class DealerTest {

    private final Dealer dealer = Dealer.create();

    @Test
    @DisplayName("create()는 호출하면 딜러를 생성한다")
    void create_whenCall_thenSuccess() {
        final Dealer dealer = assertDoesNotThrow(Dealer::create);

        assertThat(dealer)
                .isExactlyInstanceOf(Dealer.class);
    }

    @Test
    @DisplayName("addCard()는 카드를 건네주면 참가자의 카드에 추가한다")
    void addCard_givenCard_thenSuccess() {
        // when
        final Card card = Card.of(Shape.HEART, Denomination.ACE);
        dealer.addCard(card);
        final ParticipantCard participantCard = dealer.participantCard;
        final List<Card> cards = participantCard.getCards();

        // then
        assertThat(cards)
                .hasSize(1);
    }

    @Test
    @DisplayName("getStartCard()는 호출하면 딜러의 첫 번째 카드를 조회한다")
    void getStartCard_whenCall_thenReturnFirstCard() {
        // given
        final Card card = Card.of(Shape.HEART, Denomination.ACE);
        dealer.addCard(card);

        // when
        final List<Card> actual = dealer.getStartCard();

        // then
        assertThat(actual.size())
                .isSameAs(1);

        assertThat(actual.get(0))
                .isEqualTo(card);
    }

    @ParameterizedTest(name = "calculateScore()는 호출하면 점수를 계산한다")
    @MethodSource(value = "domain.helper.ParticipantArguments#makeCards")
    void calculateScore_whenCall_thenReturnScore(final List<Card> cards, final int expected) {
        // given
        cards.forEach(dealer::addCard);

        // when
        final int actual = dealer.calculateScore();

        // then
        assertThat(actual)
                .isEqualTo(expected);
    }

    @ParameterizedTest(name = "canDraw()는 호출하면 딜러가 카드를 한 장 더 받을지 여부를 반환한다")
    @MethodSource(value = "domain.helper.ParticipantArguments#makeDealerCards")
    void canGiveCard_whenCall_thenReturnCanGiveCard(final List<Card> cards, final boolean expected) {
        // given
        cards.forEach(dealer::addCard);

        // when, then
        assertThat(dealer.canDraw())
                .isSameAs(expected);
    }

    @Nested
    @DisplayName("calculateResult() 테스트")
    class CalculateResultMethodTest {

        private Dealer dealer;
        private Player player;

        @BeforeEach
        void init() {
            dealer = Dealer.create();
            player = Player.create("a");
        }

        @ParameterizedTest(name = "플레이어의 카드를 건네주면 점수 차이에 의한 게임 결과를 반환한다")
        @MethodSource(value = "domain.helper.ParticipantArguments#makeParticipantsCards")
        void calculateResult_givenPlayer_thenReturnGameResultByScore(final List<Card> dealerCards,
                final List<Card> playerCards, final GameResult expected) {
            // given
            dealerCards.forEach(dealer::addCard);
            playerCards.forEach(player::addCard);

            // when
            GameResult actual = dealer.calculateResult(player);

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
            GameResult actual = dealer.calculateResult(player);

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
            GameResult actual = dealer.calculateResult(player);

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
            GameResult actual = dealer.calculateResult(player);

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
            GameResult actual = dealer.calculateResult(player);

            // then
            assertThat(actual)
                    .isSameAs(expected);
        }
    }

    @Test
    @DisplayName("getName()은 호출하면 딜러의 이름을 호출한다")
    void getName_whenCall_thenReturnDealerName() {
        // given
        final Dealer dealer = Dealer.create();

        // when
        final String actual = dealer.getName();

        // then
        assertThat(actual)
                .isEqualTo("딜러");
    }
}
