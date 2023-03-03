package domain.participant;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardPattern;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ParticipantsTest {

    @MethodSource(value = "validPlayerNames")
    @ParameterizedTest(name = "create()는 유효한 수의 플레이어 이름 컬렉션을 받으면, 예외가 발생하지 않는다")
    void create_givenPlayerNames_thenSuccess(final List<String> playerNames) {
        assertThatCode(() -> Participants.create(playerNames))
                .doesNotThrowAnyException();

        assertThat(Participants.create(playerNames))
                .isInstanceOf(Participants.class);
    }

    @MethodSource(value = "invalidPlayerNames")
    @ParameterizedTest(name = "create()는 7명 초과의 플레이어 이름 컬렉션을 받으면, 예외가 발생한다")
    void create_givenPlayerNames_thenFail(final List<String> playerNames) {
        assertThatThrownBy(() -> Participants.create(playerNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어는 최소 1명, 최대 7명이어야 합니다.");
    }

    @Test
    @DisplayName("create()는 중복된 플레이어 이름을 받으면, 예외가 발생한다")
    void create_givenDuplicateNames_thenFail() {
        List<String> duplicateNames = List.of("a", "b", "c", "d", "e", "f", "a");

        assertThatThrownBy(() -> Participants.create(duplicateNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어의 이름은 중복될 수 없습니다.");
    }

    @ParameterizedTest(name = "addCard()는 플레이어의 순서와 카드를 전달하면, 정상적으로 실행된다.")
    @ValueSource(ints = {0, 1, 2, 3})
    void addCard_givenParticipantOrderAndCard_thenSuccess(final int participantOrder) {
        // given
        List<String> playerNames = List.of("a", "b", "c", "d", "e");
        Participants participants = Participants.create(playerNames);
        Card card = Card.create(CardPattern.CLOVER, CardNumber.QUEEN);

        // when, then
        assertThatCode(() -> participants.addCard(participantOrder, card))
                .doesNotThrowAnyException();
    }

    @MethodSource(value = "domain.helper.ParticipantArguments#makeDealerCards")
    @ParameterizedTest(name = "canDealerGiveCard()는 호출하면 딜러가 카드를 한 장 더 받을지 여부를 반환한다")
    void canDealerGiveCard_whenCall_thenReturnCanGiveCard(final List<Card> cards, final boolean expected) {
        // given
        List<String> playerNames = List.of("a", "b", "c", "d", "e");
        Participants participants = Participants.create(playerNames);
        cards.forEach(card -> participants.addCard(0, card));

        // when
        boolean actual = participants.canDealerGiveCard();

        // then
        assertThat(actual).isSameAs(expected);
    }

    @Test
    @DisplayName("size()는 호출하면 모든 참가자의 수를 반환한다")
    void size_whenCall_thenReturnParticipantSize() {
        // given
        List<String> playerNames = List.of("a", "b", "c", "d", "e");
        Participants participants = Participants.create(playerNames);

        // when
        int actual = participants.size();

        // then
        assertThat(actual).isSameAs(6);
    }

    private static Stream<Arguments> validPlayerNames() {
        return Stream.of(
                Arguments.of(List.of("zeeto")),
                Arguments.of(List.of("zeeto", "journey", "pobi", "neo", "lisa", "wonnie", "cron"))
        );
    }

    private static Stream<Arguments> invalidPlayerNames() {
        return Stream.of(
                Arguments.of(List.of("zeeto", "journey", "pobi", "neo", "lisa", "wonnie", "cron", "juno"))
        );
    }
}
