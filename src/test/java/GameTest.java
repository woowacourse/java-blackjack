import domain.Game;
import domain.Result;
import domain.TotalDeck;
import domain.TotalDeckGenerator;
import domain.card.Card;
import domain.card.Number;
import domain.card.Shape;
import domain.user.Name;
import domain.user.Player;
import domain.user.Users;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static domain.Result.LOSE;
import static domain.Result.WIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class GameTest {
    @Test
    @DisplayName("게임이 생성될 때 각 유저에게 카드를 두 장씩 나눠준다.")
    void gameConstructTest() {
        TotalDeck totalDeck = new TotalDeck(TotalDeckGenerator.generate());
        Users users = new Users(List.of(new Player(new Name("a"))));

        Game game = new Game(totalDeck, users);

        assertThat(totalDeck.size()).isEqualTo(48);
    }

    @Test
    @DisplayName("입력이 y이면 현재 유저가 카드를 추가로 받는다.")
    void doTest() {
        //given
        TotalDeck totalDeck = new TotalDeck(TotalDeckGenerator.generate());
        Users users = new Users(List.of(new Player(new Name("a"))));
        //when
        Game game = new Game(totalDeck, users);
        game.doOrDie("y");
        //then
        assertThat(game.showCurrentUserDeck().getCards()).hasSize(3);
    }

    @Test
    @DisplayName("입력이 n이면 다음 유저로 넘어간다.")
    void dieTest() {
        //given
        TotalDeck totalDeck = new TotalDeck(TotalDeckGenerator.generate());
        Users users = new Users(List.of(new Player(new Name("a"))));
        //when
        Game game = new Game(totalDeck, users);
        List<Card> oldUserDeck = game.showCurrentUserDeck().getCards();
        game.doOrDie("n");
        //then
        assertThat(game.showCurrentUserDeck()).isNotEqualTo(oldUserDeck);
    }

    @Test
    @DisplayName("카드의 합이 16 이하이면 카드를 추가로 받는다.")
    void addDealerCardAtConditionTest() {
        TotalDeck totalDeck = new TotalDeck(List.of(
                new Card(Shape.CLOVER, Number.FIVE),
                new Card(Shape.CLOVER, Number.SIX),
                new Card(Shape.CLOVER, Number.SEVEN)
        ));
        Users users = new Users(List.of());

        Game game = new Game(totalDeck, users);
        game.addDealerCardInCondition();

        List<Card> cards = game.showCurrentUserDeck().getCards();
        assertThat(cards.size()).isEqualTo(3);
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
        Map<Player, Result> playerResults = game.generatePlayerResults();
        Map<Result, Integer> dealerResult = game.generateDealerResult();

        assertAll(
                () -> assertThat(playerResults.get(player)).isEqualTo(WIN),
                () -> assertThat(dealerResult.get(LOSE)).isEqualTo(1)
        );
    }
}
