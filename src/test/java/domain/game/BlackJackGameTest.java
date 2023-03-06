package domain.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import domain.deck.Deck;
import domain.game.BlackJackGame;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class BlackJackGameTest {
    List<String> names;
    BlackJackGame blackJackGame;

    @BeforeEach
    void setup() {
        names = List.of("name1", "name2");
        blackJackGame = new BlackJackGame(new Deck(), names);
    }

    @DisplayName("처음 카드를 나눠주면 플레이어의 패의 크기는 2이다.")
    @Test
    void distributeTwoCardsToPlayersTest() {
        int expectedCardSize = 2;
        for (String name : names) {
            assertEquals(expectedCardSize, blackJackGame.getCards(name).size());
        }
    }

    @DisplayName("처음 카드를 나눠받고 카드를 뽑으면 패의 크기는 3이다.")
    @Test
    void drawCardTest() {
        int expectedCardSize = 3;
        for (String name : names) {
            blackJackGame.drawCard(name);
            assertEquals(expectedCardSize, blackJackGame.getCards(name).size());
        }
    }

    @DisplayName("섞지 않은 덱에서 2장씩 뽑았을 때 Rank의 역순으로 카드가 나온다.")
    @ParameterizedTest
    @CsvSource(value = {"name1:20", "name2:17"}, delimiter = ':')
    void getScoreTest(String name, int score) {
        assertEquals(score, blackJackGame.getScore(name));
    }

    @DisplayName("딜러보다 점수가 낮은 플레이어는 LOSE, 점수가 같은 플레이어는 DRAW를 결과로 갖는다.")
    @Test
    void decidePlayersOutcomeTest() {
        // 딜러:  K + Q = 20점
        // name1: J + 10 = 20점
        // name2: 9 + 8 = 18점
        Map<String, Outcome> playersOutcome = blackJackGame.decidePlayersOutcome();
        Outcome expectedName1Outcome = Outcome.DRAW;
        Outcome expectedName2Outcome = Outcome.LOSE;

        assertEquals(expectedName1Outcome, playersOutcome.get("name1"));
        assertEquals(expectedName2Outcome, playersOutcome.get("name2"));
    }

    @DisplayName("딜러는 플레이어와 비교하여 결과로 갖는다.")
    @Test
    void decideDealerOutcomeTest() {
        int expectedWinCount = 1;
        int expectedDrawCount = 1;
        int expectedLoseCount = 0;

        EnumMap<Outcome, Integer> dealerOutcome = blackJackGame.decideDealerOutcome();

        assertEquals(expectedWinCount, dealerOutcome.get(Outcome.WIN));
        assertEquals(expectedDrawCount, dealerOutcome.get(Outcome.DRAW));
        assertEquals(expectedLoseCount, dealerOutcome.get(Outcome.LOSE));
    }
}
