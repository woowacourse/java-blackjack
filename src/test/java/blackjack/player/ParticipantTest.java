package blackjack.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.card.Card;
import blackjack.card.Number;
import blackjack.card.Shape;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantTest {

    @Test
    @DisplayName("플레이어는 21점 미만이면 추가 드로우가 가능하다.")
    void ableToDrawTest() {
        // given
        List<Card> cards = List.of(
                Card.of(Shape.HEART, Number.JACK),
                Card.of(Shape.DIAMOND, Number.TEN)
        );
        Hand hand = new Hand(cards);
        Participant Participant = new Participant("aru", hand);
        // when
        boolean isDrawable = Participant.hasDrawableScore();
        // then
        assertThat(isDrawable).isTrue();
    }

    @Test
    @DisplayName("플레이어는 21점 이상이면 추가 드로우가 불가능하다.")
    void unableToDrawTest() {
        // given
        List<Card> cards = List.of(
                Card.of(Shape.HEART, Number.JACK),
                Card.of(Shape.DIAMOND, Number.ACE)
        );
        Hand hand = new Hand(cards);
        Participant Participant = new Participant("atto", hand);
        // when
        boolean isDrawable = Participant.hasDrawableScore();
        // then
        assertThat(isDrawable).isFalse();
    }
}
