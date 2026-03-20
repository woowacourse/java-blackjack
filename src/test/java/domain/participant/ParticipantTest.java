package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import constant.Rank;
import constant.Suit;
import domain.card.Card;
import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ParticipantTest {

    @Nested
    class AddCardsTest {

        @Nested
        class Success {

            @Test
            void 카드를_여러장_추가하면_손패에_순서대로_저장되어야_한다() {

                // given
                Participant participant = new TestParticipant(new Name("jacob"));
                Card firstCard = new Card(Rank.ACE, Suit.HEART);
                Card secondCard = new Card(Rank.K, Suit.SPADE);

                // when
                participant.addCard(List.of(firstCard, secondCard));

                // then
                assertThat(participant.getHand().getCardNames())
                    .hasSize(2)
                    .containsExactly(firstCard.getName(), secondCard.getName());
            }

            @Test
            void 빈_카드_목록을_추가하면_손패가_변하지_않아야_한다() {

                // given
                Participant participant = new TestParticipant(new Name("jacob"));
                participant.addCard(List.of(new Card(Rank.TEN, Suit.HEART)));

                // when
                participant.addCard(List.of());

                // then
                assertThat(participant.getHand().getCardNames()).hasSize(1);
            }
        }
    }
}
