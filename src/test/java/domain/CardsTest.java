package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {
    @Test
    @DisplayName("같은 카드 추가 테스트")
    void add() {
        Cards cards = new Cards();
        Card card = new Card(Type.DIAMOND, Symbol.ACE);
        cards.add(card);
        Assertions.assertThatThrownBy(() -> cards.add(card))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("카드 합계 출력")
    void getSum() {
        Cards cards = new Cards();
        cards.add(new Card(Type.DIAMOND, Symbol.TWO));
        cards.add(new Card(Type.DIAMOND, Symbol.THREE));
        cards.add(new Card(Type.DIAMOND, Symbol.ACE));
        cards.add(new Card(Type.CLUB, Symbol.JACK));
        Assertions.assertThat(cards.getSum())
                .isEqualTo(16);
    }

    @Test
    @DisplayName("카드가 21을 넘는지 확인")
    void isOverBlackJack() {
        Cards cards = new Cards();
        cards.add(new Card(Type.DIAMOND, Symbol.TWO));
        cards.add(new Card(Type.DIAMOND, Symbol.THREE));
        cards.add(new Card(Type.DIAMOND, Symbol.JACK));
        Assertions.assertThat(cards.isOverBlackJack())
                .isFalse();

        cards.add(new Card(Type.CLUB, Symbol.JACK));
        Assertions.assertThat(cards.isOverBlackJack())
                .isTrue();
    }

    @Test
    @DisplayName("카드가 21인지 확인")
    void isBlackJack() {
        Cards cards = new Cards();
        cards.add(new Card(Type.DIAMOND, Symbol.TWO));
        cards.add(new Card(Type.DIAMOND, Symbol.THREE));
        cards.add(new Card(Type.DIAMOND, Symbol.JACK));
        Assertions.assertThat(cards.isBlackJack())
                .isFalse();

        cards.add(new Card(Type.CLUB, Symbol.SIX));
        Assertions.assertThat(cards.isBlackJack())
                .isTrue();
    }
}