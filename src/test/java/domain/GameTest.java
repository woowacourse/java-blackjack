package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTest {
    private static final List<String> PLAYER_NAMES = List.of("pobi", "terry", "rati", "gump");
    private static final CardShuffleStrategy FIXED_SHUFFLE_STRATEGY = new CardShuffleStrategy() {
        @Override
        public void shuffle(List<Card> cards) {
            //TODO: Mockito 사용으로 더미 구현 코드 제거 고민해볼 것
        }
    };
    private Game game;

    @BeforeEach
    void setUp() {
        game = Game.registerParticipantsAndPrepareTotalDeck(PLAYER_NAMES, FIXED_SHUFFLE_STRATEGY);
    }

    @Test
    @DisplayName("게임 생성 시 딜러와 입력된 플레이어들이 정상적으로 등록된다.")
    void shouldRegisterParticipantsToGame() {
        assertThat(game.getDealer()).isNotNull();
        assertThat(game.getPlayers()).hasSize(4);
        assertThat(game.getParticipants()).hasSize(5);
    }

    @Test
    @DisplayName("초기 카드 분배 시 모든 참여자가 2장씩 카드를 받는다.")
    void shouldReadyParticipantDecksWithTwoInitialCards() {
        // when
        game.readyParticipantDecks();
        List<Participant> participants = game.getParticipants();

        // then
        for (Participant participant : participants) {
            assertThat(participant.getCards()).hasSize(2);
        }
    }

    @Test
    @DisplayName("플레이어와 딜러 모두 조건에 따라 카드를 추가로 뽑을 수 있다.")
    void shouldReturnTrueWhenParticipantsCanDrawCardUnderCondition() {
        // given
        Dealer dealer = game.getDealer();
        addCardsToPlayerDeck(dealer,
                new Card(CardShape.SPADE, CardContents.TWO),
                new Card(CardShape.CLOVER, CardContents.THREE)
        );

        Player player = game.getPlayers().iterator().next();
        addCardsToPlayerDeck(player,
                new Card(CardShape.HEART, CardContents.TWO),
                new Card(CardShape.HEART, CardContents.THREE)
        );

        // when
        boolean dealerResult = game.drawCardUnderCondition(dealer);
        boolean playerResult = game.drawCardUnderCondition(player);

        // then
        assertTrue(dealerResult);
        assertTrue(playerResult);
    }

    @Test
    @DisplayName("최종 승패 결과 계산이 정확하게 수행된다.")
    void shouldReturnWinTieLossResult() {
        // given
        Dealer dealer = game.getDealer();
        addCardsToPlayerDeck(dealer,
                new Card(CardShape.HEART, CardContents.J),
                new Card(CardShape.HEART, CardContents.EIGHT)
        );

        Iterator<Player> players = game.getPlayers().iterator();
        Player player1 = players.next();
        addCardsToPlayerDeck(player1,
                new Card(CardShape.DIAMOND, CardContents.J),
                new Card(CardShape.DIAMOND, CardContents.THREE)
        );
        Player player2 = players.next();
        addCardsToPlayerDeck(player2,
                new Card(CardShape.SPADE, CardContents.J),
                new Card(CardShape.SPADE, CardContents.THREE)
        );
        Player player3 = players.next();
        addCardsToPlayerDeck(player3,
                new Card(CardShape.CLOVER, CardContents.J),
                new Card(CardShape.CLOVER, CardContents.NINE)
        );
        Player player4 = players.next();
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
        Map<Player, Result> playerResults = game.getPlayerWinTieLossResults();
        Map<Result, Integer> dealerResults = game.getDealerWinTieLossResult();

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
