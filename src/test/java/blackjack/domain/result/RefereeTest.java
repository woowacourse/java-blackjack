package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Deck;
import blackjack.domain.card.HandGenerator;
import blackjack.domain.card.Number;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Participants;
import blackjack.testutil.CustomDeck;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RefereeTest {
    private static Stream<Arguments> provideParticipantsWithHandResult() {
        return Stream.of(
                Arguments.of(List.of(Number.ACE, Number.SIX, Number.ACE, Number.EIGHT), HandResult.LOSE),
                Arguments.of(List.of(Number.ACE, Number.EIGHT, Number.ACE, Number.SIX), HandResult.WIN),
                Arguments.of(List.of(Number.SEVEN, Number.JACK, Number.QUEEN, Number.SEVEN), HandResult.DRAW)
        );
    }

    @DisplayName("게임의 최종 결과를 생성한다.")
    @ParameterizedTest
    @MethodSource("provideParticipantsWithHandResult")
    void generateResultTest(List<Number> numbers, HandResult playerHandResult) {
        Deck deck = new CustomDeck(numbers);
        HandGenerator handGenerator = new HandGenerator(deck);
        List<Name> playerName = List.of(new Name("gamza"));
        Participants participants = new Participants(playerName, handGenerator);
        Referee referee = new Referee(participants);
        BlackjackResult blackjackResult = referee.generateResult();

        assertThat(blackjackResult.getPlayersResult().values()).containsExactly(playerHandResult);
    }
}
