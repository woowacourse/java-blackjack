package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;

public class CardGeneratorTest {

    @Test
    @DisplayName("52개의 카드를 생성한다.")
    void createCard() {
        CardGenerator cardGenerator = new CardGenerator();
        Queue<Card> cards = cardGenerator.generate();

        assertThat(cards.size()).isEqualTo(52);
    }

}
