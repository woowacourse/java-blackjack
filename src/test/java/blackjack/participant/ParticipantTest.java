package blackjack.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.card.Card;
import blackjack.card.Number;
import blackjack.card.Shape;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantTest {

    @Test
    @DisplayName("플레이어는 21점 미만이면 추가 드로우가 가능하다.")
    void ableToDrawTest() {
        // given
        List<Card> cards = List.of(
                new Card(Shape.HEART, Number.JACK),
                new Card(Shape.DIAMOND, Number.TEN)
        );
        Participant participant = new Participant("aru");
        Iterator<Card> cardIterator = cards.iterator();
        participant.drawCards(cardIterator::next, 2);
        // when
        boolean isDrawable = participant.isDrawable();
        // then
        assertThat(isDrawable).isTrue();
    }

    @Test
    @DisplayName("플레이어는 21점 이상이면 추가 드로우가 불가능하다.")
    void unableToDrawTest() {
        // given
        List<Card> cards = List.of(
                new Card(Shape.HEART, Number.JACK),
                new Card(Shape.DIAMOND, Number.ACE)
        );
        Iterator<Card> cardIterator = cards.iterator();
        Participant participant = new Participant("atto");
        participant.drawCards(cardIterator::next, 2);
        // when
        boolean isDrawable = participant.isDrawable();
        // then
        assertThat(isDrawable).isFalse();
    }

    @Test
    @DisplayName("카드가 없을 때, 첫 번째 카드를 확인하면 예외를 발생시킨다.")
    void emptyHandGetFirstCardTest() {
        // given
        Participant participant = new Participant("aru");
        // when, then
        assertThatThrownBy(participant::getFirstCard)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 카드를 가지고있지 않습니다.");
    }
}
