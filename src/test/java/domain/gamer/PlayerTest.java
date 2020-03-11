package domain.gamer;

import static org.assertj.core.api.Assertions.*;

import exception.NameFormatException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    @Test
    @DisplayName("플레이어가 카드를 추가로 받는 경우")
    void isDrawCardTest() {
        Assertions.assertThat(Player.isDrawable(YesOrNo.YES)).isTrue();
    }

    @Test
    @DisplayName("플레이어가 카드를 추가로 받는 경우")
    void isNotDrawCardTest() {
        Assertions.assertThat(Player.isDrawable(YesOrNo.NO)).isFalse();
    }

    @Test
    @DisplayName("잘못된 이름 입력시 예외처리")
    void isValidNameTest() {
        assertThatThrownBy(() -> new Player("po/bi")).isInstanceOf(NameFormatException.class);
    }
}

//Arrays.asList(
//			new Card(CardSuit.CLOVER, CardNumber.SEVEN),
//			new Card(CardSuit.CLOVER, CardNumber.TEN))
//		)