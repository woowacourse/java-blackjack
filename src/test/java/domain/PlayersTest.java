package domain;


import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.InputParser;

class PlayersTest {

    private final List<String> userNames = InputParser.splitByDelimiter("pobi,jason");
    private final Players players = Players.of(Cards.of(), userNames);

    @Test
    @DisplayName("Players 객체 생성 시 입력한 플레이어 수만큼 플레이어 객체 생성되어 players 리스트에 저장됨")
    void creates_players() {
        assertThat(players.getPlayers()).hasSize(2);
    }

    @Test
    @DisplayName("플레이어들의 이름이 제대로 반환되는지")
    void get_players_name_success() {
        assertThat(players.getUserNames()).isEqualTo(List.of("pobi", "jason"));
    }

    @Test
    @DisplayName("플레이어의 보유한 카드들의 종류가 올바르게 저장됐는지 테스트")
    void have_card_type_return_value_success() {
        assertThat(players.getPlayersCardsInfo()).hasSize(2);
    }

    @Test
    @DisplayName("플레이어들의 점수가 제대로 저장이 됐는지 테스트")
    void players_score_success() {
        assertThat(players.getPlayerScoreInfo()).hasSize(2);
    }

}