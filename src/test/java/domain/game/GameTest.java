package domain.game;

import domain.card.Card;
import domain.card.Number;
import domain.card.Shape;
import domain.deck.TotalDeck;
import domain.deck.TotalDeckGenerator;
import domain.user.Player;
import domain.user.Users;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static domain.Command.YES;
import static domain.game.Result.LOSE;
import static domain.game.Result.WIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class GameTest {
    @Test
    @DisplayName("게임이 생성될 때 각 유저에게 카드를 두 장씩 나눠준다.")
    void gameConstructTest() {
        TotalDeck totalDeck = TotalDeckGenerator.generate();
        Users users = new Users(List.of("a"));

        new Game(totalDeck, users);

        assertThat(totalDeck.size()).isEqualTo(48);
    }

    @Test
    @DisplayName("입력이 y이면 현재 유저가 카드를 추가로 받는다.")
    void hitTest() {
        TotalDeck totalDeck = TotalDeckGenerator.generate();
        Users users = new Users(List.of("a"));
        Player player = users.getPlayers().get(0);
        Game game = new Game(totalDeck, users);
        game.determineState(YES, player);

        assertThat(player.getUserDeck().getCards()).hasSize(3);
    }

    @Test
    @DisplayName("결과를 반환한다.")
    void gameResultTest() {
        TotalDeck totalDeck = new TotalDeck(List.of(
                new Card(Shape.CLOVER, Number.FIVE),
                new Card(Shape.CLOVER, Number.SIX),
                new Card(Shape.CLOVER, Number.SEVEN),
                new Card(Shape.CLOVER, Number.EIGHT)
        ));
        Users users = new Users(List.of("a"));
        Player player = users.getPlayers().get(0);

        Game game = new Game(totalDeck, users);
        Map<Player, Result> playerResults = game.generatePlayerResults();
        Map<Result, Integer> dealerResult = game.generateDealerResult();

        assertAll(
                () -> assertThat(playerResults.get(player)).isEqualTo(LOSE),
                () -> assertThat(dealerResult.get(WIN)).isEqualTo(1)
        );
    }
}
