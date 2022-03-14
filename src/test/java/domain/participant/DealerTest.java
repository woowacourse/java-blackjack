package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.TestUtil.getCards;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Number;
import domain.card.Suit;
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
        Dealer dealer = new Dealer(new Name("딜러"), cards);

        // then
        assertThat(dealer.isFinished()).isEqualTo(expect);
    }

    private static Stream<Arguments> provideParameters() {
        return Stream.of(
                Arguments.arguments("합계 22인 경우 true", getCards(Number.TWO, Number.QUEEN, Number.KING),
                        true),
                Arguments.arguments("합계 17인 경우 true", getCards(Number.SEVEN, Number.QUEEN), true),
                Arguments.arguments("합계 15인 경우 false", getCards(Number.QUEEN, Number.FIVE), false)
        );
    }

    @Test
    @DisplayName("딜러 draw 확인")
    void drawCard() {
        // given
        Dealer dealer = new Dealer(new Name("딜러"), getCards(Number.QUEEN));
        Card newCard = new Card(Number.ACE, Suit.CLOVER);

        // when
        dealer.drawCard(newCard);

        // then
        assertThat(dealer.getCards().getValue()).contains(newCard);
    }
}
