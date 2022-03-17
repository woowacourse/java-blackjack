package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.User;
import blackjack.domain.player.Users;
import blackjack.domain.result.GameResult;
import blackjack.domain.result.Result;
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
        final Dealer dealer = new Dealer(createFirstReceivedCard(CardNumber.KING, CardNumber.FIVE));
        user1 = new User("pobi", createFirstReceivedCard(CardNumber.TWO, CardNumber.JACK));
        user2 = new User("jun", createFirstReceivedCard(CardNumber.KING, CardNumber.JACK));
        final Users users = new Users(List.of(user1, user2));

        gameResult = GameResult.createPlayerGameResult(dealer, users);
    }

    private List<Card> createFirstReceivedCard(final CardNumber firstCardNumber, final CardNumber secondCardNumber) {
        return List.of(new Card(CardPattern.DIAMOND, firstCardNumber), new Card(CardPattern.HEART, secondCardNumber));
    }

    @Test
    @DisplayName("유저 게임결과를 확인한다.")
    void checkUserGameResult() {
        final Map<User, Result> expected = new HashMap<>(Map.ofEntries(
                Map.entry(user1, Result.LOSE),
                Map.entry(user2, Result.WIN)
        ));

        final Map<User, Result> actual = gameResult.getUserResult();
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
