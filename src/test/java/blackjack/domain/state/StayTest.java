package blackjack.domain.state;

import blackjack.domain.bet.Betting;
import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.game.PlayingCards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class StayTest {

    private static final PlayingCards playingCards = new PlayingCards();
    private static final Betting betting = new Betting(1000);

    @DisplayName("draw 를 실행하여 예외가 발생하는 것을 확인한다.")
    @Test
    void draw() {
        Stay stay = new Stay(playingCards, betting);

        assertThatThrownBy(() -> stay.draw(Card.of(Denomination.KING, Suit.SPADE)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Stay 상태일 때는 draw 를 실행할 수 없습니다.");
    }

    @DisplayName("stay 를 실행하여 예외가 발생하는 것을 확인한다.")
    @Test
    void stay() {
        Stay stay = new Stay(playingCards, betting);

        assertThatThrownBy(stay::stay)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Stay 상태일 때는 stay 를 실행할 수 없습니다.");
    }

    // TODO: 딜러와 비교하여 값 변경
    @DisplayName("Stay 일 경우 베팅 금액만큼 얻는 것을 확인한다.")
    @Test
    void profit() {
        Stay stay = new Stay(playingCards, betting);

        assertThat(stay.profit()).isEqualTo(1000.0);
    }
}