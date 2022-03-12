package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Player;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackJackResultTest {
    private List<Player> gamblers;
    private Player dealer;

    @BeforeEach
    void setup() {
        gamblers = List.of(new Gambler("포비"), new Gambler("돌범"), new Gambler("리차드"));
        dealer = new Dealer();
    }

    @Test
    @DisplayName("딜러에게 겜블러들을 전달해서 승무패를 반환받는다")
    void judge_by_dealer() {
        // given &  when
        BlackJackResult blackJackResult = BlackJackResult.of(dealer, gamblers);
        final Map<GameResult, Integer> dealerResult = blackJackResult.getDealerResult();
        final List<GameResult> gameResults = Arrays.stream(GameResult.values()).collect(Collectors.toList());

        // then
        dealerResult.keySet().forEach(result -> assertThat(gameResults).contains(result));
    }
}
