package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class GameResultTest {

    private Players createDummyPlayer(List<String> playerNames) {
        List<Player> players = new ArrayList<>();
        for (String playerName : playerNames) {
            Player player = new Player(new Name(playerName), new BetMoney(1000));
            players.add(player);
        }
        return Players.of(players);
    }

    private void addCardsToParticipantDeck(Participant participant, Card... cards) {
        for (Card card : cards) {
            participant.addCard(card);
        }
    }

    @Test
    @DisplayName("최종 승패 결과 계산이 정확하게 수행된다.")
    void shouldReturnWinTieLossResult() {
        // given
        Dealer dealer = new Dealer();
        addCardsToParticipantDeck(dealer,
                new Card(CardShape.HEART, CardContents.J),
                new Card(CardShape.HEART, CardContents.EIGHT)
        );

        List<String> playerNames = List.of("pobi", "terry", "rati", "gump");
        Players players = createDummyPlayer(playerNames);
        Iterator<Player> playerIterator = players.iterator();

        Player player1 = playerIterator.next();
        addCardsToParticipantDeck(player1,
                new Card(CardShape.DIAMOND, CardContents.J),
                new Card(CardShape.DIAMOND, CardContents.A)
        );
        Player player2 = playerIterator.next();
        addCardsToParticipantDeck(player2,
                new Card(CardShape.SPADE, CardContents.J),
                new Card(CardShape.SPADE, CardContents.THREE)
        );
        Player player3 = playerIterator.next();
        addCardsToParticipantDeck(player3,
                new Card(CardShape.CLOVER, CardContents.J),
                new Card(CardShape.CLOVER, CardContents.NINE)
        );
        Player player4 = playerIterator.next();
        addCardsToParticipantDeck(player4,
                new Card(CardShape.HEART, CardContents.K),
                new Card(CardShape.HEART, CardContents.NINE)
        );

        Map<Player, Profit> expectPlayerWinLossResults = new LinkedHashMap<>();
        expectPlayerWinLossResults.put(player1, new Profit(1500L));
        expectPlayerWinLossResults.put(player2, new Profit(-1000L));
        expectPlayerWinLossResults.put(player3, new Profit(1000L));
        expectPlayerWinLossResults.put(player4, new Profit(1000L));

        long expectDealerWinLossResults = -2500L;

        // when
        GameResult gameResult = GameResult.calculate(dealer, players);
        Map<Player, Profit> playerResults = gameResult.getPlayerProfits();
        long dealerResults = gameResult.getDealerProfit();

        // then
        assertThat(playerResults).isEqualTo(expectPlayerWinLossResults);
        assertThat(dealerResults).isEqualTo(expectDealerWinLossResults);
    }

    @Nested
    class GenerateGameResultTest {
        @Test
        @DisplayName("딜러의 카드 합계가 16 미만인 상태로 게임 결과 계산을 시도하는 경우 예외가 발생한다.")
        void shouldThrowExceptionWhenDealerScoreUnderMinimum() {
            // given
            Dealer dealer = new Dealer();
            addCardsToParticipantDeck(dealer,
                    new Card(CardShape.SPADE, CardContents.TWO),
                    new Card(CardShape.CLOVER, CardContents.THREE)
            );
            List<String> playerNames = List.of("pobi", "terry");
            Players players = createDummyPlayer(playerNames);

            // when & then
            assertThatThrownBy(() -> GameResult.calculate(dealer, players))
                    .isInstanceOf(IllegalStateException.class);
        }

        @Test
        @DisplayName("딜러의 카드 합계가 16인 상태로 게임 결과 계산을 시도하는 경우 예외가 발생한다.")
        void shouldThrowExceptionWhenDealerScoreEqualsMinimum() {
            // given
            Dealer dealer = new Dealer();
            addCardsToParticipantDeck(dealer,
                    new Card(CardShape.SPADE, CardContents.TEN),
                    new Card(CardShape.CLOVER, CardContents.SIX)
            );
            List<String> playerNames = List.of("pobi", "terry");
            Players players = createDummyPlayer(playerNames);

            // when & then
            assertThatThrownBy(() -> GameResult.calculate(dealer, players))
                    .isInstanceOf(IllegalStateException.class);
        }
    }
}
