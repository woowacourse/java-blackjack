package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    @DisplayName("플레이어가 카드를 뽑았을 때 플레이어의 점수가 올바르게 계산되는지 검가")
    void draw() {
        Player player = new Player("robin");
        Deck deck = Deck.of(new Card(CardName.JACK, CardType.HEART), new Card(CardName.EIGHT, CardType.HEART));
        player.draw(deck, cards -> cards.get(0));

        SummationCardPoint actual = player.getSummationCardPoint();
        SummationCardPoint expected = new SummationCardPoint(10);

        Assertions.assertThat(actual)
                .isEqualTo(expected);
    }
}
