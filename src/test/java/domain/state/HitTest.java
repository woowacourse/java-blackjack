package domain.state;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Shape;
import domain.card.Value;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HitTest {

    @DisplayName("추가로 카드를 뽑은 후, 21 이하면 Hit 상태가 되는지 테스트")
    @Test
    void drawCardHitTest() {
        List<Card> initialCards = List.of(new Card(Value.ACE, Shape.HEART));
        Hand hand = new Hand(new ArrayList<>(initialCards));
        State hit = new Hit(hand);

        assertThat(hit.drawCard(new Card(Value.THREE, Shape.SPADE))).isInstanceOf(Hit.class);
    }

    @DisplayName("추가로 카드를 뽑은 후, 21을 넘으면 Bust 상태가 되는지 테스트")
    @Test
    void drawCardBustTest() {
        List<Card> initialCards = List.of(new Card(Value.JACK, Shape.HEART), new Card(Value.KING, Shape.HEART));
        Hand hand = new Hand(new ArrayList<>(initialCards));
        State hit = new Hit(hand);

        assertThat(hit.drawCard(new Card(Value.THREE, Shape.SPADE))).isInstanceOf(Bust.class);
    }
}
