package view;

import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ParserTest {

    @DisplayName("쉼표로 구분된 이름을 입력하면 리스트로 바뀐다.")
    @Test
    void parserStringToListTest() {
        
        //given
        String names = "레몬, 머랭, 모코, 도기, 대니";

        //when
        List<String> nameList = Parser.parserStringToList(names);

        //then
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(nameList).hasSize(5);
            softAssertions.assertThat(nameList.getFirst()).isEqualTo("레몬");
            softAssertions.assertThat(nameList.getLast()).isEqualTo("대니");

        });
    }
}
