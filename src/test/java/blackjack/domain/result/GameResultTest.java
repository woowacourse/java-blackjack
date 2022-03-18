package blackjack.domain.result;

import static blackjack.domain.Fixtures.JACK_DIAMOND;
import static blackjack.domain.Fixtures.KING_DIAMOND;
import static blackjack.domain.Fixtures.SIX_DIAMOND;
import static blackjack.domain.Fixtures.TWO_DIAMOND;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Suit;
import blackjack.domain.card.Denomination;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.User;
import blackjack.domain.player.Users;
import blackjack.money.BettingMoney;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameResultTest {

    private GameResult gameResult;
    private User user1, user2;

    @BeforeEach
    void setUpGameResult() {
        final Dealer dealer = new Dealer(new ArrayList<>(Arrays.asList(JACK_DIAMOND, SIX_DIAMOND)));
        user1 = new User("pobi", new ArrayList<>(Arrays.asList(TWO_DIAMOND, KING_DIAMOND)));
        user2 = new User("jun", new ArrayList<>(Arrays.asList(JACK_DIAMOND, KING_DIAMOND)));
        final Users users = new Users(List.of(user1, user2));

        gameResult = GameResult.createPlayerGameResult(dealer, users);
    }

    @Test
    @DisplayName("유저 게임결과를 확인한다.")
    void checkUserGameResult() {
        final Map<String, Result> expected = new HashMap<>(Map.ofEntries(
                Map.entry("pobi", Result.LOSE),
                Map.entry("jun", Result.WIN)
        ));

        final Map<String, Result> actual = gameResult.getUserResult();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("딜러 게임결과를 확인한다.")
    void checkDealerGameResult() {
        final Map<Result, Integer> expected = new HashMap<>(Map.ofEntries(
                Map.entry(Result.LOSE, 1),
                Map.entry(Result.WIN, 1),
                Map.entry(Result.DRAW, 0)
        ));

        final Map<Result, Integer> actual = gameResult.getDealerResult();
        assertThat(actual).isEqualTo(expected);
    }
}
