package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.shuffler.CardsShuffler;
import domain.card.shuffler.FixedCardsShuffler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class CardsTest {

    private final CardsShuffler fixedShuffler = new FixedCardsShuffler();
    private final Cards cards = new Cards(fixedShuffler);

    @DisplayName("52개의 카드를 생성할 수 있다.")
    @Test
    void createCardsTest() {
        Card card = new Card(Value.FIVE, Shape.DIAMOND);
        assertThat(cards.contains(card)).isTrue();
    }

    @DisplayName("카드를 한 장씩 반환받을 수 있다.")
    @Test
    void receiveCardTest() {
        assertThat(cards.getCard()).isEqualTo(new Card(Value.KING, Shape.HEART));
        assertThat(cards.getCard()).isEqualTo(new Card(Value.KING, Shape.DIAMOND));
    }
}
