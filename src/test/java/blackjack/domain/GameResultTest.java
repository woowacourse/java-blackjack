package blackjack.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class GameResultTest {
    @Test
    @DisplayName("딜러보다 카드 합이 높으면 WIN을 반환한다")
    void win_if_bigger_than_dealer() {
        Players players = new Players();
        Player gambler = new Gambler("두리");
        players.addGamblers(List.of(gambler));
        CardPack cardPack = new CardPack(new ReversedSortShuffle());
        players.initPlayers(cardPack);
        players.dealAddCard(cardPack, gambler);
        Assertions.assertThat(GameResult.getGameResult(players.getDealer(), gambler)).isEqualTo(GameResult.WIN);
    }
}
