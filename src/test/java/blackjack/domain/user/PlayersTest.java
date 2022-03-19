package blackjack.domain.user;

import blackjack.domain.card.Deck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class PlayersTest {

    @Test
    @DisplayName("유저가 이름을 입력한만큼 참가자들 생성되는지 테스트")
    void joinTest() {
        Map<String, Bet> input = new LinkedHashMap<>();
        input.put("기론", Bet.from(1000));
        input.put("열음", Bet.from(5000));

        Players players = Players.create(input, new Deck());
        assertThat(players.size()).isEqualTo(input.size());
    }
}
