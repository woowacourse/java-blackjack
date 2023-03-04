package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.participant.Name;
import domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BlackJackGameTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    @DisplayName("참가자에게 올바르게 카드를 준다.")
    void shouldSuccessDistributeCard(int num) {
        Player player = new Player(new Name("dino"));
        BlackJackGame.distributeCard(player, num);

        assertThat(player.getCards().size()).isEqualTo(num);
    }
}
