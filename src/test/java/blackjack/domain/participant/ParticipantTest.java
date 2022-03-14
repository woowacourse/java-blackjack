package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.TestUtil.getCards;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Number;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ParticipantTest {

    @ParameterizedTest
    @MethodSource("provideParameters")
    @DisplayName("클래스 동일 여부 확인")
    <T> void isSameClass(Participant participant, Class<T> className, boolean expect) {
        // when
        boolean result = participant.isSameClass(className);

        // then
        assertThat(result).isEqualTo(expect);
    }

    private static Stream<Arguments> provideParameters() {
        Cards cards = getCards(Number.TWO, Number.QUEEN, Number.KING);
        return Stream.of(
                Arguments.arguments(new Player(new Name("yeonLog"), cards), Participant.class, false),
                Arguments.arguments(new Player(new Name("yeonLog"), cards), Player.class, true),
                Arguments.arguments(new Player(new Name("yeonLog"), cards), Dealer.class, false),
                Arguments.arguments(new Dealer(new Name("yeonLog"), cards), Participant.class, false),
                Arguments.arguments(new Dealer(new Name("yeonLog"), cards), Player.class, false),
                Arguments.arguments(new Dealer(new Name("yeonLog"), cards), Dealer.class, true)
        );
    }
}