package domain.participant;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Denomination;
import domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {
    @ParameterizedTest(name = "{0}")
    @MethodSource("provideParameters")
    @DisplayName("턴 강제 종료 여부")
    void Dealer(String comment, Cards cards, boolean expect) {
        Dealer dealer = new Dealer(new Name("딜러"), cards);
        assertThat(dealer.isFinished()).isEqualTo(expect);
    }

    private static Stream<Arguments> provideParameters() {
        return Stream.of(
                Arguments.arguments("합계 22인 경우 true", new Cards(getCardList(Denomination.TWO, Denomination.QUEEN, Denomination.KING)), true),
                Arguments.arguments("합계 17인 경우 true", new Cards(getCardList(Denomination.SEVEN, Denomination.QUEEN)), true),
                Arguments.arguments("합계 15인 경우 false", new Cards(getCardList(Denomination.QUEEN, Denomination.FIVE)), false)
        );
    }

    private static List<Card> getCardList(Denomination... arguments) {
        List<Card> list = new ArrayList<>();
        for (Denomination denomination : arguments) {
            list.add(Card.valueOf(denomination, Suit.CLOVER));
        }
        return list;
    }

    @Test
    void drawCard() {
        Dealer dealer = new Dealer(new Name("딜러"), new Cards(getCardList(Denomination.QUEEN)));
        dealer.drawCard(Card.valueOf(Denomination.ACE, Suit.CLOVER));
        assertThat(dealer.getCards().getValue().size()).isEqualTo(2);
    }
}
