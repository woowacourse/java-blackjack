package domain.user;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;

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
    @DisplayName("첫 카드 분배 결과")
    void getDrawResult() {
        user.draw(new Card(Symbol.CLOVER, Type.EIGHT));
        user.draw(new Card(Symbol.DIAMOND, Type.ACE));

        assertThat(user.getDrawResult()).isEqualTo("이름카드: 8클로버, A다이아몬드");
    }

    @Test
    @DisplayName("최종 보유 카드 및 포인트 계산 결과")
    void getTotalDrawResult() {
        user.draw(new Card(Symbol.DIAMOND, Type.KING));
        user.draw(new Card(Symbol.DIAMOND, Type.SIX));

        assertThat(user.getTotalDrawResult()).isEqualTo("이름카드: K다이아몬드, 6다이아몬드 - 결과: 16");
    }
}