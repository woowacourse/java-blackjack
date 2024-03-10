package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.result.Score;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class HandsTest {
    @DisplayName("중복된 카드가 포함된 카드 더미를 생성할 수 없다.")
    @Test
    void validateDuplicate() {
        Card card = new Card(CardNumber.ACE, CardShape.HEART);
        List<Card> cards = List.of(card, card);

        assertThatThrownBy(() -> new Hands(cards))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("중복된 카드는 존재할 수 없습니다.");
    }

    @DisplayName("게임에 유리한 방향으로 ACE를 1, 11 중 선택하여 계산한다.")
    @ParameterizedTest
    @CsvSource(value = {"ACE,NINE,TWO,TWO,14",
            "ACE,TWO,TWO,TWO,17",
            "ACE,ACE,NINE,NINE,20",
            "ACE,ACE,TWO,TWO,16",
            "ACE,ACE,ACE,TEN,13",
            "ACE,ACE,ACE,ACE,14",})
    void calculateWith2Ace(CardNumber cardNumber1,
            CardNumber cardNumber2,
            CardNumber cardNumber3,
            CardNumber cardNumber4,
            int expected) {
        // given
        Card card1 = new Card(cardNumber1, CardShape.DIA);
        Card card2 = new Card(cardNumber2, CardShape.HEART);
        Card card3 = new Card(cardNumber3, CardShape.CLOVER);
        Card card4 = new Card(cardNumber4, CardShape.SPADE);
        List<Card> cards = List.of(card1, card2, card3, card4);
        Hands hands = new Hands(cards);

        // when
        Score sum = hands.calculateScore();

        // then
        assertThat(sum).isEqualTo(new Score(expected));
    }


    @DisplayName("카드를 추가한다.")
    @Test
    void add() {
        // given
        Card card1 = new Card(CardNumber.ACE, CardShape.HEART);
        Card card2 = new Card(CardNumber.ACE, CardShape.SPADE);
        Hands hands = new Hands(List.of(card1, card2));

        Card card3 = new Card(CardNumber.ACE, CardShape.CLOVER);

        // when
        hands.add(card3);

        // then
        assertThat(hands.getCards()).containsExactly(card1, card2, card3);
    }

    @DisplayName("첫번째 카드를 반환한다.")
    @Test
    void getFirstCard() {
        // given
        Card card1 = new Card(CardNumber.ACE, CardShape.HEART);
        Card card2 = new Card(CardNumber.ACE, CardShape.SPADE);
        Hands hands = new Hands(List.of(card1, card2));

        // when
        final Hands firstCard = hands.getFirstCard();

        // then
        assertThat(firstCard.getCards())
                .hasSize(1)
                .containsExactly(card1);
    }
}
