package blackjack.domain.player;

import blackjack.domain.card.Deck;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class PlayersTest {

    @ParameterizedTest
    @MethodSource("participantListBySuccess")
    @DisplayName("참가자는 2~8명 사이이다. (성공)")
    void checkParticipantNumberBySuccess(List<Participant> participants) {
        Deck deck = new Deck();
        Dealer dealer = new Dealer(deck.initDistributeCard());
        assertDoesNotThrow(() -> new Players(participants, dealer));
    }

    private static Stream<List<Player>> participantListBySuccess() {
        Deck deck = new Deck();
        return Stream.of(
                List.of(
                        new Participant(deck.initDistributeCard(), "pobi"),
                        new Participant(deck.initDistributeCard(), "corinne")
                ),
                List.of(
                        new Participant(deck.initDistributeCard(), "1"),
                        new Participant(deck.initDistributeCard(), "2"),
                        new Participant(deck.initDistributeCard(), "3"),
                        new Participant(deck.initDistributeCard(), "4"),
                        new Participant(deck.initDistributeCard(), "5"),
                        new Participant(deck.initDistributeCard(), "6"),
                        new Participant(deck.initDistributeCard(), "7"),
                        new Participant(deck.initDistributeCard(), "8")
                )
        );
    }

    @ParameterizedTest
    @MethodSource("participantListByFail")
    @DisplayName("참가자는 2~8명 사이이다. (실패)")
    void checkParticipantNumber(List<Participant> participants) {
        Deck deck = new Deck();
        Dealer dealer = new Dealer(deck.initDistributeCard());

        Assertions.assertThatThrownBy(() -> new Players(participants, dealer))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 참가자의 수는 2~8명 입니다.");
    }

    private static Stream<List<Player>> participantListByFail() {
        Deck deck = new Deck();
        return Stream.of(
                null,
                List.of(
                        new Participant(deck.initDistributeCard(), "pobi")
                ),
                List.of(
                        new Participant(deck.initDistributeCard(), "1"),
                        new Participant(deck.initDistributeCard(), "2"),
                        new Participant(deck.initDistributeCard(), "3"),
                        new Participant(deck.initDistributeCard(), "4"),
                        new Participant(deck.initDistributeCard(), "5"),
                        new Participant(deck.initDistributeCard(), "6"),
                        new Participant(deck.initDistributeCard(), "7"),
                        new Participant(deck.initDistributeCard(), "8"),
                        new Participant(deck.initDistributeCard(), "9")
                )
        );
    }

    @Test
    @DisplayName("참가자 이름은 중복될 수 없다.")
    void thrownExceptionWhenNamesDuplicated() {
        Deck deck = new Deck();
        Dealer dealer = new Dealer(deck.initDistributeCard());
        Assertions.assertThatThrownBy(() -> new Players(List.of(
                        new Participant(deck.initDistributeCard(), "pobi"),
                        new Participant(deck.initDistributeCard(), "pobi")
                ), dealer))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 참가자 이름은 중복될 수 없습니다.");
    }
}
