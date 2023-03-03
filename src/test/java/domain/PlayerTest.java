package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Nested
    class 히트가능여부판단 {
        @Test
        void should_히트가능하다_when_카드가추가되었는데점수가21보다작을때() {
            //given
            Player player = new Player("에밀");
            player.receiveCard(new Card(Suit.SPADE, Number.ACE));
            player.receiveCard(new Card(Suit.CLUB, Number.ACE));

            //when
            boolean drawable = player.isDrawable();

            //then
            assertThat(drawable).isTrue();
        }
        @Test
        void should_히트불가능하다_when_카드가추가되었는데점수가21이상일떄() {
            //given
            Player player = new Player("에밀");
            player.receiveCard(new Card(Suit.SPADE, Number.ACE));
            player.receiveCard(new Card(Suit.CLUB, Number.JACK));

            //when
            boolean drawable = player.isDrawable();

            //then
            assertThat(drawable).isFalse();
        }
        @Test
        void should_히트불가능하다_when_점수가21보다작은데사용자가STAND를선택했을때() {
            //given
            Player player = new Player("에밀");
            player.receiveCard(new Card(Suit.SPADE, Number.ACE));
            player.receiveCard(new Card(Suit.CLUB, Number.ACE));
            player.stand();

            //when
            boolean drawable = player.isDrawable();

            //then
            assertThat(drawable).isFalse();
        }
    }

}