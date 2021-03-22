package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MoneysTest {

    @Test
    @DisplayName("Moneys 객체 생성된다")
    void createMoneys() {
        /*give*/
        List<Money> moneyList = Arrays.asList(
                Money.of("10"),
                Money.of("11"),
                Money.of("12"),
                Money.of("13")
        );

        /*when*/
        Moneys moneys = new Moneys(moneyList);

        /*then*/
        assertThat(moneys).isInstanceOf(Moneys.class);
    }

    @Test
    @DisplayName("Moneys 내부 요소를 get메서드로 가져올 수 있다.")
    void getTest() {
        /*give*/
        List<Money> moneyList = Arrays.asList(
                Money.of("10"),
                Money.of("11"),
                Money.of("12"),
                Money.of("13")
        );

        /*when*/
        Moneys moneys = new Moneys(moneyList);

        /*then*/
        assertThat(moneys.get(0)).isEqualTo(Money.of("10"));
    }

    @Test
    @DisplayName("Moneys toList 메서드로 컬렉션 필드 가져온다.")
    void toListTest() {
        /*give*/
        List<Money> moneyList = Arrays.asList(
                Money.of("10"),
                Money.of("11"),
                Money.of("12"),
                Money.of("13")
        );

        /*when*/
        Moneys moneys = new Moneys(moneyList);

        /*then*/
        assertThat(moneys.toList()).containsExactly(
                Money.of("10"),
                Money.of("11"),
                Money.of("12"),
                Money.of("13")
        );
    }
}