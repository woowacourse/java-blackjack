package blackjacktest.domaintest.cardtest;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Shape;
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
}
