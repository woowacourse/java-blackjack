package blackjack;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@SuppressWarnings("NonAsciiCharacters")
public class CardsTest {

    @Test
    void 카드_합_계산() {
        Cards cards = new Cards(new Card("3다이아몬드"), new Card("2다이아몬드"));
        assertThat(cards.score()).isEqualTo(new Score(5));
    }

    @Test
    void J_Q_K_카드_점수_계산() {
        Cards cards = new Cards(new Card("Q클로버"), new Card("J하트"), new Card("K다이아몬드"));
        assertThat(cards.score()).isEqualTo(new Score(30));
    }

    @ParameterizedTest
    @MethodSource("provideAceData")
    void ACE_카드_점수_계산(Cards cards) {
        assertThat(cards.score()).isEqualTo(new Score(21));
    }

    protected static Stream<Arguments> provideAceData() {
        return Stream.of(
                Arguments.of(new Cards(new Card("A스페이스"), new Card("J하트"))),
                Arguments.of(new Cards(new Card("A다이아몬드"), new Card("J다이아몬드"), new Card("K클로버"))),
                Arguments.of(new Cards(new Card("A다이아몬드"), new Card("A스페이드"), new Card("9클로버")))
        );
    }
}
