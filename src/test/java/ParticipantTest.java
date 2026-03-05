import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import model.Card;
import model.Participant;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ParticipantTest {

    @Test
    void 참여자는_받은_카드를_자신의_카드_패에_추가한다() {
        //given
        Participant participant = Participant.of("pobi");
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
            Participant participant = Participant.of("dealer", true);
            Card firstCard = Card.of("스페이드", 1);
            Card secondCard = Card.of("하트", 2);
            participant.draw(firstCard);
            participant.draw(secondCard);

            // when
//            List<Card> opened = participant.open(1);
            List<String> opened = participant.open(true);

            // then
            assertThat(opened.getFirst()).isEqualTo("1스페이드");
        }

        @Test
        void 딜러가_처음_오픈하는게_아니라면_모든_카드를_오픈한다() {
            // given
            Participant participant = Participant.of("dealer", true);
            Card firstCard = Card.of("스페이드", 1);
            Card secondCard = Card.of("하트", 2);
            participant.draw(firstCard);
            participant.draw(secondCard);

            // when
            List<String> opened = participant.open(false);

            // then
            assertThat(opened).hasSize(2);
        }

        @Test
        void 플레이어는_항상_모든_카드를_오픈한다() {
            // given
            Participant participant = Participant.of("player");
            Card firstCard = Card.of("스페이드", 1);
            Card secondCard = Card.of("하트", 2);
            participant.draw(firstCard);
            participant.draw(secondCard);

            // when
            List<String> opened = participant.open(true);

            // then
            assertThat(opened).hasSize(2);
        }

        @Test
        void 참가자_패의_총합계_점수가_21점_초과하면_버스트로_판정한다() {
            // given
            Participant jason = Participant.of("jason", false);
            jason.draw(Card.of("스페이드", 10));
            jason.draw(Card.of("스페이드", 9));
            jason.draw(Card.of("스페이드", 8));

            // when
            // then
            assertThat(jason.isBust()).isTrue();
        }

        @ParameterizedTest
        @MethodSource("provideCard")
        void 참가자가_가진_패의_점수를_계산한다(List<Card> cards, int expected) {
            // given
            Participant jason = Participant.of("jason", false);

            // when
            for (Card card : cards) {
                jason.draw(card);
            }

            // then
            assertThat(jason.calculateScore()).isEqualTo(expected);
        }

        private static Stream<Arguments> provideCard() {
            return Stream.of(
                    Arguments.of(List.of(Card.of("스페이드", 11), Card.of("스페이드", 6)), 17),
                    Arguments.of(List.of(Card.of("스페이드", 10), Card.of("스페이드", 11)), 21),
                    Arguments.of(List.of(Card.of("스페이드", 6), Card.of("스페이드", 8), Card.of("스페이드", 11)), 15),
                    Arguments.of(List.of(Card.of("스페이드", 10), Card.of("스페이드", 10), Card.of("스페이드", 11)), 21),
                    Arguments.of(List.of(Card.of("스페이드", 11), Card.of("스페이드", 1), Card.of("스페이드", 2), Card.of("스페이드", 11)), 15),
                    Arguments.of(List.of(Card.of("스페이드", 11), Card.of("스페이드", 11), Card.of("스페이드", 11), Card.of("스페이드", 11)), 14)

            );

        }
    }
}
