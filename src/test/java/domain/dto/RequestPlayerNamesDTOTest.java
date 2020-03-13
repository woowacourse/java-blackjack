package domain.dto;

import dto.RequestPlayerNamesDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class RequestPlayerNamesDTOTest {
    @DisplayName("플레이어의 이름을 , 를 기준으로 나눠서 반환하는지 테스트")
    @Test
    void getPlayerNameTest() {
        String playerName = "pobi,json";
        RequestPlayerNamesDTO requestPlayerNameDTO = new RequestPlayerNamesDTO(playerName);

        Assertions.assertThat(requestPlayerNameDTO.getPlayerName()).containsSequence("pobi", "json");
    }

    @DisplayName("잘못된 이름을 입력했을 때 예외 처리 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"pobi,,json", "pobi,pobi"})
    void getPlayerNameWithInvalidNameTest(String input) {
        Assertions.assertThatThrownBy(() -> {
            new RequestPlayerNamesDTO(input);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
