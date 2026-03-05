import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Random;
import org.junit.jupiter.api.Test;

public class CardDistributorTest {

    @Test
    void distribute_one_card_to_player() {
        Random mockRandom = mock(Random.class);
        RandomCardPicker randomCardPicker = new RandomCardPicker(mockRandom);
        CardDistributor cardDistributor = new CardDistributor(randomCardPicker);

        when(mockRandom.nextInt(13)).thenReturn(0); // "2"
        when(mockRandom.nextInt(4)).thenReturn(0); // "하트"

        Player player = new Player("Player1");
        cardDistributor.distributeCardTo(player);

        assertThat(player.getCards()).containsExactly(new Card("2", "하트"));
    }

    @Test
    void distribute_one_card_to_dealer() {
        Random mockRandom = mock(Random.class);
        RandomCardPicker randomCardPicker = new RandomCardPicker(mockRandom);
        CardDistributor cardDistributor = new CardDistributor(randomCardPicker);

        when(mockRandom.nextInt(13)).thenReturn(0); // "2"
        when(mockRandom.nextInt(4)).thenReturn(0); // "하트"

        Dealer dealer = new Dealer();
        cardDistributor.distributeCardTo(dealer);

        assertThat(dealer.getCards()).containsExactly(new Card("2", "하트"));
    }
}
