package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.TestUtil.BETTING_1000;
import static utils.TestUtil.CLOVER_ACE;
import static utils.TestUtil.CLOVER_KING;
import static utils.TestUtil.CLOVER_QUEEN;
import static utils.TestUtil.CLOVER_TWO;
import static utils.TestUtil.getCards;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
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
        Player player = new Player(new Name("name"), cards, BETTING_1000);

        // then
        assertThat(player.isFinished()).isEqualTo(expect);
    }

    private static Stream<Arguments> provideParameters() {
        return Stream.of(
                Arguments.arguments("합계 22인 경우 true", getCards(CLOVER_TWO, CLOVER_QUEEN, CLOVER_KING),
                        true),
                Arguments.arguments("합계 20인 경우 false", getCards(CLOVER_QUEEN, CLOVER_KING), false)
        );
    }

    @Test
    @DisplayName("드로우 카드")
    void drawCard() {
        // given
        Player player = new Player(new Name("name"), getCards(CLOVER_QUEEN), BETTING_1000);
        Card newCard = CLOVER_ACE;

        // when
        player.drawCard(newCard);

        // then
        assertThat(player.getCards().getValue()).contains(newCard);
    }
}
