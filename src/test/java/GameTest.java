import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Random;
import org.junit.jupiter.api.Test;

public class GameTest {

    @Test
    void dealer_should_draw_card_until_score_at_least_17() {
        Dealer dealer = new Dealer();
        Random mockRandom = mock(Random.class);
        RandomCardPicker randomCardPicker = new RandomCardPicker(mockRandom);
        CardDistributor cardDistributor = new CardDistributor(randomCardPicker);

        // 2 하트, 2 스페이드, 2 클로버, 2 다이아몬드, 3 하트, 3 스페이드, 3 클로버, 3 다이아몬드, ...
        when(mockRandom.nextInt(52)).thenReturn(0);

        Game game = new Game(cardDistributor);
        game.dealerDrawsCardsUntilDone(dealer);

        assertThat(dealer.calculateTotalScore()).isEqualTo(17);
    }
}
