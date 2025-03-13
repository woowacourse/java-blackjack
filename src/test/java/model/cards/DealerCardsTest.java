package model.cards;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import model.card.Card;
import model.card.CardNumber;
import model.card.CardShape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerCardsTest {

    @DisplayName("첫 번째 순서의 카드를 반환한다.")
    @Test
    void test1() {
        DealerCards cards = new DealerCards(new ArrayList<>(List.of(
                new Card(CardNumber.KING, CardShape.SPADE),
                new Card(CardNumber.FIVE, CardShape.SPADE),
                new Card(CardNumber.QUEEN, CardShape.SPADE)))
        );

        Card expected = new Card(CardNumber.KING, CardShape.SPADE);

        assertThat(cards.getFirstCard()).isEqualTo(expected);
    }

    @DisplayName("딜러가 추가로 뽑은 카드의 수를 반환한다.")
    @Test
    void test2() {
        DealerCards cards = new DealerCards(new ArrayList<>(List.of(
                new Card(CardNumber.KING, CardShape.SPADE),
                new Card(CardNumber.FIVE, CardShape.SPADE),
                new Card(CardNumber.QUEEN, CardShape.SPADE)))
        );

        assertThat(cards.getAdditionalDrawCount()).isEqualTo(1);
    }


    @DisplayName("21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다.")
    @Test
    void addCardTest() {
        Cards cards = new DealerCards(new ArrayList<>(List.of(
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
        Cards cards = new DealerCards(new ArrayList<>(List.of(
                new Card(CardNumber.KING, CardShape.SPADE),
                new Card(CardNumber.FIVE, CardShape.SPADE),
                new Card(CardNumber.QUEEN, CardShape.SPADE)))
        );

        Card cardToAdd = new Card(CardNumber.JACK, CardShape.SPADE);

        assertThatThrownBy(() -> cards.addCard(cardToAdd));
    }

    @DisplayName("카드 계산 결과가 21을 초과하면 버스트이다.")
    @Test
    void bustTest() {
        Cards cards = new DealerCards(List.of(
                new Card(CardNumber.THREE, CardShape.DIAMOND),
                new Card(CardNumber.NINE, CardShape.CLOVER),
                new Card(CardNumber.TEN, CardShape.DIAMOND)
        ));
        assertThat(cards.isBust()).isTrue();
    }

    @DisplayName("카드 계산 결과가 21을 초과하지 않는다면 버스트가 아니다.")
    @Test
    void notBustTest() {
        Cards cards = new DealerCards(List.of(
                new Card(CardNumber.THREE, CardShape.DIAMOND),
                new Card(CardNumber.NINE, CardShape.CLOVER),
                new Card(CardNumber.NINE, CardShape.DIAMOND)
        ));
        assertThat(cards.isBust()).isFalse();
    }

    @DisplayName("카드의 숫자 계산은 카드 문양이 아닌 카드 숫자로 한다.")
    @Test
    void calculateResultTest() {
        Cards cards = new DealerCards(List.of(
                new Card(CardNumber.THREE, CardShape.DIAMOND),
                new Card(CardNumber.NINE, CardShape.CLOVER),
                new Card(CardNumber.EIGHT, CardShape.DIAMOND)
        ));
        assertThat(cards.calculateResult()).isEqualTo(20);
    }

    @DisplayName("Ace는 카드의 합이 21을 초과하기 전까지는 11로 계산한다.")
    @Test
    void calculateResultWithAceEleven() {
        Cards cards = new DealerCards(List.of(
                new Card(CardNumber.ACE_ELEVEN, CardShape.DIAMOND),
                new Card(CardNumber.NINE, CardShape.CLOVER)
        ));

        assertThat(cards.calculateResult()).isEqualTo(20);
    }

    @DisplayName("예외로 Ace는 카드의 합이 21을 초과하면 1로 계산한다.")
    @Test
    void calculateResultWithAceOne() {
        Cards cards = new DealerCards(new ArrayList<>(List.of(
                new Card(CardNumber.ACE_ELEVEN, CardShape.DIAMOND),
                new Card(CardNumber.NINE, CardShape.CLOVER),
                new Card(CardNumber.NINE, CardShape.SPADE)
        )));

        assertThat(cards.calculateResult()).isEqualTo(19);
    }

    @DisplayName("예외로 Ace는 카드의 합이 21을 초과하면 1로 계산한다.")
    @Test
    void calculateResultWithAceOneOver21() {
        Cards cards = new DealerCards(new ArrayList<>(List.of(
                new Card(CardNumber.ACE_ELEVEN, CardShape.DIAMOND),
                new Card(CardNumber.ACE_ELEVEN, CardShape.SPADE),
                new Card(CardNumber.KING, CardShape.CLOVER),
                new Card(CardNumber.QUEEN, CardShape.SPADE)
        )));

        assertThat(cards.calculateResult()).isEqualTo(22);
    }
}
