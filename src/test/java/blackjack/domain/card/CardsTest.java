package blackjack.domain.card;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {

    @Test
    @DisplayName("Cards 생성시 2장의 카드를 받는다.")
    void startWithDraw() {
        // given
        Card card1 = new Card(Pattern.DIAMOND, Denomination.THREE);
        Card card2 = new Card(Pattern.CLOVER, Denomination.THREE);

        // when
        Cards cards = new Cards(List.of(card1, card2));

        // then
        assertThat(cards.getValues()).containsOnly(card1, card2);
    }

    @Test
    @DisplayName("Cards 생성시 카드가 2장이 아니면 예외가 발생한다.")
    void cardsSizeNotTwo() {
        // given
        Card card1 = new Card(Pattern.DIAMOND, Denomination.THREE);
        Card card2 = new Card(Pattern.CLOVER, Denomination.THREE);
        Card card3 = new Card(Pattern.HEART, Denomination.THREE);

        // then
        assertThatThrownBy(() -> new Cards(List.of(card1, card2, card3)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 카드 2장이 필요합니다.");
    }

    @Test
    @DisplayName("플레이어를 생성할 때 카드가 중복되면 예외가 발생한다.")
    void duplicatedCards() {
        // given
        Card card1 = new Card(Pattern.DIAMOND, Denomination.THREE);
        List<Card> cards = List.of(card1, card1);

        // then
        assertThatThrownBy(() -> new Cards(cards))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 카드는 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("카드를 추가할 수 있다.")
    void addCard() {
        // given
        Card card1 = new Card(Pattern.DIAMOND, Denomination.THREE);
        Card card2 = new Card(Pattern.CLOVER, Denomination.THREE);
        Card card3 = new Card(Pattern.HEART, Denomination.THREE);
        Cards cards = new Cards(List.of(card1, card2));

        // when
        cards.add(card3);

        // then
        assertAll(
            () -> assertThat(cards.isSameSize(3)).isTrue(),
            () -> assertThat(cards.getValues()).containsOnly(card1, card2, card3)
        );
    }

    @Test
    @DisplayName("중복된 카드를 추가할 수 없다.")
    void addDuplicatedCard() {
        // given
        Card card1 = new Card(Pattern.DIAMOND, Denomination.THREE);
        Card card2 = new Card(Pattern.CLOVER, Denomination.THREE);
        Cards cards = new Cards(List.of(card1, card2));

        // then
        assertThatThrownBy(() -> cards.add(card1))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 카드는 중복될 수 없습니다.");
    }
}
