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
    void 플레이어를_정상적으로_생성한다() {
        assertThatCode(() -> new Players(List.of(
                new Player("pobi"),
                new Player("font")
        ))).doesNotThrowAnyException();
    }

    @Test
    void 인원_수가_5명_초과이면_예외를_던진다() {
        //given
        List<Player> players = List.of(
                new Player("a"),
                new Player("b"),
                new Player("c"),
                new Player("d"),
                new Player("e"),
                new Player("f"));

        //when & then
        assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 인원_수가_0명이면_예외를_던진다() {
        //given
        List<Player> players = List.of();

        //when & then
        assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 같은_이름의_플레이어가_여러_명_있으면_예외를_던진다() {
        //given
        List<Player> players = List.of(
                new Player("a"),
                new Player("a"));

        //when & then
        assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 모든_플레이어는_게임_시작_시_카드를_드로우한다() {
        //given
        CardDeck cardDeck = CardDeck.prepareDeck(ArrayList::new);
        Players players = new Players(List.of(
                new Player("a"),
                new Player("b")));

        //when
        players.draw(cardDeck);

        //when
        Assertions.assertThat(players.getPlayers().get(0).getCardsCount()).isEqualTo(2);
        Assertions.assertThat(players.getPlayers().get(1).getCardsCount()).isEqualTo(2);
    }

    @Test
    void 모든_플레이어의_이름을_반환한다() {
        //given
        Players players = new Players(List.of(
                new Player("aa"),
                new Player("bb")));

        //when
        List<String> allPlayerNames = players.getAllPlayerNames();

        //then
        Assertions.assertThat(allPlayerNames).containsExactly("aa", "bb");
    }

    @Test
    void 모든_플레이어의_수익을_계산한다() {
        //given
        List<Player> players = List.of(
                new Player("a", 1000),
                new Player("b", 1000),
                new Player("c", 1000),
                new Player("d", 1000));
        List<GameResult> gameResults = List.of(
                GameResult.BLACKJACK, GameResult.WIN, GameResult.DRAW, GameResult.LOSE);

        //when
        Profits profits = Profits.of(players, gameResults);

        //then
        assertThat(profits.getProfits()).containsExactlyElementsOf(List.of(1500, 1000, 0, -1000));
    }
}
