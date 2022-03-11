package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
import blackjack.domain.card.Cards;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerGameResultTest {

    private Dealer dealer;
    private List<User> users;

    @BeforeEach
    void setUpPlayer() {
        dealer = new Dealer(createCards(
                new Card(CardPattern.DIAMOND, CardNumber.JACK),
                new Card(CardPattern.DIAMOND, CardNumber.KING)
        ));

        users = Arrays.asList(
                new User("slow", createCards(
                        new Card(CardPattern.SPADE, CardNumber.KING),
                        new Card(CardPattern.DIAMOND, CardNumber.QUEEN),
                        new Card(CardPattern.DIAMOND, CardNumber.ACE)
                )),
                new User("jason", createCards(
                        new Card(CardPattern.SPADE, CardNumber.TEN),
                        new Card(CardPattern.HEART, CardNumber.TEN)
                )),
                new User("pobi", createCards(
                        new Card(CardPattern.CLOVER, CardNumber.TEN),
                        new Card(CardPattern.HEART, CardNumber.FIVE)
                ))
        );
    }

    private static Cards createCards(Card... inputCards) {
        return new Cards(new ArrayList<>(Arrays.asList(inputCards)));
    }

    @Test
    @DisplayName("유저 게임결과를 확인한다.")
    void checkUserGameResult() {
        PlayerGameResult playerGameResult = PlayerGameResult.create(dealer.calculateScore(), users);
        final Map<String, ResultType> expected = new HashMap<>(Map.ofEntries(
                Map.entry("slow", ResultType.WIN),
                Map.entry("jason", ResultType.DRAW),
                Map.entry("pobi", ResultType.LOSE)
        ));

        final Map<String, ResultType> actual = playerGameResult.getUserResults();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("딜러 게임결과를 확인한다.")
    void checkDealerGameResult() {
        PlayerGameResult playerGameResult = PlayerGameResult.create(dealer.calculateScore(), users);
        final Map<ResultType, Integer> expected = new HashMap<>(Map.ofEntries(
                Map.entry(ResultType.LOSE, 1),
                Map.entry(ResultType.WIN, 1),
                Map.entry(ResultType.DRAW, 1)
        ));

        final Map<ResultType, Integer> actual = playerGameResult.getDealerResultCount();

        assertThat(actual).isEqualTo(expected);
    }
}
