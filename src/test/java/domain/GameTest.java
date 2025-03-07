package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import exception.ErrorException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class GameTest {

    private Game game;

    @BeforeEach
    void setUp() {
        List<Rank> ranks1 = List.of(Rank.JACK, Rank.JACK, Rank.JACK, Rank.JACK);
        List<Rank> ranks2 = List.of(Rank.FIVE, Rank.THREE, Rank.FIVE, Rank.SEVEN);
        List<List<Rank>> ranksStack = List.of(ranks1, ranks2);
        List<List<Card>> cardsStack = createCardsStackOfRanks(ranksStack);

        game = new Game();
        game.registerPlayers(List.of("pobi", "jason", "neo"));
        game.distributeInitialCards(cardsStack);
    }

    @Test
    @DisplayName("딜러 게임 결과 계산 기능 테스트")
    void dealerGameResultTest() {
        // given & when
        Map<Participant, GameResult> gameResult = game.determineDealerGameResult();
        GameResult dealerGameResult = gameResult.values().iterator().next();
        // then
        assertAll(
                () -> assertEquals(1, dealerGameResult.getStatusCount(GameStatus.WIN)),
                () -> assertEquals(1, dealerGameResult.getStatusCount(GameStatus.TIE)),
                () -> assertEquals(1, dealerGameResult.getStatusCount(GameStatus.LOSE))
        );
    }

    @ParameterizedTest
    @CsvSource({"pobi,0,0,1", "jason,0,1,0", "neo,1,0,0"})
    @DisplayName("플레이어 게임 결과 계산 기능 테스트")
    void playerGameResultTest(String name, int win, int tie, int lose) {
        // given & when
        Map<Participant, GameResult> gameResult = game.determinePlayersGameResult();
        Participant participant = gameResult.keySet().stream()
                .filter(player -> player.isParticipant(name))
                .findFirst()
                .orElseThrow();
        GameResult playerGameResult = gameResult.get(participant);
        // then
        assertAll(
                () -> assertEquals(win, playerGameResult.getStatusCount(GameStatus.WIN)),
                () -> assertEquals(tie, playerGameResult.getStatusCount(GameStatus.TIE)),
                () -> assertEquals(lose, playerGameResult.getStatusCount(GameStatus.LOSE))
        );
    }

    @Test
    @DisplayName("이름이 중복되는 참여자 예외 테스트")
    void duplicateParticipantNamesTest() {
        // given
        String name = "pobi,jason,pobi";
        List<String> names = Arrays.stream(name.split(",")).toList();
        // when & then
        assertThatThrownBy(() -> game.registerPlayers(names))
                .isInstanceOf(ErrorException.class)
                .hasMessageContaining("[ERROR]");
    }

    private static List<Card> createCardsOfRanks(List<Rank> ranks) {
        return ranks.stream()
                .map(rank -> new Card(rank, Suit.DIAMOND))
                .toList();
    }

    private static List<List<Card>> createCardsStackOfRanks(List<List<Rank>> ranks) {
        List<List<Card>> cardsStack = new ArrayList<>();
        for (List<Rank> rank : ranks) {
            List<Card> cards = new ArrayList<>(createCardsOfRanks(rank));
            cardsStack.add(cards);
        }
        return cardsStack;
    }
}
