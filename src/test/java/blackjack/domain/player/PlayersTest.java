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

    private Deck deck;
    private List<Card> initCards;

    @BeforeEach
    void setup() {
        deck = new Deck(new RandomCardGenerator());
        initCards = List.of(deck.draw(), deck.draw());
    }

    @ParameterizedTest
    @MethodSource("participantListBySuccess")
    @DisplayName("참가자는 2~8명 사이이다. (성공)")
    void checkParticipantNumberBySuccess(List<Player> participants) {
        Dealer dealer = new Dealer(initCards);
        assertDoesNotThrow(() -> new Players(participants, dealer));
    }

    private static Stream<List<Player>> participantListBySuccess() {
        Deck deck = new Deck(new RandomCardGenerator());
        return Stream.of(
                List.of(
                        new Participant(List.of(deck.draw(), deck.draw()), "pobi"),
                        new Participant(List.of(deck.draw(), deck.draw()), "corinne")
                ),
                List.of(
                        new Participant(List.of(deck.draw(), deck.draw()), "1"),
                        new Participant(List.of(deck.draw(), deck.draw()), "2"),
                        new Participant(List.of(deck.draw(), deck.draw()), "3"),
                        new Participant(List.of(deck.draw(), deck.draw()), "4"),
                        new Participant(List.of(deck.draw(), deck.draw()), "5"),
                        new Participant(List.of(deck.draw(), deck.draw()), "6"),
                        new Participant(List.of(deck.draw(), deck.draw()), "7"),
                        new Participant(List.of(deck.draw(), deck.draw()), "8")
                )
        );
    }

    @ParameterizedTest
    @MethodSource("participantListByFail")
    @DisplayName("참가자는 2~8명 사이이다. (실패)")
    void checkParticipantNumber(List<Player> participants) {
        Dealer dealer = new Dealer(initCards);

        Assertions.assertThatThrownBy(() -> new Players(participants, dealer))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 참가자 정보가 잘못 입력되었습니다.");
    }

    private static Stream<List<Player>> participantListByFail() {
        Deck deck = new Deck(new RandomCardGenerator());
        return Stream.of(
                null,
                List.of(
                        new Participant(List.of(deck.draw(), deck.draw()), "pobi")
                ),
                List.of(
                        new Participant(List.of(deck.draw(), deck.draw()), "1"),
                        new Participant(List.of(deck.draw(), deck.draw()), "2"),
                        new Participant(List.of(deck.draw(), deck.draw()), "3"),
                        new Participant(List.of(deck.draw(), deck.draw()), "4"),
                        new Participant(List.of(deck.draw(), deck.draw()), "5"),
                        new Participant(List.of(deck.draw(), deck.draw()), "6"),
                        new Participant(List.of(deck.draw(), deck.draw()), "7"),
                        new Participant(List.of(deck.draw(), deck.draw()), "8"),
                        new Participant(List.of(deck.draw(), deck.draw()), "9")
                )
        );
    }

    @Test
    @DisplayName("참가자 이름은 중복될 수 없다.")
    void thrownExceptionWhenNamesDuplicated() {
        Player dealer = new Dealer(initCards);
        Assertions.assertThatThrownBy(() -> new Players(List.of(
                        new Participant(List.of(deck.draw(), deck.draw()), "pobi"),
                        new Participant(List.of(deck.draw(), deck.draw()), "pobi")
                ), dealer))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 참가자 이름은 중복될 수 없습니다.");
    }


}
