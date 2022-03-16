package blackjack.domain.state;

import static blackjack.domain.card.Denomination.A;
import static blackjack.domain.card.Denomination.KING;
import static blackjack.domain.card.Denomination.QUEEN;
import static blackjack.domain.card.Denomination.TWO;
import static blackjack.domain.card.Suit.SPADES;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.Set;
import org.junit.jupiter.api.Test;

class StandTest {

    @Test
    void stand상태에서_hit하는_경우_예외발생() {
        final Cards cards = new Cards(Set.of(Card.of(SPADES, KING), Card.of(SPADES, QUEEN)));
        final Stand stand = new Stand(cards);

        assertThatThrownBy(() -> stand.hit(Card.of(SPADES, A)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Finish상태는 hit할 수 없습니다.");
    }

    @Test
    void stand상태에서_stay하는_경우_예외발생() {
        final Cards cards = new Cards(Set.of(Card.of(SPADES, KING), Card.of(SPADES, QUEEN)));
        final Stand stand = new Stand(cards);

        assertThatThrownBy(() -> stand.stay())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Finish상태는 stay할 수 없습니다.");
    }

    @Test
    void 게임_종료여부_반환() {
        final Cards cards = new Cards(Set.of(Card.of(SPADES, KING), Card.of(SPADES, QUEEN)));
        final Stand stand = new Stand(cards);

        assertThat(stand.isFinished()).isTrue();
    }

    @Test
    void 상대가_버스트면_수익률이_1() {
        final Cards cards = new Cards(Set.of(Card.of(SPADES, KING), Card.of(SPADES, QUEEN)));
        final Stand stand = new Stand(cards);

        final Cards bustCards = new Cards(Set.of(Card.of(SPADES, KING), Card.of(SPADES, QUEEN), Card.of(SPADES, TWO)));
        final Bust bust = new Bust(bustCards);

        assertThat(stand.earningRate(bust)).isEqualTo(1);
    }

    @Test
    void 상대가_블랙잭이면_수익률이_0() {
        final Cards cards = new Cards(Set.of(Card.of(SPADES, KING), Card.of(SPADES, QUEEN)));
        final Stand stand = new Stand(cards);

        final Cards bustCards = new Cards(Set.of(Card.of(SPADES, KING), Card.of(SPADES, A)));
        final Blackjack bust = new Blackjack(bustCards);

        assertThat(stand.earningRate(bust)).isEqualTo(0);
    }
}
