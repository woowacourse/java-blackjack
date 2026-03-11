package blackjackTest.service;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Card;
import java.util.HashSet;
import java.util.Random;

import blackjack.domain.Rank;
import blackjack.domain.Shape;
import blackjack.service.NumberGenerator;
import org.junit.jupiter.api.Test;
import blackjack.service.RandomCardPicker;

public class RandomCardPickerTest {
    @Test
    void draw_random_card() {
        NumberGenerator randomGenerator = new NumberGenerator() {
            @Override
            public int generate(int max) {
                return 0;
            }
        };
        RandomCardPicker cardPicker = new RandomCardPicker(randomGenerator);
        assertThat(cardPicker.drawCard()).isEqualTo(new Card(Rank.TWO, Shape.HEART));
    }

    @Test
    void same_card_is_not_drawn_twice() {
        NumberGenerator randomGenerator = getNumberGenerator();
        RandomCardPicker cardPicker = new RandomCardPicker(randomGenerator);
        HashSet<Card> drawn = new HashSet<>();

        for (int i = 0; i < 52; i++) {
            Card card = cardPicker.drawCard();
            assertThat(drawn).doesNotContain(card);
            drawn.add(card);
        }
    }

    private static NumberGenerator getNumberGenerator() {
        return new NumberGenerator() {
            private final Random random = new Random();
            @Override
            public int generate(int max) {
                return random.nextInt(max);
            }
        };
    }
}
