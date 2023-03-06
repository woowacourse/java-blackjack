package domain;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.Cards;
import domain.card.CardShape;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class CardsTest {

    @Test
    @DisplayName("Cards는 카드들의 리스트로 이루어져 있다.")
    void cardsSumTest() {
        final List<Card> data = List.of(
                Card.create(CardShape.CLOVER, CardNumber.of(1)),
                Card.create(CardShape.DIAMOND, CardNumber.of(2)),
                Card.create(CardShape.HEART, CardNumber.of(3))
        );
        assertDoesNotThrow(() -> Cards.create(data));
    }

    @Test
    @DisplayName("Cards에 Card를 추가할 수 있다.")
    void addCardTest() {
        final List<Card> data = List.of(
                Card.create(CardShape.CLOVER, CardNumber.of(1)),
                Card.create(CardShape.DIAMOND, CardNumber.of(2)),
                Card.create(CardShape.HEART, CardNumber.of(3))
        );
        final Cards cards = Cards.create(data);
        assertThat(cards).extracting("cards", collection(List.class))
                .size().isSameAs(3);

        final Card card = Card.create(CardShape.HEART, CardNumber.of(1));
        assertThat(cards.add(card)).extracting("cards", collection(List.class))
                .size().isSameAs(4);
    }

    @Test
    @DisplayName("Cards에 Card를 추가하면 Point도 업데이트 된다.")
    void addCardPointUpdateTest() {
        final List<Card> data = List.of(
                Card.create(CardShape.CLOVER, CardNumber.of(1)),
                Card.create(CardShape.DIAMOND, CardNumber.of(2)),
                Card.create(CardShape.HEART, CardNumber.of(3))
        );
        final Cards cards = Cards.create(data);
        assertThat(cards)
                .extracting("gamePoint")
                .extracting("gamePoint")
                .isSameAs(16);

        final Card card = Card.create(CardShape.HEART, CardNumber.of(5));

        assertThat(cards.add(card))
                .extracting("gamePoint")
                .extracting("gamePoint")
                .isSameAs(21);
    }

    @Test
    @DisplayName("cards의 bust 상태 테스트")
    void bustTest() {
        final List<Card> data = List.of(
                Card.create(CardShape.HEART, CardNumber.of(10)),
                Card.create(CardShape.HEART, CardNumber.of(10)),
                Card.create(CardShape.HEART, CardNumber.of(10))
        );
        final Cards cards = Cards.create(data);
        assertThat(cards.isBusted()).isTrue();
    }
}
