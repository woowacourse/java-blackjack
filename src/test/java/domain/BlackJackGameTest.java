package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import domain.deck.Deck;
import domain.game.BlackJackGame;
import domain.player.Batting;
import domain.player.Name;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class BlackJackGameTest {
    List<Name> names;
    List<Batting> amounts;
    BlackJackGame blackJackGame;

    @BeforeEach
    void setup() {
        names = Stream.of("name1", "name2").map(Name::new).collect(Collectors.toList());
        amounts = Stream.of(1, 100).map(Batting::new).collect(Collectors.toList());
        blackJackGame = new BlackJackGame(new Deck(), names, amounts);
    }

    @DisplayName("처음 카드를 나눠주면 플레이어의 패의 크기는 2이다.")
    @Test
    void distributeTwoCardsToPlayersTest() {
        final int expectedCardSize = 2;
        for (final Name name : names) {
            assertEquals(expectedCardSize, blackJackGame.getPlayerCards(name).size());
        }
    }

    @DisplayName("처음 카드를 나눠받고 카드를 뽑으면 패의 크기는 3이다.")
    @Test
    void drawCardTest() {
        final int expectedCardSize = 3;
        for (final Name name : names) {
            blackJackGame.drawCardPlayer(name);
            assertEquals(expectedCardSize, blackJackGame.getPlayerCards(name).size());
        }
    }

    @DisplayName("섞지 않은 덱에서 2장씩 뽑았을 때 Rank의 역순으로 카드가 나온다.")
    @ParameterizedTest
    @CsvSource(value = {"name1:20", "name2:17"}, delimiter = ':')
    void getScoreTest(final Name name, final int score) {
        assertEquals(score, blackJackGame.getPlayerScore(name));
    }
}
