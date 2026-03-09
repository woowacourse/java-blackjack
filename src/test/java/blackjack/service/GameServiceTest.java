package blackjack.service;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Card;
import blackjack.domain.CardShape;
import blackjack.domain.CardValue;
import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.User;
import java.util.List;
import org.junit.jupiter.api.Test;

class GameServiceTest {

    @Test
    void 게임_시작_세팅_테스트() {
        // given
        GameService gameService = new GameService(new Deck());
        User user = new User("흑곰");
        Dealer dealer = new Dealer();

        // when
        gameService.settingCards(List.of(user), dealer);

        // then
        assertThat(user.getCards().size()).isEqualTo(2);
    }

    @Test
    void 승패_판단_테스트() {
        // given
        GameService gameService = new GameService(new Deck());
        User user = new User("흑곰");
        Dealer dealer = new Dealer();
        user.bring(new Card(CardValue.SEVEN, CardShape.DIAMOND));
        dealer.bring(new Card(CardValue.EIGHT, CardShape.DIAMOND));

        // when
        boolean isDealerWinning = gameService.isDealerWinning(user, dealer);

        // then
        assertThat(isDealerWinning).isTrue();
    }

}
