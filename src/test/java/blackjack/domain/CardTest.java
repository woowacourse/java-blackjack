package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    @DisplayName("카드 생성 확인")
    void createCard() {
        String pattern = "하트";
        String point = "King";

        Card card = new Card(pattern, point);

        assertThat(card).isInstanceOf(Card.class);
    }


}
