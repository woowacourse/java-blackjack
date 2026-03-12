package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.participant.Participant;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ParticipantTest {

    @Nested
    class AddCardTest {

        @Nested
        class Success {

            @Test
            void 카드를_추가하면_손패에_저장되어야_한다() {

                // given
                Participant participant = new Participant();
                Card card = new Card(Rank.ACE, Suit.HEART);

                // when
                participant.addCard(card);

                // then
                assertThat(participant.getHand())
                        .hasSize(1)
                        .containsExactly(card);
            }
        }
    }

    @Nested
    class GetHandTest {

        @Nested
        class Success {

            @Test
            void 손패가_비어있으면_빈_리스트를_반환해야_한다() {

                // given
                Participant participant = new Participant();

                // when
                var actual = participant.getHand();

                // then
                assertThat(actual).isEmpty();
            }

            @Test
            void 손패의_카드_이름을_추가한_순서대로_반환해야_한다() {

                // given
                Participant participant = new Participant();
                Card firstCard = new Card(Rank.FIVE, Suit.HEART);
                Card secondCard = new Card(Rank.J, Suit.SPADE);
                participant.addCard(firstCard);
                participant.addCard(secondCard);

                // when
                var actual = participant.getHand();

                // then
                assertThat(actual)
                        .hasSize(2)
                        .containsExactly(firstCard, secondCard);
            }
        }
    }

    @Nested
    class CalculateScoreTest {

        @Nested
        class Success {

            @Test
            void 현재_손패_점수를_계산해_반환해야_한다() {

                // given
                Participant participant = new Participant();
                participant.addCard(new Card(Rank.TEN, Suit.HEART));
                participant.addCard(new Card(Rank.THREE, Suit.SPADE));

                // when
                int actual = participant.calculateScore();

                // then
                assertThat(actual).isEqualTo(13);
            }
        }
    }

    @Nested
    class IsBustTest {

        @Nested
        class Success {

            @Test
            void 점수가_21을_초과하면_true를_반환해야_한다() {

                // given
                Participant participant = new Participant();
                participant.addCard(new Card(Rank.TEN, Suit.HEART));
                participant.addCard(new Card(Rank.K, Suit.SPADE));
                participant.addCard(new Card(Rank.TWO, Suit.CLOVER));

                // when
                boolean actual = participant.isBust();

                // then
                assertThat(actual).isTrue();
            }

            @Test
            void 점수가_21_이하면_false를_반환해야_한다() {

                // given
                Participant participant = new Participant();
                participant.addCard(new Card(Rank.TEN, Suit.HEART));
                participant.addCard(new Card(Rank.ACE, Suit.SPADE));

                // when
                boolean actual = participant.isBust();

                // then
                assertThat(actual).isFalse();
            }
        }
    }
}
