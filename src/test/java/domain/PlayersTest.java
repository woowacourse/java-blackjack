package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {
    Player bustPlayer = Player.of(Name.from("pobi"), new Hand(new ArrayList<>(List.of(
            Card.of(CardNumber.J, CardShape.CLOVER),
            Card.of(CardNumber.K, CardShape.HEART),
            Card.of(CardNumber.Q, CardShape.DIAMOND)))));

    Player normalPlayer = Player.of(Name.from("jason"), new Hand(new ArrayList<>(List.of(
            Card.of(CardNumber.J, CardShape.CLOVER),
            Card.of(CardNumber.Q, CardShape.DIAMOND)))));

    Players allBustPlayers = Players.from(List.of(bustPlayer, bustPlayer, bustPlayer));
    Players containBustPlayers = Players.from(List.of(bustPlayer, normalPlayer, normalPlayer));
    Players allNormalPlayers = Players.from(List.of(normalPlayer, normalPlayer, normalPlayer));

    @Test
    @DisplayName("모든 플레이어들의 버스트 여부 확인 테스트")
    void 모든_플레이어_버스트_여부_확인() {
        assertThat(allBustPlayers.isAllBust()).isTrue();
        assertThat(containBustPlayers.isAllBust()).isFalse();
        assertThat(allNormalPlayers.isAllBust()).isFalse();
    }
}