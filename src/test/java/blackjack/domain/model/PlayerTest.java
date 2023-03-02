package blackjack.domain.model;

import blackjack.domain.vo.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatNoException;

public class PlayerTest {
    @Test
    @DisplayName("플레이어 생성 테스트")
    void constructorPlayer(){
        assertThatNoException().isThrownBy(()->new Player(new Name("test"),new Cards()));
    }
}
