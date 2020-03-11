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
    void getFirstDrawResult() {
        Player player = new Player("이름");
        player.draw(new Card(Symbol.CLOVER, Type.EIGHT));
        player.draw(new Card(Symbol.DIAMOND, Type.ACE));

        assertThat(player.getFirstDrawResult()).isEqualTo("이름: 8클로버, A다이아몬드");
    }
}