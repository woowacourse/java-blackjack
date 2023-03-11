package domain.participant;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Shape;
import domain.game.GameResult;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ParticipantsTest {

    private Participants participants;

    @BeforeEach
    void init() {
        final List<String> playerNames = List.of("a", "b", "c", "d", "e");

        participants = Participants.create(playerNames);
    }

    @Nested
    @DisplayName("create() 테스트")
    class CreateStaticMethodTest {

        @ParameterizedTest(name = "유효한 수의 플레이어 이름 컬렉션을 받으면 참가자 이름을 생성한다")
        @MethodSource(value = "domain.helper.ParticipantArguments#validPlayerNames")
        void create_givenPlayerNames_thenSuccess(final List<String> playerNames) {
            final Participants participants = assertDoesNotThrow(() -> Participants.create(playerNames));

            assertThat(participants)
                    .isInstanceOf(Participants.class);
        }

        @ParameterizedTest(name = "7명 초과의 플레이어 이름 컬렉션을 받으면 예외가 발생한다")
        @MethodSource(value = "domain.helper.ParticipantArguments#invalidPlayerNames")
        void create_givenPlayerNames_thenFail(final List<String> playerNames) {
            assertThatThrownBy(() -> Participants.create(playerNames))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("플레이어는 최소 1명, 최대 7명이어야 합니다.");
        }

        @Test
        @DisplayName("중복된 플레이어 이름을 받으면 예외가 발생한다")
        void create_givenDuplicateNames_thenFail() {
            final List<String> duplicateNames = List.of("a", "b", "c", "d", "e", "a ");

            assertThatThrownBy(() -> Participants.create(duplicateNames))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("플레이어의 이름은 중복될 수 없습니다.");
        }

    }

    @Nested
    @DisplayName("addCard() 테스트")
    class AddCardMethodTest {

        private final Card card = Card.of(Shape.CLOVER, Denomination.QUEEN);

        @ParameterizedTest(name = "addCard()는 플레이어의 순서와 카드를 전달하면 정상적으로 실행된다.")
        @ValueSource(ints = {0, 1, 2, 3})
        void addCard_givenParticipantOrderAndCard_thenSuccess(final int participantOrder) {
            assertThatCode(() -> participants.addCard(participantOrder, card))
                    .doesNotThrowAnyException();
        }

        @ParameterizedTest(name = "addCard()는 참가자의 순서, 카드, 참자가 타입을 전달하면 정상적으로 실행된다.")
        @ValueSource(ints = {0, 1, 2, 3})
        void addCard_givenParticipantOrderAndCardAndParticipantType_thenSuccess(final int participantOrder) {
            assertThatCode(() -> participants.addCard(participantOrder, card, ParticipantOffset.PLAYER))
                    .doesNotThrowAnyException();
        }
    }

    @ParameterizedTest(name = "canDrawByOrder()는 순서를 건네주면 카드를 더 뽑을 수 있는 상태인지 반환한다")
    @MethodSource("domain.helper.ParticipantArguments#makeDrawCards")
    void canDrawByOrder_givenPlayerOrder_thenReturnIsDraw(final List<Card> playerCards,
        final ParticipantOffset offset, final boolean expected) {
        // given
        playerCards.forEach(card -> participants.addCard(1, card));

        // when
        boolean actual = participants.canDrawByOrder(0, offset);

        // then
        assertThat(actual)
                .isSameAs(expected);
    }

    @ParameterizedTest(name = "findPlayerNameByPlayerOrder()는 플레이어 순서를 전달하면 이름을 반환한다")
    @CsvSource(value = {"0:a", "1:b", "2:c", "3:d", "4:e"}, delimiter = ':')
    void findPlayerNameByPlayerOrder_givenOrder_thenReturnName(final int playerOrder, final String expected) {
        // when
        final String actual = participants.findParticipantNameByOrder(playerOrder, ParticipantOffset.PLAYER);

        // then
        assertThat(actual)
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("findPlayerCardsByOrder()은 플레이어 순서와 참가자 타입을 전달하면 가지고 있는 카드를 반환한다")
    void findPlayerCardsByOrder_givenOrderAndOffset_thenReturnParticipantCard() {
        // given
        final Card card = Card.of(Shape.SPADE, Denomination.JACK);
        participants.addCard(0, card, ParticipantOffset.PLAYER);

        // when
        final List<Card> actual = participants.findPlayerCardsByOrder(0, ParticipantOffset.PLAYER);

        // then
        assertThat(actual.size())
                .isSameAs(1);
    }

    @Test
    @DisplayName("size()는 호출하면 모든 참가자의 수를 반환한다")
    void size_whenCall_thenReturnParticipantSize() {
        // when
        final int actual = participants.size();

        // then
        assertThat(actual)
                .isSameAs(6);
    }

    @Test
    @DisplayName("playerSize()는 호출하면 모든 플레이어의 수를 반환한다")
    void playerSize_whenCall_thenReturnPlayerSize() {
        // when
        final int actual = participants.playerSize();

        // then
        assertThat(actual)
                .isSameAs(5);
    }

    @Test
    @DisplayName("calculatePlayerGameResult()는 호출하면 각 플레이어의 게임 결과를 계산해 반환한다")
    void calculatePlayerGameResult_whenCall_thenReturnPlayerGameResult() {
        // when
        final Map<Participant, GameResult> actual = participants.calculatePlayerGameResult();

        // then
        assertThat(actual.keySet().size())
                .isSameAs(5);

        actual.keySet()
                .forEach(player -> assertThat(actual.get(player))
                        .isSameAs(GameResult.DRAW));
    }

    @ParameterizedTest(name = "getParticipantNames()는 호출하면 모든 참가자들의 이름을 반환한다")
    @CsvSource(value = {"0:딜러", "1:a", "2:b", "3:c", "4:d", "5:e"}, delimiter = ':')
    void getParticipantNames_whenCall_thenReturnParticipantNames(final int participantIndex, final String expected) {
        // given
        final List<String> participantNames = participants.getParticipantNames();

        // when
        final String actual = participantNames.get(participantIndex);

        // then
        assertThat(actual)
                .isEqualTo(expected);
    }

    @Nested
    @DisplayName("bet() 테스트")
    class BetMethodTest {

        @Test
        @DisplayName("유효한 배팅 금액을 전달하면 플레이어가 배팅금액을 배팅한다")
        void create_givenValidMoney_thenInitPlayerBet() {
            assertThatCode(() -> participants.bet(0, 1000, ParticipantOffset.PLAYER))
                    .doesNotThrowAnyException();
        }

        @Test
        @DisplayName("최소 금액을 배팅하지 않으면 예외가 발생한다")
        void create_givenLessThanMinimumMoney_thenFail() {
            assertThatThrownBy(() -> participants.bet(0, 0, ParticipantOffset.PLAYER))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("최소 천 원 이상 배팅해주세요.");
        }

        @Test
        @DisplayName("정해진 금액 단위로 배팅하지 않으면 예외가 발생한다")
        void create_givenInvalidAmountUnit_thenFail() {
            assertThatThrownBy(() -> participants.bet(0, 1100, ParticipantOffset.PLAYER))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("천 원 단위로 배팅해주세요.");
        }
    }
}
