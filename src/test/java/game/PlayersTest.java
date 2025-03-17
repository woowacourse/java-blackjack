package game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import card.CardDeck;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    @Test
    void 플레이어들을_정상적으로_생성한다() {
        assertThatCode(() -> new Players(List.of(
                new Player("pobi"),
                new Player("font")
        ))).doesNotThrowAnyException();
    }

    @Test
    void 플레이어들을_배팅금액과_함께_생성한다() {
        assertThatCode(() -> Players.of(
                List.of("pobi", "font"), List.of(1000, 2000)
        )).doesNotThrowAnyException();
    }

    @Test
    void 인원_수가_5명_초과이면_예외를_던진다() {
        List<Player> players = List.of(
                new Player("a"),
                new Player("b"),
                new Player("c"),
                new Player("d"),
                new Player("e"),
                new Player("f"));

        assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 인원_수가_0명이면_예외를_던진다() {
        List<Player> players = List.of();

        assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 같은_이름의_플레이어가_여러_명_있으면_예외를_던진다() {
        List<Player> players = List.of(
                new Player("a"),
                new Player("a"));

        assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 플레이어들은_게임_시작_시_카드를_드로우한다() {
        CardDeck cardDeck = CardDeck.prepareDeck(ArrayList::new);
        Players players = new Players(List.of(
                new Player("a"),
                new Player("b")));

        players.draw(cardDeck);

        Assertions.assertThat(players.getPlayers().get(0).getCardsCount()).isEqualTo(2);
        Assertions.assertThat(players.getPlayers().get(1).getCardsCount()).isEqualTo(2);
    }

    @Test
    void 플레이어들의_이름을_반환한다() {
        Players players = new Players(List.of(
                new Player("aa"),
                new Player("bb")));

        List<String> allPlayerNames = players.getAllPlayerNames();

        Assertions.assertThat(allPlayerNames).containsExactly("aa", "bb");
    }

    @Test
    void 플레이어들의_베팅_금액을_반환한다() {
        List<String> playerNames = List.of("a", "b");
        List<Integer> bettingMoneys = List.of(1000, 2000);
        Players players = Players.of(playerNames, bettingMoneys);

        List<Integer> actual = players.getBettingMoneys();
        
        assertThat(actual).containsExactlyElementsOf(bettingMoneys);
    }

}
