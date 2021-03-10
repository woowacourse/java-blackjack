package blackjack.domain.participant;

import blackjack.domain.carddeck.Card;
import blackjack.domain.carddeck.Number;
import blackjack.domain.carddeck.Pattern;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player(new Name("pobi"));
    }

    @Test
    @DisplayName("플레이어는 카드를 받는다.")
    void testReceiveCard() {
        Card card = new Card(Pattern.DIAMOND, Number.EIGHT);
        player.receiveCard(card);

        assertThat(player.getTotalScore()).isEqualTo(new Score(8));
    }
}
