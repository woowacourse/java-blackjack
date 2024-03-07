package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    @DisplayName("Players를 생성한다")
    @Test
    public void create() {
        assertThatCode(() -> new Players(List.of(new Player("mark"), new Player("sang"))))
                .doesNotThrowAnyException();
    }


}
