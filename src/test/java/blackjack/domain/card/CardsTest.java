package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {

    @Test
    @DisplayName("게임 시작할 때 두 장의 카드를 갖고 있지 않은 경우 예외 처리 확인")
    void initSizeTest() {
        List<Card> cardList = List.of(new Card(Denomination.TEN, Suit.CLUBS));

        assertThatThrownBy(() -> new Cards(cardList))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("게임을 시작하려면 두 장의 카드");
    }

    @Test
    @DisplayName("중복된 카드가 들어오는 경우")
    void duplicateTest() {
        List<Card> cardList = List.of(new Card(Denomination.TEN, Suit.CLUBS), new Card(Denomination.TEN, Suit.CLUBS));

        assertThatThrownBy(() -> new Cards(cardList))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("중복된 카드");
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
        List<Card> cardList = List.of(new Card(Denomination.ACE, Suit.CLUBS),
            new Card(Denomination.ACE, Suit.DIAMONDS));
        Cards cards = new Cards(cardList);

        assertThat(cards.calculateScore()).isEqualTo(12);
    }
}
