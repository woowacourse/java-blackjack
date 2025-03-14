package model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    @DisplayName("Player 는 핸드의 총합값을 반환할 수 있다")
    void test1() {
        Player player = new Player("모루");
        player.addCard(new Card(Denomination.TWO, Suit.CLUB));
        player.addCard(new Card(Denomination.FOUR, Suit.CLUB));
        player.addCard(new Card(Denomination.FIVE, Suit.DIAMOND));

        assertThat(player.getHandTotal()).isEqualTo(11);
    }

    @Nested
    @DisplayName("버스트 처리 테스트")
    class BustTest {
        @Test
        @DisplayName("버스트 발생 안하는 경우")
        void test1() {
            // given
            Player moru = new Player("moru");

            // when
            moru.addCard(new Card(Denomination.TWO, Suit.CLUB));
            moru.addCard(new Card(Denomination.EIGHT, Suit.CLUB));

            // then
            assertThat(moru.resolveBust()).isTrue();
        }

        @Test
        @DisplayName("ACE 없이 버스트 발생")
        void test2() {
            // given
            Player moru = new Player("moru");

            // when
            moru.addCard(new Card(Denomination.TWO, Suit.CLUB));
            moru.addCard(new Card(Denomination.TEN, Suit.DIAMOND));
            moru.addCard(new Card(Denomination.TEN, Suit.CLUB));

            // then
            assertThat(moru.resolveBust()).isFalse();
        }

        @Test
        @DisplayName("ACE 1장으로 버스트 해결")
        void test3() {
            // given
            Player moru = new Player("moru");

            // when
            moru.addCard(new Card(Denomination.TEN, Suit.CLUB));
            moru.addCard(new Card(Denomination.TWO, Suit.CLUB));
            moru.addCard(new Ace(Suit.CLUB));

            // then
            assertThat(moru.resolveBust()).isTrue();
        }

        @Test
        @DisplayName("ACE 2장으로 버스트 해결")
        void test4() {
            // given
            Player moru = new Player("moru");

            // when
            moru.addCard(new Card(Denomination.TEN, Suit.CLUB));
            moru.addCard(new Ace(Suit.DIAMOND));
            moru.addCard(new Ace(Suit.CLUB));

            // then
            assertThat(moru.resolveBust()).isTrue();
        }

        @Test
        @DisplayName("ACE가 있어도 버스트 해결 불가")
        void test5() {
            // given
            Player moru = new Player("moru");

            // when
            moru.addCard(new Card(Denomination.TEN, Suit.CLUB));
            moru.addCard(new Card(Denomination.TEN, Suit.DIAMOND));
            moru.addCard(new Ace(Suit.DIAMOND));
            moru.addCard(new Ace(Suit.CLUB));

            // then
            assertThat(moru.resolveBust()).isFalse();
        }
    }
}
