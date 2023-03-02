package domain;

import domain.participant.Name;
import domain.participant.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BlackJackGameTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    @DisplayName("참가자에게 올바르게 카드를 준다.")
    void shouldSuccessDistributeCard(int num) {
        BlackJackGame blackJackGame = new BlackJackGame();
        Player player = new Player(new Name("dino"));
        blackJackGame.distributeCard(player, num);

        Assertions.assertThat(player.getCards().size()).isEqualTo(num);
    }
}
