package domain;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackJackGameTest {
    @Test
    @DisplayName("GameStatusDto를 반환한다.")
    void getGameStatusDto() {
        BlackJackGame blackJackGame = new BlackJackGame(List.of(
                new Gamer(new Name("test"))
        ));
        Assertions.assertThat(blackJackGame.getGameStatusDto()).isNotNull();
    }

    @Test
    @DisplayName("모든 Gamer들에게 카드를 2장씩 나눠준다")
    void initialDealing() {
        BlackJackGame blackJackGame = new BlackJackGame(List.of(
                new Gamer(new Name("test"))
        ));
        blackJackGame.initialDealing();
        Assertions.assertThat(blackJackGame.getGameStatusDto()
                        .getGamerDtoFromName("test")
                        .getCards().size())
                .isEqualTo(2);
    }
}
