package blackjack.domain.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CardsTest {

    private Cards cards;

    @BeforeEach
    void setUp() {
        cards = new Cards();
    }

    @Test
    @DisplayName("카드 꾸러미 생성 성공")
    void createCards() {
        Cards cards1 = new Cards(new Card(Shape.CLOVER, Denomination.FOUR));
        Cards cards2 = new Cards(Arrays.asList(
                new Card(Shape.SPADE, Denomination.ACE),
                new Card(Shape.HEART, Denomination.FIVE)
        ));
        assertThat(cards1).isNotNull();
        assertThat(cards2).isNotNull();
    }

    @Test
    @DisplayName("카드 꾸러미에 카드 추가 성공")
    void addCardTest() {
        cards = cards.addCard(new Card(Shape.SPADE, Denomination.FIVE));
        assertThat(cards.getCards().get(0)).isEqualTo(new Card(Shape.SPADE, Denomination.FIVE));
    }

    @Test
    @DisplayName("카드 꾸러미에서 맨 위에 있는 카드 확인")
    void peekCardTest() {
        cards = cards.addCard(new Card(Shape.SPADE, Denomination.FIVE));
        assertThat(cards.peekCard()).isEqualTo(new Card(Shape.SPADE, Denomination.FIVE));
    }

    @Test
    @DisplayName("카드 꾸러미에서 카드 뽑기 성공")
    void removeCardSucceed() {
        cards = cards.addCard(new Card(Shape.SPADE, Denomination.FIVE));
        cards.drawCard();
        assertThat(cards.getCards().size()).isEqualTo(0);
    }

    @Test
    @DisplayName("카드 꾸러미에서 카드 뽑기 실패")
    void removeCardFail() {
        assertThatThrownBy(() -> cards.drawCard()).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    @DisplayName("카드 점수 계산 테스트")
    void calculateScoreTest() {
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
}
