package blackjack.domain.state;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
import blackjack.domain.card.Cards;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackTest {

    @Test
    @DisplayName("Blackjack 일 때 카드를 뽑으면 예외가 발생한다.")
    void blackjackDraw() {
        final Cards cards = new Cards(List.of(
                new Card(CardPattern.DIAMOND, CardNumber.JACK),
                new Card(CardPattern.DIAMOND, CardNumber.ACE)
        ));
        final State state = StateFactory.createFirstState(cards);

        assertThatThrownBy(() -> state.draw(new Card(CardPattern.HEART, CardNumber.TWO)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("더 이상 카드를 받을 수 없습니다.");
    }
}
