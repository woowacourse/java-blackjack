package domain.game;

import static domain.Command.NO;
import static domain.Command.YES;
import static domain.game.Result.LOSE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.TotalDeck;
import domain.TotalDeckGenerator;
import domain.card.Card;
import domain.card.Number;
import domain.card.Shape;
import domain.user.Name;
import domain.user.Player;
import domain.user.Users;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameTest {
    @Test
    @DisplayName("게임이 생성될 때 각 유저에게 카드를 두 장씩 나눠준다.")
    void gameConstructTest() {
        TotalDeck totalDeck = new TotalDeck(TotalDeckGenerator.generate());
        Users users = new Users(List.of(new Player(new Name("a"))));

        new Game(totalDeck, users);

        assertThat(totalDeck.size()).isEqualTo(48);
    }

    @Test
    @DisplayName("입력이 y이면 현재 유저가 카드를 추가로 받는다.")
    void hitTest() {
        TotalDeck totalDeck = new TotalDeck(TotalDeckGenerator.generate());
        Player player = new Player(new Name("a"));
        Users users = new Users(List.of(player));

        Game game = new Game(totalDeck, users);
        game.hitOrStay(YES);

        assertThat(player.getUserDeck().getCards()).hasSize(3);
    }

    @Test
    @DisplayName("입력이 n이면 다음 유저로 넘어간다.")
    void stayTest() {
        TotalDeck totalDeck = new TotalDeck(TotalDeckGenerator.generate());
        Player player = new Player(new Name("a"));
        Users users = new Users(List.of(player));

        Game game = new Game(totalDeck, users);
        game.hitOrStay(NO);

        assertThat(game.getCurrentPlayer()).isNotEqualTo(player);
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
        Player player = new Player(new Name("a"));
        Users users = new Users(List.of(player));

        Game game = new Game(totalDeck, users);
        PlayerResults playerResults = game.generatePlayerResults();
        DealerResult dealerResult = playerResults.generateDealerResult();

        assertAll(
                () -> assertThat(playerResults.getPlayerResults().get(player)).isEqualTo(LOSE),
                () -> assertThat(dealerResult.getResultsDetail()).isEqualTo("1승")
        );
    }
}
