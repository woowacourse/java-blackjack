package domain.card;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CardsTest {

    private Cards cards;

    @BeforeEach
    void setUp() {
        cards = new Cards();
    }

    @Test
    @DisplayName("카드 한장 추가")
    void add() {
        int initSize = cards.getCards().size();

        cards.add(new Card(Symbol.CLOVER, Type.ACE));

        assertThat(cards.getCards().size()).isEqualTo(initSize + 1);
    }

    @Test
    @DisplayName("카드 포인트가 21을 넘는지 확인")
    void isBust() {
        cards.add(new Card(Symbol.CLOVER, Type.EIGHT));
        cards.add(new Card(Symbol.DIAMOND, Type.EIGHT));
        cards.add(new Card(Symbol.HEART, Type.EIGHT));

        assertThat(cards.areBust()).isTrue();
    }

    @Test
    @DisplayName("카드가 2장일 때 포인트가 21인지 확인")
    void isBlackJack() {
        cards.add(new Card(Symbol.CLOVER, Type.ACE));
        cards.add(new Card(Symbol.DIAMOND, Type.KING));

        assertThat(cards.areBlackJack()).isTrue();
    }

    @Test
    @DisplayName("카드 포인트가 21인지 확인")
    void isBlackJackPoint() {
        cards.add(new Card(Symbol.DIAMOND, Type.KING));
        cards.add(new Card(Symbol.DIAMOND, Type.FIVE));
        cards.add(new Card(Symbol.DIAMOND, Type.SIX));

        assertThat(cards.areBlackJackPoint()).isTrue();
    }

    @ParameterizedTest
    @DisplayName("Ace의 보유 여부를 고려한 카드 point 계산")
    @MethodSource("createCardAndPoint")
    void calculatePointAccordingToHasAce(Card card, int expected) {
        cards.add(new Card(Symbol.CLOVER, Type.FIVE));
        cards.add(new Card(Symbol.DIAMOND, Type.ACE));
        cards.add(card);

        assertThat(cards.calculatePointAccordingToHasAce()).isEqualTo(expected);
    }

    private static Stream<Arguments> createCardAndPoint() {
        return Stream.of(
                Arguments.of(new Card(Symbol.DIAMOND, Type.FOUR), 20),
                Arguments.of(new Card(Symbol.DIAMOND, Type.FIVE), 21),
                Arguments.of(new Card(Symbol.DIAMOND, Type.SIX), 12)
        );
    }
}