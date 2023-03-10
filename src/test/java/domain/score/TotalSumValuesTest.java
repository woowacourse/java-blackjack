package domain.score;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TotalSumValuesTest {

    @Test
    @DisplayName("totalValues과 더할 수들을 받아서 모든 요소에 더할 수를 더한 totalValues를 반환한다. - ACE 있는 경우")
    void addValuesToAllElement1() {
        // given
        List<Integer> initValues = new ArrayList<>();
        initValues.add(0);
        TotalSumValues initTotalSumValues = new TotalSumValues(initValues);

        List<Integer> addValues = new ArrayList<>();
        addValues.add(1);
        addValues.add(8);

        // when
        TotalSumValues totalSumValues = initTotalSumValues.addValuesToAllElement(initTotalSumValues, addValues);

        // then
        Assertions.assertThat(totalSumValues.getValues()).contains(9, 19);
    }

    @Test
    @DisplayName("totalValues과 더할 수들을 받아서 모든 요소에 더할 수를 더한 totalValues를 반환한다. - ACE 없는 경우")
    void addValuesToAllElement2() {
        // given
        List<Integer> initValues = new ArrayList<>();
        initValues.add(0);
        TotalSumValues initTotalSumValues = new TotalSumValues(initValues);

        List<Integer> addValues = new ArrayList<>();
        addValues.add(10);
        addValues.add(8);

        // when
        TotalSumValues totalSumValues = initTotalSumValues.addValuesToAllElement(initTotalSumValues, addValues);

        // then
        Assertions.assertThat(totalSumValues.getValues()).contains(18);
    }

}
