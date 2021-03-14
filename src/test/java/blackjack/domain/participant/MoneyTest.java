package blackjack.domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.assertj.core.api.AssertionsForClassTypes.*;

public class MoneyTest {
    @Test
    @DisplayName("Money를 생성하면, 정상 작동 한다")
    void initTest() {
        assertThatCode(() -> new Money(1000))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Money 객체가 가진 돈이 같다면, 같은 객체로 판별한다")
    void equalsTest() {
        assertThat(new Money(1000)).isEqualTo(new Money(1000));

        final HashSet<Money> moneySet = new HashSet<>();

        moneySet.add(new Money(2000));
        moneySet.add(new Money(2000));
        moneySet.add(new Money(2000));

        assertThat(moneySet.size()).isEqualTo(1);
    }
}
