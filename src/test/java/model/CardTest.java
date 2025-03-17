package model;

import static model.card.Denomination.ACE;
import static model.card.Denomination.JACK;
import static model.card.Denomination.THREE;
import static model.card.Denomination.TWO;
import static model.card.Suit.CLUB;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import model.card.Ace;
import model.card.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Nested
    @DisplayName("카드 생성 테스트")
    class CardCreateTest {
        @Test
        @DisplayName("카드는 모양과 숫자를 가진다")
        void test1() {
            assertThat(new Card(ACE, CLUB)).isInstanceOf(Card.class);
        }

        @Test
        @DisplayName("카드는 고유의 숫자값을 갖는다.")
        void test2() {
            assertThat(new Card(TWO, CLUB).getValue()).isEqualTo(2);
        }

        @Test
        @DisplayName("카드는 고유의 숫자값을 갖는다. - 2")
        void test3() {
            assertThat(new Card(THREE, CLUB).getValue()).isEqualTo(3);
        }

        @Test
        @DisplayName("카드는 고유의 숫자값을 갖는다. - 3")
        void test4() {
            assertThat(new Card(JACK, CLUB).getValue()).isEqualTo(10);
        }

        @Test
        @DisplayName("ACE의 초기 숫자값은 11이다.")
        void test5() {
            assertThat(new Card(ACE, CLUB).getValue()).isEqualTo(11);
        }
    }

    @Test
    @DisplayName("Card는 값을 0으로 변경할 수 있다")
    void test1() {
        Card card = new Card(THREE, CLUB);

        card.setValueToZero();

        assertThat(card.getValue()).isEqualTo(0);
    }

    @Nested
    @DisplayName("ACE 테스트")
    class AceTest {
        @Test
        @DisplayName("ACE는 ACE임을 알릴 수 있다")
        void test1() {
            Card clubAce = new Card(ACE, CLUB);
            Card clubTwo = new Card(TWO, CLUB);

            assertAll(() -> assertThat(clubAce.isAce()).isTrue(),
                    () -> assertThat(clubTwo.isAce()).isFalse());
        }

        @Test
        @DisplayName("ACE는 고유의 숫자값을 1로 바꿀 수 있다.")
        void test2() {
            // given
            Ace clubAce = new Ace(CLUB);

            // when
            clubAce.setValueToOne();

            // then
            assertThat(clubAce.getValue()).isEqualTo(1);
        }
    }
}
