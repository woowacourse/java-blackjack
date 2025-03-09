package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import model.card.Card;
import model.card.CardNumber;
import model.card.CardShape;
import model.card.Cards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {

    @DisplayName("카드 숫자의 합은 카드 문양과 관계없이 숫자로 계산된다.")
    @Test
    void calculateTotal_UsesCardNumbersNotShapes() {
        Cards cards = new Cards(List.of(
                new Card(CardNumber.THREE, CardShape.DIAMOND),
                new Card(CardNumber.NINE, CardShape.CLOVER),
                new Card(CardNumber.EIGHT, CardShape.DIAMOND)
        ));
        assertThat(cards.calculateResult()).isEqualTo(20);
    }

    @DisplayName("Ace는 합이 21 이하일 경우 11로 계산된다.")
    @Test
    void aceCountsAsEleven_IfTotalDoesNotExceed21() {
        Cards cards = new Cards(List.of(
                new Card(CardNumber.ACE_ELEVEN, CardShape.DIAMOND),
                new Card(CardNumber.NINE, CardShape.CLOVER)
        ));

        assertThat(cards.calculateResult()).isEqualTo(20);
    }

    @DisplayName("Ace는 합이 21을 초과하면 1로 계산된다.")
    @Test
    void aceCountsAsOne_IfTotalExceeds21() {
        Cards cards = new Cards(new ArrayList<>(List.of(
                new Card(CardNumber.ACE_ELEVEN, CardShape.DIAMOND),
                new Card(CardNumber.NINE, CardShape.CLOVER),
                new Card(CardNumber.NINE, CardShape.SPADE)
        )));

        assertThat(cards.calculateResult()).isEqualTo(19);
    }

    @DisplayName("Ace 여러 장이 있을 경우 합이 21을 초과하면 각각 1로 계산된다.")
    @Test
    void multipleAces_CountAsOne_IfTotalExceeds21() {
        Cards cards = new Cards(new ArrayList<>(List.of(
                new Card(CardNumber.ACE_ELEVEN, CardShape.DIAMOND),
                new Card(CardNumber.ACE_ELEVEN, CardShape.SPADE),
                new Card(CardNumber.KING, CardShape.CLOVER),
                new Card(CardNumber.QUEEN, CardShape.SPADE)
        )));

        assertThat(cards.calculateResult()).isEqualTo(22);
    }

    @DisplayName("21을 넘지 않는 한 카드를 계속 추가할 수 있다.")
    @Test
    void canDrawMoreCards_IfTotalDoesNotExceed21() {
        Cards cards = new Cards(new ArrayList<>(List.of(
                new Card(CardNumber.KING, CardShape.SPADE),
                new Card(CardNumber.QUEEN, CardShape.SPADE)))
        );

        Card cardToAdd = new Card(CardNumber.JACK, CardShape.SPADE);
        cards.addCard(cardToAdd);

        assertThat(cards.getCards()).contains(cardToAdd);
    }

    @DisplayName("21을 넘으면 카드를 추가할 수 없다.")
    @Test
    void cannotDrawMoreCards_IfTotalExceeds21() {
        Cards cards = new Cards(new ArrayList<>(List.of(
                new Card(CardNumber.KING, CardShape.SPADE),
                new Card(CardNumber.FIVE, CardShape.SPADE),
                new Card(CardNumber.QUEEN, CardShape.SPADE)))
        );

        Card cardToAdd = new Card(CardNumber.JACK, CardShape.SPADE);

        assertThatThrownBy(() -> cards.addCard(cardToAdd))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("첫 번째 카드를 반환한다.")
    @Test
    void getFirstCard_ReturnsFirstCard() {
        Cards cards = new Cards(new ArrayList<>(List.of(
                new Card(CardNumber.KING, CardShape.SPADE),
                new Card(CardNumber.FIVE, CardShape.SPADE),
                new Card(CardNumber.QUEEN, CardShape.SPADE)))
        );

        Card expected = new Card(CardNumber.KING, CardShape.SPADE);

        assertThat(cards.getFirstCard()).isEqualTo(expected);
    }
}