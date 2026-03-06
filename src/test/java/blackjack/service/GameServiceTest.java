package blackjack.service;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Card;
import blackjack.domain.CardShape;
import blackjack.domain.CardValue;
import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Player;
import java.util.List;
import org.junit.jupiter.api.Test;

class GameServiceTest {

    @Test
    void 게임_시작_세팅_테스트() {
        // given
        GameService gameService = new GameService(new Deck());
        Player player = new Player("흑곰");
        Dealer dealer = new Dealer();

        // when
        gameService.settingCards(List.of(player), dealer);

        // then
        assertThat(player.getCards().size()).isEqualTo(2);
    }

    @Test
    void 승패_판단_테스트() {
        // given
        GameService gameService = new GameService(new Deck());
        Player player = new Player("흑곰");
        Dealer dealer = new Dealer();
        player.bring(new Card(CardValue.SEVEN, CardShape.DIAMOND));
        dealer.bring(new Card(CardValue.EIGHT, CardShape.DIAMOND));

        // when
        boolean isDealerWinning = gameService.isDealerWinning(player, dealer);

        // then
        assertThat(isDealerWinning).isTrue();
    }

}
