package domain.game;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.Pattern;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class PlayerTest {

    private static Stream<Arguments> provideCard() {
        return Stream.of(
                Arguments.of(List.of(new Card(Pattern.SPADE, CardNumber.TEN),
                        new Card(Pattern.SPADE, CardNumber.TEN),
                        new Card(Pattern.SPADE, CardNumber.TWO)), false),
                Arguments.of(List.of(new Card(Pattern.SPADE, CardNumber.TEN),
                        new Card(Pattern.SPADE, CardNumber.ACE)), true));
    }

    @ParameterizedTest
    @MethodSource("provideCard")
    void 플레이어의_카드_합계가_21_이하인지_판정한다(List<Card> cards, boolean expected) {
        //given
        Player player = new Player("pobi", 1000);
        List<Card> playerCards = player.getCards();
        playerCards.addAll(cards);

        //when
        boolean actual = player.isUnderBurstBound();

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 플레이어의_이름과_배팅_금액으로_플레이어를_생성한다() {
        //given
        String name = "pobi";
        int bettingAmount = 1000;

        //when
        Player player = new Player(name, bettingAmount);

        //then
        assertAll(
                () -> assertThat(player.getName()).isEqualTo(name),
                () -> assertThat(player.getBettingAmount()).isEqualTo(bettingAmount)
        );
    }
}
