package domain;

import domain.card.Card;
import domain.card.CardHolder;
import domain.card.Number;
import domain.card.Shape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CardHolderTest {
    @Test
    @DisplayName("플레이어에게 카드를 추가할 수 있다")
    void whenGivingCard_thenSuccess() {
        Card card = new Card(Shape.CLOVER, Number.THREE);

        CardHolder cardHolder = new CardHolder(new ArrayList<>());
        cardHolder.addCard(card);

        assertThat(cardHolder.getCards()).contains(card);
    }

    @Test
    @DisplayName("플레이어 점수를 가져올 수 있다.")
    void givenCard_whenGetScore() {
        CardHolder cardHolder = new CardHolder(List.of(new Card(Shape.SPADE, Number.THREE)));
        assertThat(cardHolder.getTotalScore()).isEqualTo(3);
    }

    @Test
    @DisplayName("나머지 점수가 10 이하인 경우, Ace의 점수를 11로 한다.")
    void givenScore_whenOrLessTen() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Shape.SPADE, Number.THREE));
        cards.add(new Card(Shape.HEART, Number.ACE));
        cards.add(new Card(Shape.SPADE, Number.SEVEN));
        CardHolder cardHolder = new CardHolder(cards);

        assertThat(cardHolder.getTotalScore()).isEqualTo(21);
    }

    @Test
    @DisplayName("나머지 점수가 11 이상인 경우, Ace의 점수를 1로 한다.")
    void givenScore_whenOrMoreEleven() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Shape.SPADE, Number.THREE));
        cards.add(new Card(Shape.HEART, Number.ACE));
        cards.add(new Card(Shape.SPADE, Number.EIGHT));
        CardHolder cardHolder = new CardHolder(cards);

        assertThat(cardHolder.getTotalScore()).isEqualTo(12);
    }

    @Test
    @DisplayName("점수가 21을 초과했을 경우 Burst 된다.")
    void givenOverTwentyOneScore_thenBurst() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Shape.SPADE, Number.KING));
        cards.add(new Card(Shape.SPADE, Number.QUEEN));
        cards.add(new Card(Shape.SPADE, Number.TWO));
        CardHolder cardHolder = new CardHolder(cards);

        assertThat(cardHolder.isBust()).isTrue();
    }

    @Test
    @DisplayName("점수가 21 이하일 경우 Burst되지 않는다.")
    void givenOrLessTwentyOneScore_thenNotBurst() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Shape.SPADE, Number.KING));
        cards.add(new Card(Shape.SPADE, Number.ACE));
        cards.add(new Card(Shape.SPADE, Number.QUEEN));
        CardHolder cardHolder = new CardHolder(cards);

        assertThat(cardHolder.isBust()).isFalse();
    }
}