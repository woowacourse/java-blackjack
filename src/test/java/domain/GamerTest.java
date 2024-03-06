package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GamerTest {

    @Test
    @DisplayName("게임 참가자가 카드를 뽑았을 때 점수가 올바르게 계산되는지 검증")
    void draw() {
        Gamer Gamer = new Gamer("robin", HoldingCards.of());
        Deck deck = Deck.of(new Card(CardName.JACK, CardType.HEART), new Card(CardName.EIGHT, CardType.HEART));
        Gamer.draw(deck, cards -> cards.get(0), new SummationCardPoint(21));

        SummationCardPoint actual = Gamer.getSummationCardPoint();
        SummationCardPoint expected = new SummationCardPoint(10);

        Assertions.assertThat(actual)
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("플레이어는 총합이 21이 넘으면 카드를 뽑을 수 없는지 검증")
    void validateDrawLimit() {
        Gamer Gamer = new Gamer("robin", HoldingCards.of(
                new Card(CardName.JACK, CardType.HEART),
                new Card(CardName.EIGHT, CardType.HEART),
                new Card(CardName.JACK, CardType.SPADE)
        ));
        Deck deck = Deck.of(
                new Card(CardName.TWO, CardType.SPADE)
        );

        Assertions.assertThatThrownBy(() -> Gamer.draw(deck, cards -> cards.get(0), new SummationCardPoint(21)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("카드를 더이상 뽑을 수 없습니다.");
    }

    @Test
    @DisplayName("딜러는 총합이 16이 넘으면 카드를 뽑을 수 없는지 검증")
    void validateDealerDrawLimit() {
        Gamer Gamer = new Gamer("robin", HoldingCards.of(
                new Card(CardName.JACK, CardType.HEART),
                new Card(CardName.SEVEN, CardType.HEART)
        ));
        Deck deck = Deck.of(
                new Card(CardName.TWO, CardType.SPADE)
        );

        Assertions.assertThatThrownBy(() -> Gamer.draw(deck, cards -> cards.get(0), new SummationCardPoint(16)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("카드를 더이상 뽑을 수 없습니다.");
    }
}
