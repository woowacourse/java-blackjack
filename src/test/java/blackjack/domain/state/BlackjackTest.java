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
import static org.junit.jupiter.api.Assertions.*;

class BlackjackTest {

    private static final PlayingCards playingCards = new PlayingCards();
    private static final Betting betting = new Betting(1000);

    @DisplayName("draw 를 실행하여 예외가 발생하는 것을 확인한다.")
    @Test
    void draw() {
        Blackjack blackjack = new Blackjack(playingCards, betting);

        assertThatThrownBy(() -> blackjack.draw(Card.of(Denomination.KING, Suit.SPADE)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Blackjack 상태일 때는 draw 를 실행할 수 없습니다.");
    }

    @DisplayName("블랙잭일 경우 베팅 금액의 1.5 배를 얻는 것을 확인한다.")
    @Test
    void profit() {
        Blackjack blackjack = new Blackjack(playingCards, betting);

        assertThat(blackjack.profit()).isEqualTo(1500.0);
    }
}