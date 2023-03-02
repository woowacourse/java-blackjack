package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CardsTest {

    @Test
    @DisplayName("Cards 를 생성한다.")
    void createCardsSuccess() {
        List<Card> initialCards = List.of(new Card(Shape.DIAMOND, Value.TWO), new Card(Shape.HEART, Value.ACE));

        Cards cards = new Cards(initialCards);

        assertThat(cards.getSize()).isEqualTo(2);
    }

}
