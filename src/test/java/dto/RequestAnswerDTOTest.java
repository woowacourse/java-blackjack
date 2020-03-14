package dto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RequestAnswerDTOTest {
    @DisplayName("대답을 담는 DTO 에 값이 제대로 설정되는지 테스트")
    @ParameterizedTest
    @CsvSource(value = {"y,y", "n,n"}, delimiter = ',')
    void requestAnswerDTOTest(String input, String expected) {
        RequestAnswerDTO requestAnswerDTO = new RequestAnswerDTO(input);

        Assertions.assertThat(requestAnswerDTO.getAnswer()).isEqualTo(expected);
    }
}
