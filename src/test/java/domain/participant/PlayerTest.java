package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Denomination;
import domain.card.Suit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PlayerTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("provideParameters")
    @DisplayName("턴 강제 종료 여부")
    void player(String comment, Cards cards, boolean expect) {
        // given
        Player player = new Player(new Name("name"), cards);

        // then
        assertThat(player.isFinished()).isEqualTo(expect);
    }

    private static Stream<Arguments> provideParameters() {
        return Stream.of(
                Arguments.arguments("합계 22인 경우 true", getCards(Denomination.TWO, Denomination.QUEEN, Denomination.KING),
                        true),
                Arguments.arguments("합계 20인 경우 false", getCards(Denomination.QUEEN, Denomination.KING), false)
        );
    }

    private static Cards getCards(Denomination... arguments) {
        List<Card> list = new ArrayList<>();
        for (Denomination denomination : arguments) {
            list.add(new Card(denomination, Suit.CLOVER));
        }
        return new Cards(list);
    }

    @Test
    void drawCard() {
        // given
        Player player = new Player(new Name("name"), getCards(Denomination.QUEEN));
        Card newCard = new Card(Denomination.ACE, Suit.CLOVER);

        // when
        player.drawCard(newCard);

        // then
        assertThat(player.getCards().getValue()).contains(newCard);
    }
}
