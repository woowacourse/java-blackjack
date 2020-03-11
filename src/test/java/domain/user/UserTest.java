package domain.user;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import domain.deck.Card;
import domain.deck.DeckFactory;
import domain.deck.Symbol;
import domain.deck.Type;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new Player("이름");
    }

    @Test
    @DisplayName("빈 이름이 있는 경우 예외 처리")
    void createPlayerWithEmptyName() {
        assertThatThrownBy(() -> new Player(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("빈 이름이 있습니다.");
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
    @DisplayName("카드 point 계산")
    void calculatePoint() {
        user.draw(new Card(Symbol.CLOVER, Type.EIGHT));
        user.draw(new Card(Symbol.DIAMOND, Type.FIVE));

        assertThat(user.calculatePoint()).isEqualTo(13);
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
    @DisplayName("카드 포인트가 21인지 확인")
    void isBlackJack() {
        user.draw(new Card(Symbol.CLOVER, Type.ACE));
        user.draw(new Card(Symbol.DIAMOND, Type.KING));

        assertThat(user.isBlackJack()).isTrue();
    }

    @ParameterizedTest
    @DisplayName("Bust, BlackJack 여부에 따른 드로우")
    @MethodSource("createOption")
    void optionalDraw(Card card, int expected) {
        user.draw(new Card(Symbol.CLOVER, Type.KING));
        user.draw(new Card(Symbol.DIAMOND, Type.EIGHT));
        user.draw(card);
        int initSize = user.cards.size();

        user.optionalDraw(DeckFactory.getDeck());

        assertThat(user.cards.size()).isEqualTo(initSize + expected);
    }

    private static Stream<Arguments> createOption() {
        return Stream.of(
                Arguments.of(new Card(Symbol.DIAMOND, Type.TWO), 1),
                Arguments.of(new Card(Symbol.DIAMOND, Type.THREE), 0),
                Arguments.of(new Card(Symbol.DIAMOND, Type.FIVE), 0)
        );
    }
}