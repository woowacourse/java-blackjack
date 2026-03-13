package model.card;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CardsTest {
    private Cards cards;

    @BeforeEach
    void setUp() {
        cards = new Cards();
        cards.add(new Card(Shape.DIAMOND, CardNumber.NINE));
        cards.add( new Card(Shape.CLOVER, CardNumber.QUEEN));
    }

    @Test
    public void 카드_저장_정상_작동() {
        cards.add(new Card(Shape.CLOVER, CardNumber.KING));

        List<Card> currentCard = cards.get();

        assertThat(cards.getSize()).isEqualTo(3);
        assertThat(currentCard.getFirst()).isEqualTo(new Card(Shape.DIAMOND, CardNumber.NINE));
        assertThat(currentCard.getLast()).isEqualTo(new Card(Shape.CLOVER, CardNumber.KING));
    }

    @Test
    public void ACE_저장_여부_정상_작동() {
        assertThat(cards.hasAce()).isFalse();

        cards.add(new Card(Shape.CLOVER, CardNumber.ACE));
        assertThat(cards.hasAce()).isTrue();

        cards.add(new Card(Shape.CLOVER, CardNumber.KING));
        assertThat(cards.hasAce()).isTrue();
    }

    @Test
    public void 생성자로_리스트_주입_시_ACE_저장_여부_정상_작동() {
        Cards cards1 = new Cards(List.of(new Card(Shape.CLOVER, CardNumber.KING)));
        Cards cards2 = new Cards(List.of(new Card(Shape.DIAMOND, CardNumber.ACE)));
        Cards cards3 = new Cards(List.of());

        assertAll(
                () -> assertThat(cards1.hasAce()).isFalse(),
                () -> assertThat(cards2.hasAce()).isTrue(),
                () ->assertThat(cards3.hasAce()).isFalse()
        );
    }
}
