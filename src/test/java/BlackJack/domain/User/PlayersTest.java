package BlackJack.domain.User;

import BlackJack.domain.Card.CardFactory;
import BlackJack.domain.utils.FixedCardFactory;
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
        Players players = Players.create(input, new CardFactory());
        assertThat(players.size()).isEqualTo(input.size());
    }

    @Test
    @DisplayName("플레이어의 승패에 맞게 통계내는지 테스트 - 무승부")
    void getStatisticsTest() {
        CardFactory cardFactory = new FixedCardFactory();
        List<String> inputPlayerNames = List.of("giron", "tester");
        Players players = Players.create(inputPlayerNames, cardFactory);
        Dealer dealer = new Dealer(cardFactory);

        Map<String, String> statistics = players.getStatistics(dealer);
        assertAll(
                () -> assertThat(statistics.get("giron")).isEqualTo("무"),
                () -> assertThat(statistics.get("tester")).isEqualTo("무")
        );
    }
}
