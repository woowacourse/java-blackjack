package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import model.bet.BettingResults;
import model.bet.ParticipantsBet;
import model.card.Card;
import model.card.CardNumber;
import model.card.CardShape;
import model.cards.Cards;
import model.cards.DealerCards;
import model.cards.ParticipantsCards;
import model.cards.PlayerCards;
import model.deck.Deck;
import model.result.GameResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {

    private BlackjackGame blackjackGame;

    @BeforeEach
    void setUp() {
        Deck deck = new Deck(new ArrayList<>(List.of(new Card(CardNumber.THREE, CardShape.DIAMOND))));

        PlayerCards pobiCards = new PlayerCards(new ArrayList<>(List.of(
                new Card(CardNumber.NINE, CardShape.CLOVER),
                new Card(CardNumber.TEN, CardShape.DIAMOND)))
        );

        PlayerCards hotteokCards = new PlayerCards(new ArrayList<>(List.of(
                new Card(CardNumber.FIVE, CardShape.CLOVER),
                new Card(CardNumber.SEVEN, CardShape.SPADE),
                new Card(CardNumber.QUEEN, CardShape.CLOVER)
        )));

        DealerCards dealerCards = new DealerCards(new ArrayList<>(List.of(
                new Card(CardNumber.KING, CardShape.SPADE),
                new Card(CardNumber.FIVE, CardShape.SPADE),
                new Card(CardNumber.QUEEN, CardShape.SPADE)))
        );

        Map<String, PlayerCards> map = new LinkedHashMap<>();
        map.put("pobi", pobiCards);
        map.put("hotteok", hotteokCards);

        ParticipantsCards participantsCards = new ParticipantsCards(dealerCards, map);

        ParticipantsBet participantsBet = new ParticipantsBet(
                Map.of("pobi", 10_000, "hotteok", 1_000));

        Participants participants = new Participants(participantsCards, participantsBet);

        blackjackGame = new BlackjackGame(deck, participants);
    }

    @DisplayName("플레이어 이름에 해당하는 플레이어의 Cards를 찾아 반환한다.")
    @Test
    void getPlayerCardsByNameTest() {
        Cards expected = new PlayerCards(new ArrayList<>(List.of(
                new Card(CardNumber.NINE, CardShape.CLOVER),
                new Card(CardNumber.TEN, CardShape.DIAMOND)))
        );

        assertThat(blackjackGame.getPlayerCardsByName("pobi")).isEqualTo(expected);
    }

    @DisplayName("딜러의 점수를 반환한다.")
    @Test
    void getDealerResultTest() {
        assertThat(blackjackGame.getDealerResult()).isEqualTo(25);
    }

    @DisplayName("플레이어의 이름으로 플레이어의 점수를 반환한다.")
    @Test
    void getPlayerResultByName() {
        assertThat(blackjackGame.getPlayerResultByName("pobi")).isEqualTo(19);
    }

    @DisplayName("플레이어 이름에 해당하는 플레이어에게 카드를 한장 준다.")
    @Test
    void giveCardToPlayer() {
        blackjackGame.giveCardToPlayer("pobi");
        assertThat(blackjackGame.getPlayerCardsByName("pobi").getCards()).hasSize(3);
    }

    @DisplayName("플레이어가 카드를 더 뽑을 수 있는지 확인한다.")
    @Test
    void checkIsBustByNameTest() {
        assertAll(
                () -> assertThat(blackjackGame.canPlayerDrawCard("pobi")).isTrue(),
                () -> assertThat(blackjackGame.canPlayerDrawCard("hotteok")).isFalse()
        );
    }

    @DisplayName("딜러가 추가로 뽑은 카드의 수를 반환한다.")
    @Test
    void getDealerAdditionalDrawCountTest() {
        assertThat(blackjackGame.getDealerAdditionalDrawCount()).isEqualTo(1);
    }

    @DisplayName("순서가 있는 플레이어 이름 Set을 반환한다.")
    @Test
    void getSequencedPlayerNames() {
        assertThat(blackjackGame.getSequencedPlayerNames())
                .containsSequence("pobi", "hotteok");
    }

    @DisplayName("딜러와 플레이어들의 베팅 결과를 반환한다.")
    @Test
    void calculateBettingResult() {
        BettingResults bettingResults = blackjackGame.calculateBettingResults();
        assertAll(
                () -> assertThat(bettingResults.getBettingResultByName("pobi")).isEqualTo(10_000),
                () -> assertThat(bettingResults.getBettingResultByName("hotteok")).isEqualTo(-1_000),
                () -> assertThat(bettingResults.calculateDealerBettingResult()).isEqualTo(-9_000)
        );
    }

    @DisplayName("플레이어들의 이름과 배팅금액을 통해 BlackjackGame을 생성한다.")
    @Test
    void createTest() {
        List<String> rawPlayers = List.of("pobi", "hotteok");
        List<Integer> betAmount = List.of(10_000, 1_000);
        BlackjackGame game = BlackjackGame.createBlackjackGame(rawPlayers, betAmount);

        assertAll(
                () -> assertThat(game.getSequencedPlayerNames()).containsSequence("pobi", "hotteok"),
                () -> assertThat(game.getDealerCards()).isNotNull(),
                () -> assertThatCode(() -> game.giveCardToPlayer("pobi")).doesNotThrowAnyException()
        );
    }
}
