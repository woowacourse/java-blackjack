package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("딜러 카드를 뽑았을 때 플레이어의 점수가 올바르게 계산되는지 검증")
    void draw() {
        Dealer dealer = new Dealer();
        Deck deck = Deck.of(new Card(CardName.JACK, CardType.HEART), new Card(CardName.EIGHT, CardType.HEART));
        dealer.draw(deck, cards -> cards.get(0));

        SummationCardPoint actual = dealer.getSummationCardPoint();
        SummationCardPoint expected = new SummationCardPoint(10);

        Assertions.assertThat(actual)
                .isEqualTo(expected);
    }

}
