package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Nested
    class ConstructorTest {

        @Nested
        class Success {

            @Test
            void 게임_참가자_조건이_맞으면_성공_이다() {

                // given
                String name = "jacob";

                // when
                Player player = new Player(name);
                String actual = player.getName();

                // then
                String expected = "jacob";
                Assertions.assertEquals(expected, actual);
            }
        }
    }

    @Nested
    class AddCardTest {

        @Nested
        class Success {

            @Test
            void 카드를_추가하면_손패에_카드가_저장되어야_한다() {

                // given
                Player player = new Player("jacob");
                Card card = new Card(Rank.ACE, Suit.HEART);

                // when
                player.addCard(card);

                // then
                assertThat(player.getHand().size()).isEqualTo(1);
            }
        }
    }

    @Nested
    class IsBustTest {

        @Nested
        class Success {

            @Test
            void 플레이어의_점수가_21_초과이면_버스트이다() {

                // given
                Player player = new Player("jacob");
                Card card1 = new Card(Rank.TEN, Suit.HEART);
                Card card2 = new Card(Rank.TEN, Suit.HEART);
                Card card3 = new Card(Rank.TEN, Suit.HEART);

                // when
                player.addCard(card1);
                player.addCard(card2);
                player.addCard(card3);

                // then
                assertThat(player.isBust()).isTrue();
            }

            @Test
            void 플레이어의_점수가_21_이하이면_버스트가_아니다() {

                // given
                Player player = new Player("jacob");
                Card card1 = new Card(Rank.TEN, Suit.HEART);
                Card card2 = new Card(Rank.ACE, Suit.HEART);

                // when
                player.addCard(card1);
                player.addCard(card2);

                // then
                assertThat(player.isBust()).isFalse();
            }
        }
    }

    @Nested
    class CalculateScoreTest {

        @Nested
        class Success {

            @Test
            void 플레이어의_현재_점수를_반환해야_한다() {

                // given
                Player player = new Player("jacob");
                player.addCard(new Card(Rank.TEN, Suit.HEART));
                player.addCard(new Card(Rank.THREE, Suit.SPADE));

                // when
                int actual = player.calculateScore();

                // then
                assertThat(actual).isEqualTo(13);
            }
        }
    }
}
