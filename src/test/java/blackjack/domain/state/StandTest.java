package blackjack.domain.state;

import static blackjack.domain.card.Denomination.A;
import static blackjack.domain.card.Denomination.KING;
import static blackjack.domain.card.Denomination.NINE;
import static blackjack.domain.card.Denomination.QUEEN;
import static blackjack.domain.card.Denomination.TWO;
import static blackjack.domain.card.Suit.SPADES;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StandTest {

    private Cards kingQueenCards;

    @BeforeEach
    void setup() {
        kingQueenCards = new Cards(List.of(Card.of(SPADES, KING), Card.of(SPADES, QUEEN)));
    }

    @Test
    void stand상태에서_hit하는_경우_예외발생() {
        final Stand stand = new Stand(kingQueenCards, kingQueenCards.score());

        assertThatThrownBy(() -> stand.hit(Card.of(SPADES, A)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Finish상태는 hit할 수 없습니다.");
    }

    @Test
    void stand상태에서_stay하는_경우_예외발생() {
        final Stand stand = new Stand(kingQueenCards, kingQueenCards.score());

        assertThatThrownBy(() -> stand.stay())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Finish상태는 stay할 수 없습니다.");
    }

    @Test
    void 게임_종료여부_반환() {
        final Stand stand = new Stand(kingQueenCards, kingQueenCards.score());

        assertThat(stand.isFinished()).isTrue();
    }

    @Test
    void 상대가_버스트면_수익률이_1() {
        final Stand stand = new Stand(kingQueenCards, kingQueenCards.score());

        final Cards bustCards = new Cards(List.of(Card.of(SPADES, KING), Card.of(SPADES, QUEEN), Card.of(SPADES, TWO)));
        final Bust bust = new Bust(bustCards, bustCards.maxScore());

        assertThat(stand.earningRate(bust)).isEqualTo(1);
    }

    @Test
    void 상대가_블랙잭이면_수익률이_마이너스1() {
        final Stand stand = new Stand(kingQueenCards, kingQueenCards.score());

        final Cards bustCards = new Cards(List.of(Card.of(SPADES, KING), Card.of(SPADES, A)));
        final Blackjack bust = new Blackjack(bustCards);

        assertThat(stand.earningRate(bust)).isEqualTo(-1);
    }

    @Test
    void 상대와_스코어가_같으면_수익률이_0() {
        final Stand stand = new Stand(kingQueenCards, kingQueenCards.score());
        final Stand compareStand = new Stand(kingQueenCards, kingQueenCards.score());

        assertThat(stand.earningRate(compareStand)).isEqualTo(0);
    }

    @Test
    void 상대보다_스코어가_크면_수익률이_1() {
        final Stand stand = new Stand(kingQueenCards, kingQueenCards.score());

        final Cards standCards = new Cards(List.of(Card.of(SPADES, KING), Card.of(SPADES, NINE)));
        final Stand compareStand = new Stand(standCards, standCards.score());

        assertThat(stand.earningRate(compareStand)).isEqualTo(1);
    }

    @Test
    void 상대보다_스코어가_작으면_수익률이_마이너스1() {
        final Stand stand = new Stand(kingQueenCards, kingQueenCards.score());

        final Cards standCards = new Cards(List.of(Card.of(SPADES, KING), Card.of(SPADES, QUEEN), Card.of(SPADES, A)));
        final Stand compareStand = new Stand(standCards, standCards.score());

        assertThat(stand.earningRate(compareStand)).isEqualTo(-1);
    }
}
