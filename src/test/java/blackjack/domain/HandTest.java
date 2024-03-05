package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        Hand hand = new Hand();

        List<Card> cards = hand.getCards();

        assertThat(cards).isEmpty();
    }

    @DisplayName("패는 카드를 받을 수 있다.")
    @Test
    void add() {
        Hand hand = new Hand();

        hand.add(new Card(CardRank.EIGHT, CardShape.DIAMOND));

        List<Card> cards = hand.getCards();
        assertThat(cards.size()).isEqualTo(1);
    }
}
