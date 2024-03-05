import domain.card.Card;
import domain.user.Name;
import domain.user.Player;
import domain.user.Users;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
        assertThat(game.showCurrentUserDeck()).hasSize(3);
    }

    @Test
    @DisplayName("입력이 n이면 다음 유저로 넘어간다.")
    void dieTest() {
        //given
        TotalDeck totalDeck = new TotalDeck(TotalDeckGenerator.generate());
        Users users = new Users(List.of(new Player(new Name("a"))));
        //when
        Game game = new Game(totalDeck, users);
        List<Card> oldUserDeck = game.showCurrentUserDeck();
        game.doOrDie("n");
        //then
        assertThat(game.showCurrentUserDeck()).isNotEqualTo(oldUserDeck);
    }
}
