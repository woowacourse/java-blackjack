package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameResultTest {
    @Test
    @DisplayName("최종 승패 결과 계산이 정확하게 수행된다.")
    void shouldReturnWinTieLossResult() {
        // given
        Dealer dealer = new Dealer();
        addCardsToPlayerDeck(dealer,
                new Card(CardShape.HEART, CardContents.J),
                new Card(CardShape.HEART, CardContents.EIGHT)
        );

        List<String> playerNames = List.of("pobi", "terry", "rati", "gump");
        Players players = Players.of(playerNames);
        Iterator<Player> playerIterator = players.iterator();

        Player player1 = playerIterator.next();
        addCardsToPlayerDeck(player1,
                new Card(CardShape.DIAMOND, CardContents.J),
                new Card(CardShape.DIAMOND, CardContents.THREE)
        );
        Player player2 = playerIterator.next();
        addCardsToPlayerDeck(player2,
                new Card(CardShape.SPADE, CardContents.J),
                new Card(CardShape.SPADE, CardContents.THREE)
        );
        Player player3 = playerIterator.next();
        addCardsToPlayerDeck(player3,
                new Card(CardShape.CLOVER, CardContents.J),
                new Card(CardShape.CLOVER, CardContents.NINE)
        );
        Player player4 = playerIterator.next();
        addCardsToPlayerDeck(player4,
                new Card(CardShape.HEART, CardContents.K),
                new Card(CardShape.HEART, CardContents.NINE)
        );

        Map<Player, Result> expectPlayerWinLossResults = new LinkedHashMap<>();
        expectPlayerWinLossResults.put(player1, Result.LOSS);
        expectPlayerWinLossResults.put(player2, Result.LOSS);
        expectPlayerWinLossResults.put(player3, Result.WIN);
        expectPlayerWinLossResults.put(player4, Result.WIN);

        Map<Result, Integer> expectDealerWinLossResults = new LinkedHashMap<>();
        expectDealerWinLossResults.put(Result.WIN, 2);
        expectDealerWinLossResults.put(Result.LOSS, 2);

        // when
        GameResult gameResult = GameResult.calculate(dealer, players);
        Map<Player, Result> playerResults = gameResult.getPlayerResults();
        Map<Result, Integer> dealerResults = gameResult.getDealerResult();

        // then
        assertThat(playerResults).containsAllEntriesOf(expectPlayerWinLossResults);
        assertThat(dealerResults).containsAllEntriesOf(expectDealerWinLossResults);
    }

    private void addCardsToPlayerDeck(Participant participant, Card... cards) {
        for (Card card : cards) {
            participant.addCard(card);
        }
    }
}
