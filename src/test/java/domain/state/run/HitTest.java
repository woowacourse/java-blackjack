package domain.state.run;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Hands;
import domain.card.Rank;
import domain.card.Suit;
import domain.state.State;
import domain.state.fininsh.Bust;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HitTest {

    @Test
    @DisplayName("카드를 뽑은 후 카드의 합이 21미만일 때 상태는 히트다")
    void hit() {
        final State hit = new Hit(new Hands(new ArrayList<>(List.of(new Card(Rank.SEVEN, Suit.CLUBS)))));

        final State newState = hit.add(new Card(Rank.THREE, Suit.CLUBS));

        assertThat(newState).isInstanceOf(Hit.class);
    }

    @Test
    @DisplayName("카드를 뽑은 후 카드의 합이 21을 초과할 때 상태는 버스트다")
    void bust() {
        State hit = new Hit(new Hands(new ArrayList<>(List.of(new Card(Rank.TEN, Suit.CLUBS)))));

        hit = hit.add(new Card(Rank.TEN, Suit.CLUBS));
        hit = hit.add(new Card(Rank.TEN, Suit.CLUBS));

        assertThat(hit).isInstanceOf(Bust.class);
    }

}
