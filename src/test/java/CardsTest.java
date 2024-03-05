import static org.assertj.core.api.Assertions.assertThat;

import domain.Card;
import domain.Cards;
import domain.constants.CardValue;
import domain.constants.Shape;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {
    @DisplayName("블랙잭에 필요한 카드들을 생성해둔다.")
    @Test
    void createCards() {
        Cards cards = new Cards();

        assertThat(cards.getTotalSize()).isEqualTo(52);
    }

    @DisplayName("하나의 카드를 뽑는다.")
    @Test
    void pickCard() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(CardValue.ACE, Shape.CLOVER));
        cards.add(new Card(CardValue.FIVE, Shape.DIAMOND));
        cards.add(new Card(CardValue.EIGHT, Shape.HEART));

        Cards createdCards = new Cards(cards);
        Card picked = createdCards.pick();
        Assertions.assertThat(picked).isEqualTo(new Card(CardValue.ACE, Shape.CLOVER));
    }
}
