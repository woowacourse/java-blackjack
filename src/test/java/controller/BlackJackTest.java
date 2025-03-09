package controller;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Ace;
import domain.card.Card;
import domain.participant.Dealer;
import domain.card.Denomination;
import domain.participant.Player;
import domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BlackJackTest {
    BlackJack blackjack = new BlackJack();

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
            assertThat(blackjack.resolveBust(moru)).isTrue();
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
            assertThat(blackjack.resolveBust(moru)).isFalse();
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
            assertThat(blackjack.resolveBust(moru)).isTrue();
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
            assertThat(blackjack.resolveBust(moru)).isTrue();
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
            assertThat(blackjack.resolveBust(moru)).isFalse();
        }
    }
}
