package domain.participant;

import domain.CardsFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ParticipantTest {

    @Nested
    @DisplayName("버스트 처리 테스트")
    class BustTest {
        @Test
        @DisplayName("버스트 발생 안하는 경우")
        void test1() {
            // given
            Player player = new Player("moru");

            // when
            CardsFactory cardsFactory = new CardsFactory();
            player.addCard(cardsFactory.createScore19Cards1());

            // then
            assertThat(player.resolveBust()).isTrue();
        }

        @Test
        @DisplayName("ACE 없이 버스트 발생")
        void test2() {
            // given
            Player player = new Player("moru");

            // when
            CardsFactory cardsFactory = new CardsFactory();
            player.addCard(cardsFactory.createBustCardsWithNoAce());

            // then
            assertThat(player.resolveBust()).isFalse();
        }

        @Test
        @DisplayName("ACE 1장으로 버스트 해결")
        void test3() {
            // given
            Player player = new Player("moru");

            // when
            CardsFactory cardsFactory = new CardsFactory();
            player.addCard(cardsFactory.createCanResolveBustCardsWithOneAce());

            // then
            assertThat(player.resolveBust()).isTrue();
        }

        @Test
        @DisplayName("ACE 2장으로 버스트 해결")
        void test4() {
            // given
            Player player = new Player("moru");

            // when
            CardsFactory cardsFactory = new CardsFactory();
            player.addCard(cardsFactory.createCanResolveBustCardsWithTwoAce());

            // then
            assertThat(player.resolveBust()).isTrue();
        }

        @Test
        @DisplayName("ACE가 있어도 버스트 해결 불가")
        void test5() {
            // given
            Player player = new Player("moru");

            // when
            CardsFactory cardsFactory = new CardsFactory();
            player.addCard(cardsFactory.createCantResolveBustCardsWithTwoAce());

            // then
            assertThat(player.resolveBust()).isFalse();
        }
    }

}
