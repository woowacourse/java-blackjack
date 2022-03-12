package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
import blackjack.domain.card.Cards;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ParticipantResultTest {

    private Dealer dealer;
    private List<Player> players;

    @BeforeEach
    void setUpPlayer() {
        dealer = new Dealer(createCards(
                new Card(CardPattern.DIAMOND, CardNumber.JACK),
                new Card(CardPattern.DIAMOND, CardNumber.KING)
        ));

        players = Arrays.asList(
                new Player("slow", createCards(
                        new Card(CardPattern.SPADE, CardNumber.KING),
                        new Card(CardPattern.DIAMOND, CardNumber.QUEEN),
                        new Card(CardPattern.DIAMOND, CardNumber.ACE)
                )),
                new Player("jason", createCards(
                        new Card(CardPattern.SPADE, CardNumber.TEN),
                        new Card(CardPattern.HEART, CardNumber.TEN)
                )),
                new Player("pobi", createCards(
                        new Card(CardPattern.CLOVER, CardNumber.TEN),
                        new Card(CardPattern.HEART, CardNumber.FIVE)
                ))
        );
    }

    private static Cards createCards(Card... inputCards) {
        return new Cards(new ArrayList<>(Arrays.asList(inputCards)));
    }

    @Test
    @DisplayName("플레이어 게임결과를 확인한다.")
    void checkPlayerGameResult() {
        ParticipantResult participantResult = ParticipantResult.create(dealer.calculateScore(), players);
        final Map<String, GameResult> expected = new HashMap<>(Map.ofEntries(
                Map.entry("slow", GameResult.WIN),
                Map.entry("jason", GameResult.DRAW),
                Map.entry("pobi", GameResult.LOSE)
        ));

        final Map<String, GameResult> actual = participantResult.getPlayerResults();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("딜러 게임결과를 확인한다.")
    void checkDealerGameResult() {
        ParticipantResult participantResult = ParticipantResult.create(dealer.calculateScore(), players);
        final Map<GameResult, Integer> expected = new HashMap<>(Map.ofEntries(
                Map.entry(GameResult.LOSE, 1),
                Map.entry(GameResult.WIN, 1),
                Map.entry(GameResult.DRAW, 1)
        ));

        final Map<GameResult, Integer> actual = participantResult.getDealerResultCount();

        assertThat(actual).isEqualTo(expected);
    }
}
