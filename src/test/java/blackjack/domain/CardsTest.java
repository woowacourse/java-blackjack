package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardsTest {
    @DisplayName("Cards 객체를 생성한다.")
    @Test
    public void createCards() {
        Cards cards = new Cards(CardFactory.make());

        assertThat(cards).isInstanceOf(Cards.class);
    }
}
