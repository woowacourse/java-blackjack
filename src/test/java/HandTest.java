import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class HandTest {
    List<Integer> list = new ArrayList<>();

    @BeforeEach
    void setUp() {
        list.clear();
    }

    @Test
    @DisplayName("카드의 합이 21을 넘는 경우 버스트")
    void isBurst() {
        // given
        list.add(10);
        list.add(13);
        Hand hand = new Hand(list);

        // when
        boolean burst = hand.isBurst();

        // then
        assertThat(burst).isEqualTo(true);
    }

    @Test
    @DisplayName("카드의 합이 21을 넘지 않는 경우 버스트하지 않는다.")
    void shouldNotBurstWhenSumIsTwentyOne() {
        // given
        list.add(10);
        list.add(10);
        Hand hand = new Hand(list);

        // when
        boolean burst = hand.isBurst();

        // then
        assertThat(burst).isEqualTo(false);
    }

    @Test
    @DisplayName("카드가 1일 때 ACE로 처리한다.")
    void shouldOneIsAce() {
        // given
        list.add(1);
        Hand hand = new Hand(list);

        // when
        int total = hand.getTotal();

        // then
        assertThat(total).isEqualTo(11);
    }

    @Test
    @DisplayName("21일 때 버스트되지 않는다.")
    void shouldNotBurstWhenCardsAreTwentyOne() {
        // given
        list.add(10);
        list.add(1);
        Hand hand = new Hand(list);

        // when
        boolean burst = hand.isBurst();

        // then
        assertThat(burst).isEqualTo(false);
    }

    @Test
    @DisplayName("ACE가 존재하고 합계가 21을 초과할 때 ACE를 1로 취급한다.")
    void shouldAceIsOneWhenBurst() {
        // given
        list.add(10);
        list.add(5);
        list.add(1);
        Hand hand = new Hand(list);

        // when
        boolean burst = hand.isBurst();
        int total = hand.getTotal();

        // then
        assertThat(burst).isEqualTo(false);
        assertThat(total).isEqualTo(16);
    }

    @Test
    @DisplayName("적절한 카드가 아닌 경우 예외가 발생한다.")
    void throwExceptionWhenNotProperCardNumber() {
        // given
        list.add(-1);

        // when & then
        assertThatThrownBy(() -> new Hand(list))
                .isInstanceOf(IllegalArgumentException.class);
    }

    /*
        [관찰]
        테스트를 작성하기 어려운 코드가 있는가?
        어렵다면, 무엇 때문에 어려운가?
        테스트를 쉽게 하려면 코드를 어떻게 바꾸고 싶은가?

        [기록]
        테스트가 어려웠던 코드 1곳
        - Hand클래스의 getTotal()에 ACE의 1/11 선택 규칙이 들어있고,
        입력이 List<Integer>라 테스트가 원시타입 숫자 규칙에 종속됨.

        바꾸고 싶은 부분과 이유
        - 현재 ACE를 1로 표현하고 있으나 ACE, TWO 같이 RANK(enum)을 두고싶음.
            왜냐하면 등급과 명칭을 명확히 매핑하고 관리하면 관리하기에 좋다고 생각함.
        - 카드의 의미와 유효 범위가 Hand에 강하게 종속되어있음. 따라서 도메인 타입을 도입 (Card/Rank etc...)
     */

    /*
    테스트 단위에 대한 내 의견
    - 테스트 단위는 구현의 레벨이 아닌 행위의 영역이어야 하지 않을까?

    테스트 해야하는 것과 하지 않을 것.
    - 도메인 규칙에 대해서는 테스트를 진행해야 함.
    - 도메인 규칙을 이행하는 세부 구현사항은 테스트하지 않아도 됨.
     */
}