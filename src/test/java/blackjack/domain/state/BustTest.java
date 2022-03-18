package blackjack.domain.state;

import blackjack.domain.bet.Betting;
import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.game.PlayingCards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BustTest {

    private static final PlayingCards playingCards = new PlayingCards();
    private static final Betting betting = new Betting(1000);

    @DisplayName("draw 를 실행하여 예외가 발생하는 것을 확인한다.")
    @Test
    void draw() {
        Bust bust = new Bust(playingCards, betting);

        assertThatThrownBy(() -> bust.draw(Card.of(Denomination.KING, Suit.SPADE)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Bust 상태일 때는 draw 를 실행할 수 없습니다.");
    }

    @DisplayName("버스트일 경우 베팅 금액만큼 잃는 것을 확인한다.")
    @Test
    void profit() {
        Bust bust = new Bust(playingCards, betting);

        assertThat(bust.profit()).isEqualTo(-1000.0);
    }
}
