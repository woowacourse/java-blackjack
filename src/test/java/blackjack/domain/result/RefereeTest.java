package blackjack.domain.result;

import blackjack.domain.card.Deck;
import blackjack.domain.card.HandGenerator;
import blackjack.domain.card.Number;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;
import blackjack.testutil.CustomDeck;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

class RefereeTest {
    @DisplayName("플레이어의 승무패를 올바르게 판단한다.")
    @ParameterizedTest
    @MethodSource("provideParticipantsWithHandResult")
    void generateResultTest(List<Number> numbers, HandResult expectedResult) {
        HandGenerator handGenerator = createHandGenerator(numbers);
        Player player = new Player(new Name("감자"), handGenerator);
        Dealer dealer = new Dealer(handGenerator);
        Referee referee = Referee.getInstance();
        HandResult playerResult = referee.getPlayerResult(player, dealer);

        assertThat(playerResult).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> provideParticipantsWithHandResult() {
        return Stream.of(
                Arguments.of(List.of(Number.ACE, Number.SIX, Number.ACE, Number.EIGHT), HandResult.LOSE),
                Arguments.of(List.of(Number.ACE, Number.EIGHT, Number.ACE, Number.SIX), HandResult.WIN),
                Arguments.of(List.of(Number.SEVEN, Number.JACK, Number.QUEEN, Number.SEVEN), HandResult.DRAW)
        );
    }

    private static HandGenerator createHandGenerator(List<Number> numbers) {
        Deck deck = new CustomDeck(numbers);
        return new HandGenerator(deck);
    }
}
