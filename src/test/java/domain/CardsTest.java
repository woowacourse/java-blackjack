package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {
    private Cards cards;

    @BeforeEach
    void init() {
        cards = new Cards();
        cards.add(new Card(Type.DIAMOND, Symbol.TEN));
        cards.add(new Card(Type.SPADE, Symbol.TEN));
    }

    @Test
    @DisplayName("같은 카드 추가 테스트")
    void add() {
        Assertions.assertThatThrownBy(() -> cards.add(new Card(Type.DIAMOND, Symbol.TEN)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("카드 합계 출력")
    void getScore() {
        Assertions.assertThat(cards.getScore())
                .isEqualTo(20);
    }

    @Test
    @DisplayName("카드가 21을 넘는지 확인")
    void isOverBlackJack() {
        Assertions.assertThat(cards.isBust())
                .isFalse();

        cards.add(new Card(Type.CLUB, Symbol.TWO));
        Assertions.assertThat(cards.isBust())
                .isTrue();
    }

    @Test
    @DisplayName("카드가 21인지 확인")
    void isBlackJack() {
        Assertions.assertThat(cards.isBlackJack())
                .isFalse();

        cards.add(new Card(Type.CLUB, Symbol.ACE));
        Assertions.assertThat(cards.isBlackJack())
                .isTrue();
    }
}