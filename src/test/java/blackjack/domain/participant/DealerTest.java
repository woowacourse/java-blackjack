package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.TestUtil.CLOVER_ACE;
import static utils.TestUtil.CLOVER_FIVE;
import static utils.TestUtil.CLOVER_KING;
import static utils.TestUtil.CLOVER_QUEEN;
import static utils.TestUtil.CLOVER_SEVEN;
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

public class DealerTest {
    @ParameterizedTest(name = "{0}")
    @MethodSource("provideParameters")
    @DisplayName("턴 강제 종료 여부")
    void Dealer(String comment, Cards cards, boolean expect) {
        // given
        Dealer dealer = new Dealer(cards);

        // then
        assertThat(dealer.isFinished()).isEqualTo(expect);
    }

    private static Stream<Arguments> provideParameters() {
        return Stream.of(
                Arguments.arguments("합계 22인 경우 true", getCards(CLOVER_TWO, CLOVER_QUEEN, CLOVER_KING),
                        true),
                Arguments.arguments("합계 17인 경우 true", getCards(CLOVER_SEVEN, CLOVER_QUEEN), true),
                Arguments.arguments("합계 15인 경우 false", getCards(CLOVER_QUEEN, CLOVER_FIVE), false)
        );
    }

    @Test
    @DisplayName("딜러 draw 확인")
    void drawCard() {
        // given
        Dealer dealer = new Dealer(getCards(CLOVER_QUEEN));
        Card newCard = CLOVER_ACE;

        // when
        dealer.drawCard(newCard);

        // then
        assertThat(dealer.getCards().getValue()).contains(newCard);
    }
}
