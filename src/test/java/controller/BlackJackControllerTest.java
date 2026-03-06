package controller;

import static org.junit.jupiter.api.Assertions.*;

import domain.player.Dealer;
import domain.player.Participants;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackControllerTest {
    @Test
    @DisplayName("딜러와 참가자 초기_카드 나눠주기 기능 테스트")
    void 딜러_참가자_초기_카드_나눠주기_기능_테스트() {
        // given
        Participants participants = new Participants(List.of("pobi", "jason"), 1);
        // when
        // then
    }
}