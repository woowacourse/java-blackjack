package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {

    @DisplayName("동일 객체 테스트")
    @Test
    void create() {
        Cards cards = Cards.getInstance();
        assertThat(cards).isSameAs(Cards.getInstance());
    }

    @DisplayName("card 뽑기 테스트")
    @Test
    void drawCard() {
        List<Card> cards = new ArrayList<>();
        Arrays.stream(CardShape.values())
            .forEach(shape -> Arrays.stream(CardNumber.values())
                .forEach(number -> cards.add(Card.valueOf(shape, number))));

        Card card = Cards.getInstance().draw();
        assertThat(cards).contains(card);
    }
}