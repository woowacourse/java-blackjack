package model.cards;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import exception.IllegalBlackjackStateException;
import java.util.ArrayList;
import java.util.List;
import model.card.Card;
import model.card.CardNumber;
import model.card.CardShape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerCardsTest {

    @DisplayName("21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다.")
    @Test
    void addCardTest() {
        Cards cards = new PlayerCards(new ArrayList<>(List.of(
                new Card(CardNumber.KING, CardShape.SPADE),
                new Card(CardNumber.FIVE, CardShape.SPADE)))
        );
        Card cardToAdd = new Card(CardNumber.JACK, CardShape.SPADE);
        cards.addCard(cardToAdd);

        assertThat(cards.getCards()).containsExactlyInAnyOrder(
                new Card(CardNumber.KING, CardShape.SPADE),
                new Card(CardNumber.FIVE, CardShape.SPADE),
                new Card(CardNumber.JACK, CardShape.SPADE)
        );
    }

    @DisplayName("21을 넘었을 때 카드를 뽑으려하면 예외를 발생시킨다.")
    @Test
    void addCardOver21Test() {
        Cards cards = new PlayerCards(new ArrayList<>(List.of(
                new Card(CardNumber.KING, CardShape.SPADE),
                new Card(CardNumber.FIVE, CardShape.SPADE),
                new Card(CardNumber.QUEEN, CardShape.SPADE)))
        );

        Card cardToAdd = new Card(CardNumber.JACK, CardShape.SPADE);

        assertThatThrownBy(() -> cards.addCard(cardToAdd))
                .isInstanceOf(IllegalBlackjackStateException.class);
    }

    @DisplayName("카드 계산 결과가 21을 초과하면 버스트이다.")
    @Test
    void bustTest() {
        Cards cards = new PlayerCards(List.of(
                new Card(CardNumber.THREE, CardShape.DIAMOND),
                new Card(CardNumber.NINE, CardShape.CLOVER),
                new Card(CardNumber.TEN, CardShape.DIAMOND)
        ));
        assertThat(cards.isBust()).isTrue();
    }

    @DisplayName("카드 계산 결과가 21을 초과하지 않는다면 버스트가 아니다.")
    @Test
    void notBustTest() {
        Cards cards = new PlayerCards(List.of(
                new Card(CardNumber.THREE, CardShape.DIAMOND),
                new Card(CardNumber.NINE, CardShape.CLOVER),
                new Card(CardNumber.NINE, CardShape.DIAMOND)
        ));
        assertThat(cards.isBust()).isFalse();
    }

    @DisplayName("카드 계산 결과가 21 미만이라면 카드를 더 뽑을 수 있다.")
    @Test
    void canDrawTest() {
        PlayerCards cards = new PlayerCards(List.of(
                new Card(CardNumber.THREE, CardShape.DIAMOND),
                new Card(CardNumber.NINE, CardShape.CLOVER),
                new Card(CardNumber.EIGHT, CardShape.DIAMOND)
        ));
        assertThat(cards.canDraw()).isTrue();
    }

    @DisplayName("카드 계산 결과가 21 이상이라면 카드를 더 뽑을 수 없다.")
    @Test
    void canNotDrawTest() {
        PlayerCards cards = new PlayerCards(List.of(
                new Card(CardNumber.THREE, CardShape.DIAMOND),
                new Card(CardNumber.NINE, CardShape.CLOVER),
                new Card(CardNumber.NINE, CardShape.DIAMOND)
        ));
        assertThat(cards.canDraw()).isFalse();
    }

    @DisplayName("카드의 숫자 계산은 카드 문양이 아닌 카드 숫자로 한다.")
    @Test
    void calculateResultTest() {
        Cards cards = new PlayerCards(List.of(
                new Card(CardNumber.THREE, CardShape.DIAMOND),
                new Card(CardNumber.NINE, CardShape.CLOVER),
                new Card(CardNumber.EIGHT, CardShape.DIAMOND)
        ));
        assertThat(cards.calculateResult()).isEqualTo(20);
    }

    @DisplayName("Ace는 카드의 합이 21을 초과하기 전까지는 11로 계산한다.")
    @Test
    void calculateResultWithAceEleven() {
        Cards cards = new PlayerCards(List.of(
                new Card(CardNumber.ACE_ELEVEN, CardShape.DIAMOND),
                new Card(CardNumber.NINE, CardShape.CLOVER)
        ));

        assertThat(cards.calculateResult()).isEqualTo(20);
    }

    @DisplayName("예외로 Ace는 카드의 합이 21을 초과하면 1로 계산한다.")
    @Test
    void calculateResultWithAceOne() {
        Cards cards = new PlayerCards(new ArrayList<>(List.of(
                new Card(CardNumber.ACE_ELEVEN, CardShape.DIAMOND),
                new Card(CardNumber.NINE, CardShape.CLOVER),
                new Card(CardNumber.NINE, CardShape.SPADE)
        )));

        assertThat(cards.calculateResult()).isEqualTo(19);
    }

    @DisplayName("예외로 Ace는 카드의 합이 21을 초과하면 1로 계산한다.")
    @Test
    void calculateResultWithAceOneOver21() {
        Cards cards = new PlayerCards(new ArrayList<>(List.of(
                new Card(CardNumber.ACE_ELEVEN, CardShape.DIAMOND),
                new Card(CardNumber.ACE_ELEVEN, CardShape.SPADE),
                new Card(CardNumber.KING, CardShape.CLOVER),
                new Card(CardNumber.QUEEN, CardShape.SPADE)
        )));

        assertThat(cards.calculateResult()).isEqualTo(22);
    }

}
