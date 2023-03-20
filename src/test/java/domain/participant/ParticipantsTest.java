package domain.participant;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import domain.card.Card;
import domain.card.Deck;
import domain.card.Denomination;
import domain.card.RandomUniqueCardSelector;
import domain.card.Shape;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class ParticipantsTest {

    private Participants participants;

    @BeforeEach
    void init() {
        final List<String> playerNames = List.of("a", "b", "c", "d", "e");

        participants = Participants.create(playerNames, ignored -> 1000);
    }

    @Nested
    @DisplayName("create() 테스트")
    class CreateStaticMethodTest {

        @ParameterizedTest(name = "유효한 수의 플레이어 이름 컬렉션을 받으면 참가자 이름을 생성한다")
        @MethodSource(value = "domain.helper.ParticipantArguments#validPlayerNames")
        void create_givenPlayerNames_thenSuccess(final List<String> playerNames) {
            final Participants participants = assertDoesNotThrow(() -> Participants.create(playerNames, ignored -> 1000));

            assertThat(participants)
                    .isInstanceOf(Participants.class);
        }

        @ParameterizedTest(name = "7명 초과의 플레이어 이름 컬렉션을 받으면 예외가 발생한다")
        @MethodSource(value = "domain.helper.ParticipantArguments#invalidPlayerNames")
        void create_givenPlayerNames_thenFail(final List<String> playerNames) {
            assertThatThrownBy(() -> Participants.create(playerNames, ignored -> 1000))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("플레이어는 최소 1명, 최대 7명이어야 합니다.");
        }

        @Test
        @DisplayName("중복된 플레이어 이름을 받으면 예외가 발생한다")
        void create_givenDuplicateNames_thenFail() {
            final List<String> duplicateNames = List.of("a", "b", "c", "d", "e", "a ");

            assertThatThrownBy(() -> Participants.create(duplicateNames, ignored -> 1000))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("플레이어의 이름은 중복될 수 없습니다.");
        }
    }

    private final Card card = Card.of(Shape.CLOVER, Denomination.QUEEN);

    @ParameterizedTest(name = "addCardForPlayer()는 플레이어 이름과 카드를 전달하면 정상적으로 실행된다.")
    @ValueSource(strings = {"a", "b", "c", "d"})
    void addCardForPlayer_givenPlayerNameAndCard_thenSuccess(final String playerName) {
        assertThatCode(() -> participants.addCardForPlayer(playerName, card))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest(name = "canDrawByOrder()는 순서를 건네주면 카드를 더 뽑을 수 있는 상태인지 반환한다")
    @MethodSource("domain.helper.ParticipantArguments#makeDrawCards")
    void canDrawByOrder_givenPlayerOrder_thenReturnIsDraw(final List<Card> playerCards, final boolean expected) {
        // given
        playerCards.forEach(card -> participants.addCardForPlayer("a", card));

        // when
        boolean actual = participants.canDraw("a");

        // then
        assertThat(actual)
                .isSameAs(expected);
    }

    @Test
    @DisplayName("getPlayerCard()는 플레이어 이름을 전달하면 플레이어가 가지고 있는 카드를 반환한다")
    void getPlayerCard_givenOrderAndOffset_thenReturnParticipantCard() {
        // given
        final Card card = Card.of(Shape.SPADE, Denomination.JACK);
        participants.addCardForPlayer("a", card);

        // when
        final List<Card> actual = participants.getPlayerCard("a");

        // then
        assertThat(actual.size())
                .isSameAs(1);
    }

    @Test
    @DisplayName("getDealerCard()는 호출하면 딜러가 가지고 있는 카드를 반환한다")
    void getDealerCard_whenCall_thenReturnDealerCard() {
        // given
        final Card card = Card.of(Shape.SPADE, Denomination.JACK);
        participants.addCardForDealer(card);

        // when
        final List<Card> actual = participants.getDealerCard();

        // then
        assertThat(actual.size())
                .isSameAs(1);
    }

    @Test
    @DisplayName("playDealerTurn()은 호출하면 딜러가 카드를 뽑은 수를 반환한다")
    void playDealerTurn_whenCall_thenReturnDealerDrawCardCount() {
        // given
        final RandomUniqueCardSelector cardSelector = new RandomUniqueCardSelector();
        final Deck deck = Deck.create(cardSelector);

        // when
        final int actual = participants.playDealerTurn(deck);

        // then
        assertThat(actual)
                .isPositive();
    }

    @ParameterizedTest(name = "calculateProfit()는 호출하면 플레이어의 순수익을 반환한다")
    @ValueSource(strings = {"a", "b", "c", "d", "e"})
    void calculateProfit_whenCall_thenReturnPlayerProfit(final String playerName) {
        // when
        final Map<String, BigDecimal> actual = participants.calculateProfit();

        // then
        assertThat(actual.keySet().size())
                .isEqualTo(5);
        assertThat(actual.get(playerName))
                .isEqualTo(BigDecimal.ZERO);
    }
}
