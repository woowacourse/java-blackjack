package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardGeneratorTest {

    @DisplayName("카드를 랜덤 생성한다.")
    @Test
    void testDealerGenerate() {
        CardGenerator cardGenerator = new CardGenerator();
        Card card = cardGenerator.generate();

        System.out.println(card);
        assertThat(card).isNotNull();
    }

}
