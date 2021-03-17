package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HitTest {
    @DisplayName("Hit인 상태에서 draw하였을 때 Bust인 경우")
    @Test
    void hit_draw_test() {
        Hit hit = new Hit(new Cards(Card.of("하트", "7"),
                Card.of("스페이드", "7")));
        assertThat(hit.draw(Card.of("클로버", "8"))).isInstanceOf(Bust.class);
    }

    @DisplayName("Hit인 상태에서 draw하였을 때 hit인 경우")
    @Test
    void hit_draw_test2() {
        Hit hit = new Hit(new Cards(Card.of("하트", "7"),
                Card.of("스페이드", "7")));
        assertThat(hit.draw(Card.of("클로버", "6"))).isInstanceOf(Hit.class);
    }

    @DisplayName("Hit인 상태에서 stay하는 경우 정상 처리 된다.")
    @Test
    void hit_draw_test3() {
        Hit hit = new Hit(new Cards(Card.of("하트", "7"),
                Card.of("스페이드", "7")));
        assertThat(hit.stay()).isInstanceOf(Stay.class);
    }
}