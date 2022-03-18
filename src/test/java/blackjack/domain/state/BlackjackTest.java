package blackjack.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackTest {
    @Test
    @DisplayName("처음 카드 두 장을 뽑고 블랙잭이면 블랙잭 상태가 되는지 확인한다.")
    void blackjack() {
        State state = Ready.start(new Card(Denomination.ACE, Suit.CLUBS), new Card(Denomination.JACK, Suit.CLUBS));
        assertThat(state).isInstanceOf(Blackjack.class);
    }
}