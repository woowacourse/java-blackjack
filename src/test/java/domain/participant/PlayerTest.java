package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Emblem;
import domain.card.Grade;
import java.util.List;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    void 처음_받은_카드들을_노출한다() {
        // given
        Hand hand = new Hand(
                List.of(new Card(Emblem.CLOVER, Grade.TEN),
                        new Card(Emblem.DIAMOND, Grade.TEN)));
        Participant dealer = new Player("러키", hand);
        // when
        List<String> result = dealer.getInitialCards();
        // then
        assertThat(result).hasSize(2);
    }

    @Test
    void 카드의_합이_21이초과되면_버스트이다() {
        // given
        Hand hand = new Hand(
                List.of(
                        new Card(Emblem.CLOVER, Grade.TEN),
                        new Card(Emblem.CLOVER, Grade.TWO),
                        new Card(Emblem.DIAMOND, Grade.TEN)
                )
        );
        Participant participant = new Player("test", hand);
        // when
        boolean result = participant.isBust();
        // then
        assertThat(result).isTrue();
    }

    @Test
    void 카드의_합이_21이하이면_버스트가_아니다() {
        // given
        Hand hand = new Hand(
                List.of(
                        new Card(Emblem.CLOVER, Grade.TEN),
                        new Card(Emblem.DIAMOND, Grade.ACE)
                )
        );
        Participant participant = new Player("test", hand);
        // when
        boolean result = participant.isBust();

        // then
        assertThat(result).isFalse();
    }

}
