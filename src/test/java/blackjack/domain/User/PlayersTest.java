package blackjack.domain.User;

import blackjack.domain.Card.Deck;
import blackjack.domain.utils.FixedCardFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class PlayersTest {

    @Test
    @DisplayName("유저가 이름을 입력한만큼 참가자들 생성되는지 테스트")
    void joinTest() {
        List<String> input = List.of("기론", "열음");
        List<Betting> bettings = List.of(Betting.from(1000), Betting.from(5000));
        Players players = Players.create(input, bettings, new Deck());
        assertThat(players.size()).isEqualTo(input.size());
    }
}
