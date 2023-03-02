package blackjack.domain;

import static blackjack.domain.Rank.ACE;
import static blackjack.domain.Rank.JACK;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.util.FixedDeck;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class GamblerTest {

    @Test
    void 겜블러가_정상_생성된다() {
        final Gambler gambler = new Gambler("허브");

        assertThat(gambler.getName()).isEqualTo("허브");
    }

    @ParameterizedTest(name = "카드를 더 뽑을 수 있는지 확인한다 입력: {0}, 결과: {1}")
    @MethodSource("isPlayableSource")
    void 카드를_더_뽑을_수_있는지_확인한다(final List<Rank> ranks, final boolean result) {
        final Gambler gambler = new Gambler("허브");
        final Deck deck = new FixedDeck(ranks);
        
        gambler.initialDraw(deck);

        assertThat(gambler.canDraw()).isEqualTo(result);
    }

    static Stream<Arguments> isPlayableSource() {
        return Stream.of(
                Arguments.of(List.of(ACE, JACK), false),
                Arguments.of(List.of(JACK, JACK), true)
        );
    }
}
