package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.TestUtil.getCards;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Number;
import blackjack.domain.card.Suit;
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
                Arguments.arguments("합계 22인 경우 true", getCards(Number.TWO, Number.QUEEN, Number.KING),
                        true),
                Arguments.arguments("합계 20인 경우 false", getCards(Number.QUEEN, Number.KING), false)
        );
    }

    @Test
    void drawCard() {
        // given
        Player player = new Player(new Name("name"), getCards(Number.QUEEN));
        Card newCard = new Card(Number.ACE, Suit.CLOVER);

        // when
        player.drawCard(newCard);

        // then
        assertThat(player.getCards().getValue()).contains(newCard);
    }
}
