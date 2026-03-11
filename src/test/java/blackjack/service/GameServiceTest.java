package blackjack.service;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.deck.Card;
import blackjack.domain.deck.CardShape;
import blackjack.domain.deck.CardValue;
import blackjack.domain.participant.Dealer;
import blackjack.domain.deck.Deck;
import blackjack.domain.GameResult;
import blackjack.domain.participant.User;
import blackjack.domain.participant.Users;
import java.util.List;
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
        Users users = new Users(List.of(user));

        // when
        gameService.settingCards(users, dealer);

        // then
        assertThat(user.getCardsName().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("딜러 및 유저 승리 횟수 할당")
    void applyWinningCount() {
        // given
        User user = new User("흑곰");
        Dealer dealer = new Dealer();
        GameResult gameResult = new GameResult();
        user.bring(new Card(CardValue.SEVEN, CardShape.DIAMOND));
        dealer.bring(new Card(CardValue.EIGHT, CardShape.DIAMOND));

        // when
        gameService.applyGameResult(user, dealer, gameResult);

        // then
        assertThat(gameResult.isUserWin("흑곰")).isFalse();
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
}
