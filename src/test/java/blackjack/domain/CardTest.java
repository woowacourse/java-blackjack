package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    @DisplayName("카드를 생성한다.")
    void createCard() {
        String pattern = "다이아몬드";
        String denomination = "A";

        Card card = new Card(pattern, denomination);

        assertThat(card).isNotNull();
    }
}
