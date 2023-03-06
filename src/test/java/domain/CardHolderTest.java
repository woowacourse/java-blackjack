package domain;

import domain.card.Card;
import domain.card.CardHolder;
import domain.card.Number;
import domain.card.Shape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class CardHolderTest {
    private CardHolder cardHolder;

    @BeforeEach
    void setUp() {
        cardHolder = new CardHolder(new ArrayList<>());
    }

    @Test
    @DisplayName("카드를 추가한 뒤, 플레이어 점수를 가져올 수 있다.")
    void givenCard_whenGetScore() {
        cardHolder.addCard(new Card(Shape.SPADE, Number.THREE));

        assertThat(cardHolder.getTotalScore()).isEqualTo(3);
    }

    @Test
    @DisplayName("나머지 점수가 10 이하인 경우, Ace의 점수를 11로 한다.")
    void givenScore_whenOrLessTen() {
        cardHolder.addCard(new Card(Shape.SPADE, Number.THREE));
        cardHolder.addCard(new Card(Shape.HEART, Number.ACE));
        cardHolder.addCard(new Card(Shape.SPADE, Number.SEVEN));

        assertThat(cardHolder.getTotalScore()).isEqualTo(21);
    }

    @Test
    @DisplayName("나머지 점수가 11 이상인 경우, Ace의 점수를 1로 한다.")
    void givenScore_whenOrMoreEleven() {
        cardHolder.addCard(new Card(Shape.SPADE, Number.THREE));
        cardHolder.addCard(new Card(Shape.HEART, Number.ACE));
        cardHolder.addCard(new Card(Shape.SPADE, Number.EIGHT));

        assertThat(cardHolder.getTotalScore()).isEqualTo(12);
    }

    @Test
    @DisplayName("점수가 21을 초과했을 경우 Burst 된다.")
    void givenOverTwentyOneScore_thenBurst() {
        cardHolder.addCard(new Card(Shape.SPADE, Number.KING));
        cardHolder.addCard(new Card(Shape.SPADE, Number.QUEEN));
        cardHolder.addCard(new Card(Shape.SPADE, Number.TWO));

        assertThat(cardHolder.isBust()).isTrue();
    }

    @Test
    @DisplayName("점수가 21 이하일 경우 Burst되지 않는다.")
    void givenOrLessTwentyOneScore_thenNotBurst() {
        cardHolder.addCard(new Card(Shape.SPADE, Number.KING));
        cardHolder.addCard(new Card(Shape.SPADE, Number.ACE));
        cardHolder.addCard(new Card(Shape.SPADE, Number.QUEEN));

        assertThat(cardHolder.isBust()).isFalse();
    }
}