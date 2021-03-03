package blackjack;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {
    @Test
    @DisplayName("플레이어 카드 추가")
    void addCard() {
        Player player = new Player("wannte");
        Card card = Deck.draw();
        player.addCard(card);
        assertThat(player.getCards()).containsExactly(card);
    }

    @Test
    @DisplayName("보유한 카드 계산 결과")
    void cardValueCalculate() {
        Card card1 = new Card(Number.TWO, Shape.DIAMOND);
        Card card2 = new Card(Number.JACK, Shape.SPADE);
        Card card3 = new Card(Number.THREE, Shape.HEART);
        Player wannte = new Player("wannte");
        wannte.addCard(card1);
        wannte.addCard(card2);
        wannte.addCard(card3);

        assertThat(wannte.calculateCards()).isEqualTo(15);
    }
}