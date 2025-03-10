package domain.game;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.Pattern;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

    private static Stream<Arguments> provideCard() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(Pattern.SPADE, CardNumber.TEN),
                                new Card(Pattern.SPADE, CardNumber.TEN),
                                new Card(Pattern.SPADE, CardNumber.TWO)
                        ),
                        true
                ),
                Arguments.of(
                        List.of(
                                new Card(Pattern.SPADE, CardNumber.TEN),
                                new Card(Pattern.SPADE, CardNumber.ACE)
                        ),
                        false
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideCard")
    void 플레이어의_카드_합계가_21_이하인지_판정한다(List<Card> cards, boolean expected) {
        //given
        Player player = new Player("pobi");
        List<Card> playerCards = player.getCards();
        playerCards.addAll(cards);

        //when
        boolean actual = player.isOverBurstBound();

        //then
        assertThat(actual).isEqualTo(expected);
    }
}
