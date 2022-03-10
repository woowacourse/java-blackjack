package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {

    @Test
    @DisplayName("카드 목록에 카드 추가 확인")
    void addCardTest() {
        Cards cards = new Cards(new ArrayList<>());

        Card card = Card.draw();
        cards.add(card);
        assertThat(cards.getCards().get(0) == card)
                .isTrue();
    }

    @Test
    @DisplayName("카드 목록의 합 계산 - 에이스 없는 경우")
    void calculateScoreNonAceTest() {
        List<Card> cardList = List.of(new Card(Denomination.TEN, Suit.CLUBS), new Card(Denomination.JACK, Suit.CLUBS));
        Cards cards = new Cards(cardList);
        assertThat(cards.calculateScore()).isEqualTo(20);
    }

    @Test
    @DisplayName("카드 목록의 합 계산 - 에이스 있는 경우")
    void calculateScoreTest() {
        List<Card> cardList = List.of(new Card(Denomination.ACE, Suit.CLUBS), new Card(Denomination.JACK, Suit.CLUBS));
        Cards cards = new Cards(cardList);
        assertThat(cards.calculateScore()).isEqualTo(21);
    }

    @Test
    @DisplayName("카드 목록의 합 계산")
    void calculateScoreTest2() {
        List<Card> cardList = List.of(new Card(Denomination.ACE, Suit.CLUBS), new Card(Denomination.ACE, Suit.CLUBS));
        Cards cards = new Cards(cardList);
        assertThat(cards.calculateScore()).isEqualTo(12);
    }
}