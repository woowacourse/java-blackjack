package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CardFactoryTest {

    @DisplayName("카드 52개 생성 확인")
    @Test
    void test1() {
        int actualCardDeckSize = CardFactory.create().size();

        assertThat(actualCardDeckSize).isEqualTo(52);
    }

    @DisplayName("52종류 카드 객체 1회 생성 확인")
    @Test
    void test2() {
        List<Card> cards = CardFactory.create();
        List<Card> anotherCards = CardFactory.create();

        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            Card anotherCard = anotherCards.get(i);
            assertThat(card).isSameAs(anotherCard);
        }
    }

}
