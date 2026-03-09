import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import blackjack.domain.Card;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import blackjack.domain.Rank;
import blackjack.domain.Shape;
import org.junit.jupiter.api.Test;
import blackjack.service.RandomCardPicker;

public class RandomCardPickerTest {
    @Test
    void draw_random_card() {
        Random mockRandom = mock(Random.class);
        RandomCardPicker randomCardPicker = new RandomCardPicker(mockRandom);

        when(mockRandom.nextInt(13)).thenReturn(0); // "2"
        when(mockRandom.nextInt(4)).thenReturn(0); // "하트"

        assertThat(randomCardPicker.drawCard()).isEqualTo(new Card(Rank.TWO, Shape.HEART));
    }

    @Test
    void same_card_is_not_drawn_twice() {
        RandomCardPicker randomCardPicker = new RandomCardPicker(new Random());

        Set<Card> drawn = new HashSet<>();

        for (int i = 0; i < 52; i++) {
            Card card = randomCardPicker.drawCard();
            assertThat(drawn).doesNotContain(card);
            drawn.add(card);
        }
    }
}
