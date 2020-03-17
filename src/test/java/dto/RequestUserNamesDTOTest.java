package dto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RequestUserNamesDTOTest {
    @DisplayName("플레이어의 이름을 , 를 기준으로 나눠서 반환하는지 테스트")
    @Test
    void getPlayerNameTest() {
        RequestPlayerInformationDTO requestPlayerNameDTO
                = new RequestPlayerInformationDTO("pobi", 1000);

        Assertions.assertThat(requestPlayerNameDTO.getPlayerName()).isEqualTo("pobi");
        Assertions.assertThat(requestPlayerNameDTO.getMoney()).isEqualTo(1000);
    }
}
