package blackjack.service;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Card;
import blackjack.domain.CardShape;
import blackjack.domain.CardValue;
import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameServiceTest {

    private GameService gameService;

    @BeforeEach
    void setUp() {
        gameService = new GameService(new Deck());
    }

    @Test
    @DisplayName("게임 시작 세팅 테스트")
    void gameSetting() {
        // given
        User user = new User("흑곰");
        Dealer dealer = new Dealer();

        // when
        gameService.settingCards(List.of(user), dealer);

        // then
        assertThat(user.getCards().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("승패 판단 테스트")
    void winningJudge() {
        // given
        User user = new User("흑곰");
        Dealer dealer = new Dealer();
        user.bring(new Card(CardValue.SEVEN, CardShape.DIAMOND));
        dealer.bring(new Card(CardValue.EIGHT, CardShape.DIAMOND));

        // when
        boolean isDealerWinning = gameService.isDealerWinning(user, dealer);

        // then
        assertThat(isDealerWinning).isTrue();
    }

    @Test
    @DisplayName("딜러 및 유저 승리 횟수 할당")
    void applyWinningCount() {
        // given
        User user = new User("흑곰");
        Dealer dealer = new Dealer();
        Map<String, Boolean> result = new HashMap<>();
        user.bring(new Card(CardValue.SEVEN, CardShape.DIAMOND));
        dealer.bring(new Card(CardValue.EIGHT, CardShape.DIAMOND));

        // when
        gameService.applyGameResult(user, dealer, result);

        // then
        assertThat(false).isEqualTo(result.get("흑곰"));

    }

}
