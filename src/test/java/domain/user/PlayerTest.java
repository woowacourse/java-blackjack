package domain.user;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.deck.Card;
import domain.deck.Symbol;
import domain.deck.Type;

class PlayerTest {

    @Test
    @DisplayName("생성 확인")
    void create() {
        assertThatCode(() -> new Player("이름"))
                .doesNotThrowAnyException();
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
        Player player = new Player("이름");
        assertThat(player.getName()).isEqualTo("이름");
    }

    @Test
    void draw() {
        Player player = new Player("이름");
        int initSize = player.cards.size();

        player.draw(new Card(Symbol.CLOVER, Type.ACE));

        assertThat(player.cards.size()).isEqualTo(initSize + 1);
    }
}