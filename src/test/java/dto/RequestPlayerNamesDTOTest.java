package dto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class RequestPlayerNamesDTOTest {
    @DisplayName("플레이어의 이름을 , 를 기준으로 나눠서 반환하는지 테스트")
    @Test
    void getPlayerNameTest() {
        List<String> playerNames = Arrays.asList("pobi", "json");
        RequestPlayerNamesDTO requestPlayerNameDTO = new RequestPlayerNamesDTO(playerNames);

        Assertions.assertThat(requestPlayerNameDTO.getPlayerName()).containsSequence("pobi", "json");
    }
}
