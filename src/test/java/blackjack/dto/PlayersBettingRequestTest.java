package blackjack.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersBettingRequestTest {

    @Test
    @DisplayName("플레이어의 베팅 정보 dto 생성을 확인한다.")
    void makePlayerBettingRequest() {
        // given
        PlayerBettingRequest playerBettingRequestA = PlayerBettingRequest.of("boye", "1000");
        PlayerBettingRequest playerBettingRequestB = PlayerBettingRequest.of("zzang", "1000");

        // when
        PlayersBettingRequest playersBettingRequest = PlayersBettingRequest.from(List.of(
            playerBettingRequestA,
            playerBettingRequestB
        ));

        // then
        assertThat(playersBettingRequest.value())
            .extracting(PlayerBettingRequest::playerNickname, PlayerBettingRequest::amount)
            .containsExactly(
                tuple("boye", 1000L),
                tuple("zzang", 1000L)
            );
    }
}
