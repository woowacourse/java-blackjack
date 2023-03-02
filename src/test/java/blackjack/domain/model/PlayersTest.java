package blackjack.domain.model;

import blackjack.domain.vo.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatNoException;

class PlayersTest {

    @Test
    @DisplayName("플레이어들 생성 테스트")
    void constructorPlayersTest() {
        assertThatNoException().isThrownBy(()->new Players(List.of(new Player(new Name("test"), new Cards()))));
    }

}
