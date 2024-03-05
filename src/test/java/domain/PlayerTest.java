package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    @DisplayName("플레이어가 카드를 뽑았을 때 플레이어의 점수가 올바르게 계산되는지 검증")
    void draw() {
        Player player = new Player("robin");
        Deck deck = Deck.of(new Card(CardName.JACK, CardType.HEART), new Card(CardName.EIGHT, CardType.HEART));
        player.draw(deck, cards -> cards.get(0));

        SummationCardPoint actual = player.getSummationCardPoint();
        SummationCardPoint expected = new SummationCardPoint(10);

        Assertions.assertThat(actual)
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("플레이어는 총합이 21이 넘으면 카드를 뽑을 수 없는지 검증")
    void validateDrawLimit() {
        Player player = new Player("robin");
        Deck deck = Deck.of(
                new Card(CardName.JACK, CardType.HEART),
                new Card(CardName.EIGHT, CardType.HEART),
                new Card(CardName.JACK, CardType.SPADE),
                new Card(CardName.TWO, CardType.SPADE)
        );

        player.draw(deck, cards -> cards.get(0));
        player.draw(deck, cards -> cards.get(0));
        player.draw(deck, cards -> cards.get(0));

        Assertions.assertThatThrownBy(() -> player.draw(deck, cards -> cards.get(0)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("카드를 더이상 뽑을 수 없습니다.");
    }
}
