package domain.user;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.deck.Card;
import domain.deck.Symbol;
import domain.deck.Type;

class UserTest {

    @Test
    @DisplayName("빈 이름이 있는 경우 예외 처리")
    void createPlayerWithEmptyName() {
        assertThatThrownBy(() -> new Player(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("빈 이름이 있습니다.");
    }

    @Test
    void getName() {
        User user = new Player("이름");
        assertThat(user.getName()).isEqualTo("이름");
    }

    @Test
    @DisplayName("카드 한장 분배")
    void draw() {
        User user = new Player("이름");
        int initSize = user.cards.size();

        user.draw(new Card(Symbol.CLOVER, Type.ACE));

        assertThat(user.cards.size()).isEqualTo(initSize + 1);
    }
}