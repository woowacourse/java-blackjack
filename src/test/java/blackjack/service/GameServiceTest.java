package blackjack.service;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Card;
import blackjack.domain.CardValue;
import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Player;
import blackjack.domain.Shape;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameServiceTest {

    @Test
    @DisplayName("게임 시작 세팅 테스트")
    void 게임_시작_세팅_테스트() {
        // given
        GameService gameService = new GameService();
        Player player = new Player("흑곰");
        Dealer dealer = new Dealer(new Deck());

        // when
        gameService.settingCards(List.of(player), dealer);

        // then
        assertThat(player.getCards().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("승패 판단 테스트")
    void 승패_판단_테스트() {
        // given
        GameService gameService = new GameService();
        Player player = new Player("흑곰");
        Dealer dealer = new Dealer(new Deck());
        player.bring(new Card(CardValue.SEVEN, Shape.DIAMOND));
        dealer.bring(new Card(CardValue.EIGHT, Shape.DIAMOND));

        // when
        boolean isDealerWinning = gameService.isDealerWinning(player, dealer);

        // then
        assertThat(isDealerWinning).isTrue();
    }

}