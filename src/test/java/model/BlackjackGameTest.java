package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import model.card.Card;
import model.card.CardNumber;
import model.card.CardShape;
import model.cards.Cards;
import model.cards.DealerCards;
import model.cards.PlayerCards;
import model.deck.Deck;
import model.result.GameResult;
import model.result.GameResults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {

    private Deck deck;
    private Cards pobiCards;
    private Players players;
    private DealerCards dealerCards;
    private BlackjackGame blackjackGame;

    @BeforeEach
    void setUp() {
        deck = new Deck(new ArrayList<>(List.of(new Card(CardNumber.THREE, CardShape.DIAMOND))));

        pobiCards = new PlayerCards(new ArrayList<>(List.of(
                new Card(CardNumber.NINE, CardShape.CLOVER),
                new Card(CardNumber.TEN, CardShape.DIAMOND)))
        );

        players = new Players(Map.of("pobi", pobiCards));

        dealerCards = new DealerCards(new ArrayList<>(List.of(
                new Card(CardNumber.KING, CardShape.SPADE),
                new Card(CardNumber.FIVE, CardShape.SPADE),
                new Card(CardNumber.QUEEN, CardShape.SPADE)))
        );

        blackjackGame = new BlackjackGame(deck, players, dealerCards);
    }

    @DisplayName("플레이어 이름에 해당하는 플레이어의 Cards를 찾아 반환한다.")
    @Test
    void getPlayerCardsByNameTest() {
        assertThat(blackjackGame.getPlayerCardsByName("pobi")).isEqualTo(pobiCards);
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

    @DisplayName("플레이어가 버스트인지 확인한다.")
    @Test
    void checkIsBustByNameTest() {
        Cards hotteokCards = new PlayerCards(new ArrayList<>(List.of(
                new Card(CardNumber.FIVE, CardShape.CLOVER),
                new Card(CardNumber.SEVEN, CardShape.SPADE),
                new Card(CardNumber.QUEEN, CardShape.CLOVER)
        )));
        BlackjackGame blackjackGame = new BlackjackGame(
                deck, new Players(Map.of(
                "pobi", pobiCards,
                "hotteok", hotteokCards)), dealerCards
        );

        assertAll(
                () -> assertThat(blackjackGame.checkIsBustByName("pobi")).isFalse(),
                () -> assertThat(blackjackGame.checkIsBustByName("hotteok")).isTrue()
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
        Cards hotteokCards = new PlayerCards(new ArrayList<>(List.of(
                new Card(CardNumber.FIVE, CardShape.CLOVER),
                new Card(CardNumber.SEVEN, CardShape.SPADE),
                new Card(CardNumber.QUEEN, CardShape.CLOVER)
        )));
        Map<String, Cards> map = new LinkedHashMap<>();
        map.put("pobi", pobiCards);
        map.put("hotteok", hotteokCards);

        BlackjackGame blackjackGame = new BlackjackGame(
                deck, new Players(map), dealerCards
        );
        assertThat(blackjackGame.getSequencedPlayerNames())
                .containsSequence("pobi", "hotteok");
    }

    @DisplayName("플레이어들의 게임 결과를 반환한다.")
    @Test
    void calculateGameResult() {
        Cards hotteokCards = new PlayerCards(new ArrayList<>(List.of(
                new Card(CardNumber.FIVE, CardShape.CLOVER),
                new Card(CardNumber.SEVEN, CardShape.SPADE),
                new Card(CardNumber.QUEEN, CardShape.CLOVER)
        )));
        BlackjackGame blackjackGame = new BlackjackGame(
                deck, new Players(Map.of(
                "pobi", pobiCards,
                "hotteok", hotteokCards)), dealerCards
        );
        GameResults gameResults = blackjackGame.calculateGameResults();
        assertAll(
                () -> assertThat(gameResults.getGameResultByName("pobi")).isEqualTo(GameResult.WIN),
                () -> assertThat(gameResults.getGameResultByName("hotteok")).isEqualTo(GameResult.LOSE)
        );
    }
}
