package object.participant;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    void 참가자_생성_테스트() {
        //given
        String nickname = "우가";
        Player player = Player.from(nickname);

        //when & then
        Assertions.assertThat(player).isInstanceOf(Player.class);
    }
}
