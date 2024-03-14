package domain.state.run;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.player.Hands;
import domain.state.State;
import domain.state.fininsh.Blackjack;
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

        final State newState = hit.draw(new Card(Rank.THREE, Suit.CLUBS));

        assertThat(newState).isInstanceOf(Hit.class);
    }

    @Test
    @DisplayName("두 장의 카드를 뽑은 후 카드의 합이 21일 때 상태는 블랙잭이다")
    void blackjack() {
        final State hit = new Hit(new Hands(new ArrayList<>(List.of(new Card(Rank.ACE, Suit.CLUBS)))));

        final State newState = hit.draw(new Card(Rank.TEN, Suit.CLUBS));

        assertThat(newState).isInstanceOf(Blackjack.class);
    }

    @Test
    @DisplayName("카드를 뽑은 후 카드의 합이 21을 초과할 때 상태는 버스트다")
    void bust() {
        final State hit = new Hit(new Hands(new ArrayList<>(List.of(new Card(Rank.ACE, Suit.CLUBS)))));

        hit.draw(new Card(Rank.TEN, Suit.CLUBS));
        final State newState = hit.draw(new Card(Rank.TEN, Suit.CLUBS));

        assertThat(newState).isInstanceOf(Bust.class);
    }

}
