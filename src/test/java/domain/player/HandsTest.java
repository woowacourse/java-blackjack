package domain.player;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import domain.card.Card;
import domain.card.Hands;
import domain.card.Rank;
import domain.card.Suit;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandsTest {

    @Test
    @DisplayName("카드 한 장이 추가되면 핸즈의 사이즈도 1만큼 늘어난다")
    void hit() {
        final Hands hands = new Hands(new ArrayList<>());
        hands.add(new Card(Rank.ACE, Suit.CLUBS));
        assertThat(hands.getValue().size()).isEqualTo(1);
    }
}
