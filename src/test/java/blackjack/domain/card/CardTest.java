package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class CardTest {

    @Test
    @DisplayName("카드 생성 테스트")
    void createCard() {
        Card card = Card.of(Suit.SPADE, Denomination.ACE);

        assertThat(card).isNotNull();
    }

    @ParameterizedTest(name = "card = {0}-{2}, otherCard = {1}-{3}")
    @MethodSource("suitDenominationDummy")
    @DisplayName("카드는 Suit와 Denomination이 같은면 같은 객체다")
    void sameAs(Suit suit, Suit otherSuit, Denomination denomination, Denomination otherDenomination) {
        Card card = Card.of(suit, denomination);
        Card otherCard = Card.of(otherSuit, otherDenomination);

        assertThat(card).isSameAs(otherCard);
    }

    static Stream<Arguments> suitDenominationDummy() {
        return Stream.of(
                Arguments.of(Suit.HEART, Suit.HEART, Denomination.ACE, Denomination.ACE),
                Arguments.of(Suit.DIAMOND, Suit.DIAMOND, Denomination.TEN, Denomination.TEN),
                Arguments.of(Suit.CLOVER, Suit.CLOVER, Denomination.TWO, Denomination.TWO),
                Arguments.of(Suit.SPADE, Suit.SPADE, Denomination.KING, Denomination.KING)
        );
    }

    @Test
    @DisplayName("모든 카드를 반환한다.")
    void test() {
        List<Card> allCards = Card.getAllCards();
        int count = allCards.size();
        int countDistinct = (int) allCards.stream()
                .distinct()
                .count();

        assertThat(count).isEqualTo(52);
        assertThat(countDistinct).isEqualTo(52);
    }

}
