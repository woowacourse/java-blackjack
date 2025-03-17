package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

@DisplayName("여러 카드 테스트")
class CardsTest {

    @Test
    @DisplayName("카드 객체를 생성한다")
    void createCardObject() {
        assertThatNoException()
                .isThrownBy(Cards::new);
    }

    @Test
    @DisplayName("카드 리스트로 객체를 생성한다")
    void createObjectWithCardList() {
        Card card1 = new Card(CardNumber.EIGHT, CardShape.CLOVER);
        Card card2 = new Card(CardNumber.ACE, CardShape.CLOVER);
        List<Card> cards = List.of(card1, card2);

        Cards newCards = new Cards(cards);
        List<Card> result = newCards.getCards();

        assertThat(result).containsExactlyElementsOf(cards);
    }

    @Test
    @DisplayName("카드를 추가한다")
    void addCard() {
        Card card = new Card(CardNumber.EIGHT, CardShape.CLOVER);
        List<Card> cards = List.of(card);

        Cards addCards = new Cards(cards);

        Cards newCards = new Cards();
        newCards.addCard(addCards);

        List<Card> result = newCards.getCards();

        assertThat(result).containsExactlyElementsOf(cards);
    }

    @Test
    @DisplayName("카드 점수 종합을 반환한다")
    void returnSumOfCardScores() {
        Card card1 = new Card(CardNumber.EIGHT, CardShape.CLOVER);
        Card card2 = new Card(CardNumber.ACE, CardShape.CLOVER);

        Cards cards = new Cards(List.of(card1, card2));
        int result = cards.sumCardScore();

        assertThat(result).isEqualTo(9);
    }

    @Test
    @DisplayName("에이스 카드를 카운트해 반환한다")
    void countAndReturnAceCards() {
        Card card1 = new Card(CardNumber.EIGHT, CardShape.CLOVER);
        Card card2 = new Card(CardNumber.ACE, CardShape.CLOVER);

        Cards cards = new Cards(List.of(card1, card2));
        long result = cards.countAceCard();

        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("블랙잭 개수에 알맞는지 판단한다")
    void determineIfBlackJackSize() {
        Card card1 = new Card(CardNumber.ACE, CardShape.CLOVER);
        Card card2 = new Card(CardNumber.JACK, CardShape.CLOVER);

        Cards cards = new Cards(List.of(card1, card2));
        boolean result = cards.isBlackJackSize();

        assertThat(result).isTrue();
    }
}
