package blackjack.domain.participant;

import blackjack.domain.Money;
import blackjack.domain.carddeck.Card;
import blackjack.domain.carddeck.Number;
import blackjack.domain.carddeck.Pattern;
import blackjack.domain.state.hand.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player(new Name("pobi"), Money.of(1));
        List<Card> cards = Arrays.asList(
                new Card(Pattern.CLOVER, Number.TEN),
                new Card(Pattern.DIAMOND, Number.TEN)
        );
        player.receiveFirstHand(cards);
    }

    @Test
    @DisplayName("플레이어는 카드를 받는다.")
    void testReceiveCard() {
        assertThat(player.getTotalScore()).isEqualTo(new Score(20));
    }
}
