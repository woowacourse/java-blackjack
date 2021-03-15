package blackjack.domain.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static blackjack.domain.card.Deck.TOP_CARD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CardsTest {

    private Cards cards;

    @BeforeEach
    void setUp() {
        cards = new Cards();
    }

    @Test
    @DisplayName("카드 꾸러미 생성 성공")
    void createCards() {
        Cards cards1 = new Cards(Collections.singletonList(
                new Card(Shape.CLOVER, Denomination.FOUR)
        ));
        Cards cards2 = new Cards(Arrays.asList(
                new Card(Shape.SPADE, Denomination.ACE),
                new Card(Shape.HEART, Denomination.FIVE)
        ));
        assertThat(cards1).isEqualTo(new Cards(Collections.singletonList(
                new Card(Shape.CLOVER, Denomination.FOUR)
        )));
        assertThat(cards2).isEqualTo(new Cards(Arrays.asList(
                new Card(Shape.SPADE, Denomination.ACE),
                new Card(Shape.HEART, Denomination.FIVE)
        )));
    }

    @Test
    @DisplayName("카드 꾸러미에 카드 추가 성공")
    void addCardTest() {
        cards.addCard(new Card(Shape.SPADE, Denomination.FIVE));
        assertThat(cards.getCards().get(0)).isEqualTo(new Card(Shape.SPADE, Denomination.FIVE));
    }

    @Test
    @DisplayName("중복된 카드는 추가될 수 없습니다.")
    void duplicateCard() {
        cards.addCard(new Card(Shape.SPADE, Denomination.FIVE));
        assertThatThrownBy(() ->
                cards.addCard(new Card(Shape.SPADE, Denomination.FIVE))
        ).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("카드 꾸러미에서 맨 위에 있는 카드 확인")
    void peekCardTest() {
        cards.addCard(new Card(Shape.SPADE, Denomination.FIVE));
        assertThat(cards.getCards().get(TOP_CARD)).isEqualTo(new Card(Shape.SPADE, Denomination.FIVE));
    }

    @Test
    @DisplayName("Cards 점수 계산")
    void calculatorScore() {
        Cards cards1 = new Cards(Arrays.asList(
                new Card(Shape.SPADE, Denomination.ACE),
                new Card(Shape.SPADE, Denomination.TWO),
                new Card(Shape.HEART, Denomination.ACE)
        ));

        Cards cards2 = new Cards(Arrays.asList(
                new Card(Shape.SPADE, Denomination.ACE),
                new Card(Shape.SPADE, Denomination.JACK)
        ));

        Cards cards3 = new Cards(Arrays.asList(
                new Card(Shape.SPADE, Denomination.ACE),
                new Card(Shape.SPADE, Denomination.EIGHT),
                new Card(Shape.HEART, Denomination.ACE)
        ));

        assertThat(cards1.calculateScore()).isEqualTo(14);
        assertThat(cards2.calculateScore()).isEqualTo(21);
        assertThat(cards3.calculateScore()).isEqualTo(20);
    }

    @Test
    @DisplayName("BlackJack 여부 테스트")
    void isBlackJack() {
        Cards cards = new Cards(
                Arrays.asList(
                        new Card(Shape.SPADE, Denomination.ACE),
                        new Card(Shape.HEART, Denomination.KING)
                )
        );
        assertTrue(cards.isBlackJack());
    }

    @Test
    @DisplayName("Bust 여부 테스트")
    void isBust() {
        Cards cards = new Cards(
                Arrays.asList(
                        new Card(Shape.SPADE, Denomination.QUEEN),
                        new Card(Shape.HEART, Denomination.KING)
                )
        );
        cards.addCard(new Card(Shape.DIAMOND, Denomination.JACK));
        assertTrue(cards.isBust());
    }

    @Test
    @DisplayName("Stay 여부 테스트")
    void isStay() {
        Cards cards = new Cards(
                Arrays.asList(
                        new Card(Shape.SPADE, Denomination.QUEEN),
                        new Card(Shape.HEART, Denomination.KING)
                )
        );
        cards.addCard(new Card(Shape.DIAMOND, Denomination.ACE));
        assertTrue(cards.isStay());
    }
}
