package blackjack.domain;

import blackjack.domain.card.CardPack;
import blackjack.domain.player.Gambler;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class GameResultTest {
    @Test
    @DisplayName("딜러보다 카드 합이 높으면 WIN을 반환한다")
    void win_if_bigger_than_dealer() {
        Players players = new Players();
        Gambler gambler = new Gambler("두리");
        players.addGamblers(List.of(gambler));
        CardPack cardPack = new CardPack(new ReversedSortShuffle());
        players.initPlayers(cardPack);
        players.dealAddCard(cardPack, gambler);
        Assertions.assertThat(GameResult.getGameResult(players.getDealer(), gambler)).isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("딜러보다 카드 합이 낮으면 LOSE 을 반환한다")
    void win_if_bigger_than_dealer2() {
        Players players = new Players();
        Gambler gambler = new Gambler("두리");
        players.addGamblers(List.of(gambler));
        CardPack cardPack = new CardPack(new ReversedSortShuffle());
        players.initPlayers(cardPack);
        players.dealAddCard(cardPack, players.getDealer());
        Assertions.assertThat(GameResult.getGameResult(players.getDealer(), gambler)).isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("딜러와 카드 합이 같으면 DRAW 을 반환한다")
    void win_if_bigger_than_dealer3() {
        Players players = new Players();
        Gambler gambler = new Gambler("두리");
        players.addGamblers(List.of(gambler));
        CardPack cardPack = new CardPack(new ReversedSortShuffle());
        players.initPlayers(cardPack);
        Assertions.assertThat(GameResult.getGameResult(players.getDealer(), gambler)).isEqualTo(GameResult.DRAW);
    }

    @Test
    @DisplayName("딜러와 참가자가 둘다 bust 면 DRAW 를 반환한다")
    void both_bust_test() {
        Players players = new Players();
        Gambler gambler = new Gambler("두리");
        players.addGamblers(List.of(gambler));
        CardPack cardPack = new CardPack(new SortShuffle());
        players.initPlayers(cardPack);
        players.dealAddCard(cardPack, gambler);
        players.dealAddCard(cardPack, players.getDealer());

        Assertions.assertThat(GameResult.getGameResult(players.getDealer(), gambler)).isEqualTo(GameResult.DRAW);
    }

    @Test
    @DisplayName("참가자만 bust 면 LOSE 를 반환한다")
    void both_bust_test2() {
        Players players = new Players();
        Gambler gambler = new Gambler("두리");
        players.addGamblers(List.of(gambler));
        CardPack cardPack = new CardPack(new SortShuffle());
        players.initPlayers(cardPack);
        players.dealAddCard(cardPack, gambler);

        Assertions.assertThat(GameResult.getGameResult(players.getDealer(), gambler)).isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("딜러만 bust 면 WIN 를 반환한다")
    void both_bust_test3() {
        Players players = new Players();
        Gambler gambler = new Gambler("두리");
        players.addGamblers(List.of(gambler));
        CardPack cardPack = new CardPack(new SortShuffle());
        players.initPlayers(cardPack);
        players.dealAddCard(cardPack, players.getDealer());

        Assertions.assertThat(GameResult.getGameResult(players.getDealer(), gambler)).isEqualTo(GameResult.WIN);
    }
}
