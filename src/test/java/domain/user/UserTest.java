package domain.user;

import domain.deck.Card;
import domain.deck.Symbol;
import domain.deck.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new Player("이름");
    }

    @Test
    void getName() {
        assertThat(user.getName()).isEqualTo("이름");
    }

    @Test
    @DisplayName("카드 한장 분배")
    void draw() {
        int initSize = user.cards.size();

        user.draw(new Card(Symbol.CLOVER, Type.ACE));

        assertThat(user.cards.size()).isEqualTo(initSize + 1);
    }

    @Test
    @DisplayName("첫 카드 분배 결과")
    void getDrawResult() {
        user.draw(new Card(Symbol.CLOVER, Type.EIGHT));
        user.draw(new Card(Symbol.DIAMOND, Type.ACE));

        assertThat(user.getDrawResult()).isEqualTo("이름카드: 8클로버, A다이아몬드");
    }

    @ParameterizedTest
    @DisplayName("Ace의 보유 여부를 고려한 카드 point 계산")
    @MethodSource("createCardAndPoint")
    void calculatePointAccordingToHasAce(Card card, int expected) {
        user.draw(new Card(Symbol.CLOVER, Type.FIVE));
        user.draw(new Card(Symbol.DIAMOND, Type.ACE));
        user.draw(card);

        assertThat(user.calculatePointAccordingToHasAce()).isEqualTo(expected);
    }

    private static Stream<Arguments> createCardAndPoint() {
        return Stream.of(
                Arguments.of(new Card(Symbol.DIAMOND, Type.FOUR), 20),
                Arguments.of(new Card(Symbol.DIAMOND, Type.FIVE), 21),
                Arguments.of(new Card(Symbol.DIAMOND, Type.SIX), 12)
        );
    }

    @Test
    @DisplayName("카드 포인트가 21을 넘는지 확인")
    void isBust() {
        user.draw(new Card(Symbol.CLOVER, Type.EIGHT));
        user.draw(new Card(Symbol.DIAMOND, Type.EIGHT));
        user.draw(new Card(Symbol.HEART, Type.EIGHT));

        assertThat(user.isBust()).isTrue();
    }

    @Test
    @DisplayName("카드가 2장일 때 포인트가 21인지 확인")
    void isBlackJack() {
        user.draw(new Card(Symbol.CLOVER, Type.ACE));
        user.draw(new Card(Symbol.DIAMOND, Type.KING));

        assertThat(user.isBlackJack()).isTrue();
    }

    @Test
    @DisplayName("카드 포인트가 21인지 확인")
    void isBlackJackPoint() {
        user.draw(new Card(Symbol.DIAMOND, Type.KING));
        user.draw(new Card(Symbol.DIAMOND, Type.FIVE));
        user.draw(new Card(Symbol.DIAMOND, Type.SIX));

        assertThat(user.isBlackJackPoint()).isTrue();
    }

    @ParameterizedTest
    @DisplayName("ACE를 가지고 있는지 확인")
    @MethodSource("createCard")
    void hasAce(Card card, boolean expected) {
        user.draw(card);

        assertThat(user.hasAce()).isEqualTo(expected);
    }

    private static Stream<Arguments> createCard() {
        return Stream.of(
                Arguments.of(new Card(Symbol.DIAMOND, Type.ACE), true),
                Arguments.of(new Card(Symbol.DIAMOND, Type.FIVE), false)
        );
    }

    @Test
    @DisplayName("최종 보유 카드 및 포인트 계산 결과")
    void getTotalDrawResult() {
        user.draw(new Card(Symbol.DIAMOND, Type.KING));
        user.draw(new Card(Symbol.DIAMOND, Type.SIX));

        assertThat(user.getTotalDrawResult()).isEqualTo("이름카드: K다이아몬드, 6다이아몬드 - 결과: 16");
    }
}