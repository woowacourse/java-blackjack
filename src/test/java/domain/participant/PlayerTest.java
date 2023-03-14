package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Number;
import domain.card.Suit;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Nested
    class 생성 {
        @Test
        void should_입력된베팅머니를가진다_when_생성됐을때() {
            //given

            //when
            Player player = new Player(new Name("이름"), new Hand(), new BettingMoney(1000));

            //then
            assertThat(player.bettingMoney()).isEqualTo(1000);
        }
    }

    @Nested
    class 히트가능여부판단 {
        @Test
        void should_히트가능하다_when_카드가추가되었는데점수가21보다작을때() {
            //given
            Player player = new Player(new Name("에밀"), new Hand(), new BettingMoney(1000));
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
            Player player = new Player(new Name("에밀"), new Hand(), new BettingMoney(1000));
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
            Player player = new Player(new Name("에밀"), new Hand(), new BettingMoney(1000));
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