package blackjack.domain.player;

import blackjack.domain.card.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class PlayersTest {

    public Deck deck;

    @BeforeEach
    void setup() {
        deck = new Deck(new DeckCardGenerator());
    }

    @ParameterizedTest
    @MethodSource("participantListBySuccess")
    @DisplayName("참가자는 2~8명 사이이다. (성공)")
    void checkParticipantNumberBySuccess(List<Player> participants) {
        Dealer dealer = new Dealer(deck.makeDistributeCard());
        assertDoesNotThrow(() -> new Players(participants, dealer));
    }

    private static Stream<List<Player>> participantListBySuccess() {
        Deck deck = new Deck(new DeckCardGenerator());
        return Stream.of(
                List.of(
                        new Participant(deck.makeDistributeCard(), "pobi"),
                        new Participant(deck.makeDistributeCard(), "corinne")
                ),
                List.of(
                        new Participant(deck.makeDistributeCard(), "1"),
                        new Participant(deck.makeDistributeCard(), "2"),
                        new Participant(deck.makeDistributeCard(), "3"),
                        new Participant(deck.makeDistributeCard(), "4"),
                        new Participant(deck.makeDistributeCard(), "5"),
                        new Participant(deck.makeDistributeCard(), "6"),
                        new Participant(deck.makeDistributeCard(), "7"),
                        new Participant(deck.makeDistributeCard(), "8")
                )
        );
    }

    @ParameterizedTest
    @MethodSource("participantListByFail")
    @DisplayName("참가자는 2~8명 사이이다. (실패)")
    void checkParticipantNumber(List<Player> participants) {
        Dealer dealer = new Dealer(deck.makeDistributeCard());

        Assertions.assertThatThrownBy(() -> new Players(participants, dealer))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 참가자 정보가 잘못 입력되었습니다.");
    }

    private static Stream<List<Player>> participantListByFail() {
        Deck deck = new Deck(new DeckCardGenerator());
        return Stream.of(
                null,
                List.of(
                        new Participant(deck.makeDistributeCard(), "pobi")
                ),
                List.of(
                        new Participant(deck.makeDistributeCard(), "1"),
                        new Participant(deck.makeDistributeCard(), "2"),
                        new Participant(deck.makeDistributeCard(), "3"),
                        new Participant(deck.makeDistributeCard(), "4"),
                        new Participant(deck.makeDistributeCard(), "5"),
                        new Participant(deck.makeDistributeCard(), "6"),
                        new Participant(deck.makeDistributeCard(), "7"),
                        new Participant(deck.makeDistributeCard(), "8"),
                        new Participant(deck.makeDistributeCard(), "9")
                )
        );
    }

    @Test
    @DisplayName("참가자 이름은 중복될 수 없다.")
    void thrownExceptionWhenNamesDuplicated() {
        Player dealer = new Dealer(deck.makeDistributeCard());
        Assertions.assertThatThrownBy(() -> new Players(List.of(
                        new Participant(deck.makeDistributeCard(), "pobi"),
                        new Participant(deck.makeDistributeCard(), "pobi")
                ), dealer))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 참가자 이름은 중복될 수 없습니다.");
    }

}
