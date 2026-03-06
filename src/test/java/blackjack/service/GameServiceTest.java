package blackjack.service;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.User;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameServiceTest {

    @Test
    @DisplayName("게임 시작 세팅 테스트")
    void 게임_시작_세팅_테스트() {
        // given
        GameService gameService = new GameService();
        User user = new User("흑곰");
        Dealer dealer = new Dealer(new Deck());

        // when
        gameService.settingCards(List.of(user), dealer);

        // then
        assertThat(user.getCards().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("승패 판단 테스트")
    void 승패_판단_테스트() {
        // given
        GameService gameService = new GameService();
        User user = new User("흑곰");
        Dealer dealer = new Dealer(new Deck());
        user.bring(dealer.bringCard());
        dealer.bring(dealer.bringCard());

        // when
        boolean isDealerWinning = gameService.isDealerWinning(user, dealer);

        // then
        assertThat(isDealerWinning).isTrue();
    }

}