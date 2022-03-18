package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.game.PlayingCards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class BlackjackTest {

    @DisplayName("draw 를 실행하여 예외가 발생하는 것을 확인한다.")
    @Test
    void draw() {
        PlayingCards playingCards = new PlayingCards();
        playingCards.add(List.of(Card.of(Denomination.ACE, Suit.SPADE), Card.of(Denomination.ACE, Suit.SPADE)));
        Blackjack blackjack = new Blackjack(playingCards);

        assertThatThrownBy(() -> blackjack.draw(Card.of(Denomination.KING, Suit.SPADE)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Blackjack 상태일 때는 draw 를 실행할 수 없습니다.");
    }

}