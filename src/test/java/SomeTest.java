import static domain.Denomination.*;
import static domain.Suit.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.Card;
import domain.Denomination;
import domain.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class SomeTest {

    @Nested
    @DisplayName("카드 생성 테스트")
    class CardCreateTest {
        @Test
        @DisplayName("카드는 모양과 끗수를 가진다")
        public void test1() {
            assertThat(new Card(ACE, CLUB)).isInstanceOf(Card.class);
        }

        @Test
        @DisplayName("카드는 고유의 숫자값을 갖는다.")
        public void test2() {
            assertThat(new Card(TWO, CLUB)).extracting("value").isEqualTo(2);
        }

        @Test
        @DisplayName("카드는 고유의 숫자값을 갖는다. - 2")
        public void test3() {
            assertThat(new Card(THREE, CLUB)).extracting("value").isEqualTo(3);
        }

        @Test
        @DisplayName("카드는 고유의 숫자값을 갖는다. - 3")
        public void test4() {
            assertThat(new Card(JACK, CLUB)).extracting("value").isEqualTo(10);
        }
    }


    @Test
    @DisplayName("ACE는 ACE임을 알릴 수 있다")
    public void test1() {
        Card clubAce = new Card(ACE, CLUB);
        Card clubTwo = new Card(TWO, CLUB);

        assertAll(() -> assertThat(clubAce.isAce()).isTrue(),
                () -> assertThat(clubTwo.isAce()).isFalse());
    }

}
