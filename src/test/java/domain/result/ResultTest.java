package domain.result;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultTest {

    @Test
    @DisplayName("블랙잭 게임 결과에 따른 최종 금액 테스트")
    void calculateResultMoneyTest(){
        assertThat(Result.DRAW.calculateResultMoney(1000,false)).isEqualTo(0);
        assertThat(Result.LOSE.calculateResultMoney(1000,false)).isEqualTo(-1000);
        assertThat(Result.WIN.calculateResultMoney(1000,false)).isEqualTo(1000);
        assertThat(Result.WIN.calculateResultMoney(1000,true)).isEqualTo(1500);
    }
}
