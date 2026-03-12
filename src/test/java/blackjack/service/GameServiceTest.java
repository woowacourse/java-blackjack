package blackjack.service;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.BettingAmount;
import blackjack.domain.GameResultType;
import blackjack.domain.deck.Card;
import blackjack.domain.deck.CardShape;
import blackjack.domain.deck.CardValue;
import blackjack.domain.participant.Dealer;
import blackjack.domain.deck.Deck;
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
        User user = new User("흑곰", new BettingAmount(10000));
        Dealer dealer = new Dealer();
        Users users = new Users(List.of(user));

        // when
        gameService.settingCards(users, dealer);

        // then
        assertThat(user.getCardsName().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("플레이어 버스트 시 LOSE 반환")
    void playerBurst() {
        // given
        User user = new User("흑곰", new BettingAmount(1000));
        Dealer dealer = new Dealer();

        // when
        user.add(new Card(CardValue.TEN, CardShape.DIAMOND));
        user.add(new Card(CardValue.TEN, CardShape.HEART));
        user.add(new Card(CardValue.TEN, CardShape.CLOVER));

        // then
        assertThat(gameService.determineResult(user, dealer)).isEqualTo(GameResultType.LOSE);
    }

    @Test
    @DisplayName("플레이어와 딜러 모두 블랙잭 시 DRAW 반환")
    void bothBlackjack() {
        // given
        User user = new User("흑곰", new BettingAmount(1000));
        Dealer dealer = new Dealer();

        // when
        user.add(new Card(CardValue.ACE, CardShape.DIAMOND));
        user.add(new Card(CardValue.TEN, CardShape.DIAMOND));
        dealer.add(new Card(CardValue.ACE, CardShape.HEART));
        dealer.add(new Card(CardValue.TEN, CardShape.HEART));

        // then
        assertThat(gameService.determineResult(user, dealer)).isEqualTo(GameResultType.DRAW);
    }

    @Test
    @DisplayName("플레이어만 블랙잭 시 BLACKJACK_WIN 반환")
    void playerOnlyBlackjack() {
        // given
        User user = new User("흑곰", new BettingAmount(1000));
        Dealer dealer = new Dealer();

        // when
        user.add(new Card(CardValue.ACE, CardShape.DIAMOND));
        user.add(new Card(CardValue.TEN, CardShape.DIAMOND));
        dealer.add(new Card(CardValue.EIGHT, CardShape.HEART));
        dealer.add(new Card(CardValue.NINE, CardShape.HEART));

        // then
        assertThat(gameService.determineResult(user, dealer)).isEqualTo(GameResultType.BLACKJACK_WIN);
    }

    @Test
    @DisplayName("딜러만 블랙잭 시 LOSE 반환")
    void dealerOnlyBlackjack() {
        // given
        User user = new User("흑곰", new BettingAmount(1000));
        Dealer dealer = new Dealer();

        // when
        user.add(new Card(CardValue.EIGHT, CardShape.DIAMOND));
        user.add(new Card(CardValue.NINE, CardShape.DIAMOND));
        dealer.add(new Card(CardValue.ACE, CardShape.HEART));
        dealer.add(new Card(CardValue.TEN, CardShape.HEART));

        // then
        assertThat(gameService.determineResult(user, dealer)).isEqualTo(GameResultType.LOSE);
    }

    @Test
    @DisplayName("딜러 버스트 시 WIN 반환")
    void dealerBurst() {
        // given
        User user = new User("흑곰", new BettingAmount(1000));
        Dealer dealer = new Dealer();

        // when
        user.add(new Card(CardValue.EIGHT, CardShape.DIAMOND));
        user.add(new Card(CardValue.NINE, CardShape.DIAMOND));
        dealer.add(new Card(CardValue.TEN, CardShape.HEART));
        dealer.add(new Card(CardValue.TEN, CardShape.CLOVER));
        dealer.add(new Card(CardValue.TEN, CardShape.DIAMOND));

        // then
        assertThat(gameService.determineResult(user, dealer)).isEqualTo(GameResultType.WIN);
    }

    @Test
    @DisplayName("플레이어 점수가 딜러보다 높으면 WIN 반환")
    void playerScoreHigher() {
        // given
        User user = new User("흑곰", new BettingAmount(1000));
        Dealer dealer = new Dealer();

        // when
        user.add(new Card(CardValue.TEN, CardShape.DIAMOND));
        user.add(new Card(CardValue.NINE, CardShape.DIAMOND));
        dealer.add(new Card(CardValue.TEN, CardShape.HEART));
        dealer.add(new Card(CardValue.EIGHT, CardShape.HEART));

        // then
        assertThat(gameService.determineResult(user, dealer)).isEqualTo(GameResultType.WIN);
    }

    @Test
    @DisplayName("플레이어와 딜러 점수가 같으면 DRAW 반환")
    void sameScore() {
        // given
        User user = new User("흑곰", new BettingAmount(1000));
        Dealer dealer = new Dealer();

        // when
        user.add(new Card(CardValue.TEN, CardShape.DIAMOND));
        user.add(new Card(CardValue.EIGHT, CardShape.DIAMOND));
        dealer.add(new Card(CardValue.TEN, CardShape.HEART));
        dealer.add(new Card(CardValue.EIGHT, CardShape.HEART));

        // then
        assertThat(gameService.determineResult(user, dealer)).isEqualTo(GameResultType.DRAW);
    }

    @Test
    @DisplayName("플레이어 점수가 딜러보다 낮으면 LOSE 반환")
    void playerScoreLower() {
        // given
        User user = new User("흑곰", new BettingAmount(1000));
        Dealer dealer = new Dealer();

        // when
        user.add(new Card(CardValue.TEN, CardShape.DIAMOND));
        user.add(new Card(CardValue.SEVEN, CardShape.DIAMOND));
        dealer.add(new Card(CardValue.TEN, CardShape.HEART));
        dealer.add(new Card(CardValue.EIGHT, CardShape.HEART));

        // then
        assertThat(gameService.determineResult(user, dealer)).isEqualTo(GameResultType.LOSE);
    }
}
