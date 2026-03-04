import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class ParticipantTest {

    @Test
    void 참여자는_받은_카드를_자신의_카드_패에_추가한다() {
        //given
        Participant participant = Participant.from("pobi");
        Card card = Card.of("스페이드", 1);

        //when
        List<Card> drawn = participant.draw(card);

        //then
        assertThat(drawn.getLast()).isEqualTo(card);
    }

    @Nested
    class 참여자가_가진_패를_오픈한다 {
        @Test
        void 딜러가_처음_오픈할_때는_첫_번째_카드만_오픈한다() {
            // given
            Participant participant = Participant.from("dealer", true);
            Card firstCard = Card.of("스페이드", 1);
            Card secondCard = Card.of("하트", 2);
            participant.draw(firstCard);
            participant.draw(secondCard);

            // when
            List<Card> opened = participant.open(1);

            // then
            assertThat(opened.getFirst()).isEqualTo(firstCard);
        }

        @Test
        void 딜러가_처음_오픈하는게_아니라면_모든_카드를_오픈한다() {
            // given
            Participant participant = Participant.from("dealer", true);
            Card firstCard = Card.of("스페이드", 1);
            Card secondCard = Card.of("하트", 2);
            participant.draw(firstCard);
            participant.draw(secondCard);

            // when
            List<Card> opened = participant.open(2);

            // then
            assertThat(opened).hasSize(2);
        }

        @Test
        void 플레이어는_항상_모든_카드를_오픈한다() {
            // given
            Participant participant = Participant.from("player");
            Card firstCard = Card.of("스페이드", 1);
            Card secondCard = Card.of("하트", 2);
            participant.draw(firstCard);
            participant.draw(secondCard);

            // when
            List<Card> opened = participant.open(1);

            // then
            assertThat(opened).hasSize(2);
        }
    }
}
