package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import constant.Rank;
import constant.Suit;
import exception.ErrorMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

        @Nested
        class Fail {

            @ParameterizedTest
            @ValueSource(strings = {"a", "aaa aa"})
            void 게임_참가자_이름의_길이가_2_이상_5_이하가_아니라면_예외를_발생_시켜야_한다(String name) {

                // when & then
                assertThatThrownBy(() -> new Player(name))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorMessage.PLAYER_NAME_LENGTH_OUT_OF_RANGE.getMessage());
            }

            @Test
            void 입력값에_공백만_입력되면_예외를_발생_시켜야_한다() {

                // given
                String name = "  ";

                // when & then
                assertThatThrownBy(() -> new Player(name))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorMessage.PLAYER_NAME_BLANK.getMessage());
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
                assertThat(player.getHand().getCardNames().size()).isEqualTo(1);
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
}
