package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class CardTest {

    @Test
    @DisplayName("Card를 생성한다.")
    void createCardSuccess() {
        Shape diamond = Shape.DIAMOND;
        Value two = Value.TWO;

        Card card = new Card(diamond, two);

        assertThat(card.getShape()).isEqualTo(Shape.DIAMOND);
        assertThat(card.getValue().getExpression()).isEqualTo("2");
        assertThat(card.getValue().getValue()).isEqualTo(2);
    }

    @Test
    @DisplayName("카드는 한 번 뽑으면 삭제된다.")
    void removeCardWhenPicked() {
        CardDeck cardDeck = new CardDeck();

        Card card = cardDeck.pick();

        assertThat(cardDeck.getSize()).isEqualTo(51);
        assertThat(cardDeck.getCards().contains(card)).isFalse();
    }

}
