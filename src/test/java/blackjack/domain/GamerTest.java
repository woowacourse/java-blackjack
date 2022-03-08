package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GamerTest {

    @Test
    @DisplayName("이름이 딜러인경우 예외를 발생시킨다.")
    void createGamerExceptionNameDealer() {
        assertThatThrownBy(()-> new Gamer("딜러"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] Gamer의 이름은 딜러일 수 없습니다.");
    }
}
