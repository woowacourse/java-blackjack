package domain.game;

import domain.card.Card;
import domain.player.Dealer;
import domain.player.Name;
import domain.player.User;
import domain.player.Users;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.ResultGenerator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultTest {
    @Test
    @DisplayName("Result 생성")
    void create() {
        assertThat(new Result()).isInstanceOf(Result.class);
    }

    @Test
    @DisplayName("winCount 증가 확인")
    void addWinCount() {
        Result result = new Result();

        assertThat(result.getWinCount()).isEqualTo(0);

        result.addWinCount();
        assertThat(result.getWinCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("loseCount 증가 확인")
    void addLoseCount() {
        Result result = new Result();

        assertThat(result.getLoseCount()).isEqualTo(0);

        result.addLoseCount();
        assertThat(result.getLoseCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("플레이 횟수 1초과 확인")
    void isPlayCountMoreThanOne_IfPlayedMoreThanOnce_IsTrue() {
        Result result = new Result();

        result.addLoseCount();
        result.addLoseCount();
        assertThat(result.isPlayCountMoreThanOne()).isTrue();
    }

    @Test
    @DisplayName("1회 이상 승리 확인")
    void hasWin_IfWonMoreThanOnce_IsTrue() {
        Result result = new Result();

        result.addLoseCount();
        result.addWinCount();
        assertThat(result.hasWin()).isTrue();
    }

    @Test
    @DisplayName("수익률 테스트 - 유저 블랙잭 승리시 1.5배의 수익금")
    void calculateWinningMoney_WhenBlackJackWin_Get150PercentMoney() {
        User user = new User("user");
        Dealer dealer = new Dealer();

        user.addBettingMoney(new Money("1000"));
        user.addCard(Card.of("스페이드", "10"));
        user.addCard(Card.of("스페이드", "A"));
        dealer.addCard(Card.of("스페이드", "9"));
        dealer.addCard(Card.of("스페이드", "K"));

        Results results = ResultGenerator.create(dealer, new Users(Arrays.asList(user)));
        Result userResult = results.getResults().get(new Name("user"));
        BigDecimal userWinningMoney = userResult.getWinningMoney();

        assertThat(userWinningMoney.setScale(0, RoundingMode.FLOOR)).isEqualTo("1500");
    }

    @Test
    @DisplayName("수익률 테스트 - 승리시 1배의 수익금")
    void calculateWinningMoney_WhenNormalWin_Get100PercentMoney() {
        User user = new User("user");
        Dealer dealer = new Dealer();

        user.addBettingMoney(new Money("1000"));
        user.addCard(Card.of("스페이드", "5"));
        user.addCard(Card.of("스페이드", "4"));
        dealer.addCard(Card.of("스페이드", "3"));
        dealer.addCard(Card.of("스페이드", "5"));

        Results results = ResultGenerator.create(dealer, new Users(Arrays.asList(user)));
        Result userResult = results.getResults().get(new Name("user"));
        BigDecimal userWinningMoney = userResult.getWinningMoney();

        assertThat(userWinningMoney.setScale(0, RoundingMode.FLOOR)).isEqualTo("1000");
    }

    @Test
    @DisplayName("수익률 테스트 - 블랙잭 무승부시 배팅금액 반환")
    void calculateWinningMoney_WhenBlackJackDraw_GetBackBettingMoney() {
        User user = new User("user");
        Dealer dealer = new Dealer();

        user.addBettingMoney(new Money("1000"));
        user.addCard(Card.of("스페이드", "10"));
        user.addCard(Card.of("스페이드", "A"));
        dealer.addCard(Card.of("하트", "Q"));
        dealer.addCard(Card.of("하트", "A"));

        Results results = ResultGenerator.create(dealer, new Users(Arrays.asList(user)));
        Result userResult = results.getResults().get(new Name("user"));
        BigDecimal userWinningMoney = userResult.getWinningMoney();

        assertThat(userWinningMoney.setScale(0, RoundingMode.FLOOR)).isEqualTo("0");
    }

    @Test
    @DisplayName("수익률 테스트 - 무승부시 배팅금액 반환")
    void calculateWinningMoney_WhenDraw_GetBackBettingMoney() {
        User user = new User("user");
        Dealer dealer = new Dealer();

        user.addBettingMoney(new Money("1000"));
        user.addCard(Card.of("스페이드", "5"));
        user.addCard(Card.of("스페이드", "4"));
        dealer.addCard(Card.of("스페이드", "3"));
        dealer.addCard(Card.of("스페이드", "6"));

        Results results = ResultGenerator.create(dealer, new Users(Arrays.asList(user)));
        Result userResult = results.getResults().get(new Name("user"));
        BigDecimal userWinningMoney = userResult.getWinningMoney();

        assertThat(userWinningMoney.setScale(0, RoundingMode.FLOOR)).isEqualTo("0");
    }

    @Test
    @DisplayName("수익률 테스트 - 딜러가 블랙잭 패배시 1.5배 비용 지불")
    void calculateWinningMoney_WhenDraw_Pay150PercentMoney() {
        User user = new User("user");
        Dealer dealer = new Dealer();

        user.addBettingMoney(new Money("1000"));
        user.addCard(Card.of("스페이드", "A"));
        user.addCard(Card.of("스페이드", "K"));
        dealer.addCard(Card.of("스페이드", "3"));
        dealer.addCard(Card.of("스페이드", "10"));

        Results results = ResultGenerator.create(dealer, new Users(Arrays.asList(user)));
        Result dealerResult = results.getResults().get(new Name("딜러"));
        BigDecimal userWinningMoney = dealerResult.getWinningMoney();

        assertThat(userWinningMoney.setScale(0, RoundingMode.FLOOR)).isEqualTo("-1500");
    }

    @Test
    @DisplayName("수익률 테스트 - 패배시 1배 비용 지불")
    void calculateWinningMoney_WhenLose_Pay100PercentMoney() {
        User user = new User("user");
        Dealer dealer = new Dealer();

        user.addBettingMoney(new Money("1000"));
        user.addCard(Card.of("스페이드", "5"));
        user.addCard(Card.of("스페이드", "4"));
        dealer.addCard(Card.of("스페이드", "3"));
        dealer.addCard(Card.of("스페이드", "10"));

        Results results = ResultGenerator.create(dealer, new Users(Arrays.asList(user)));
        Result userResult = results.getResults().get(new Name("user"));
        BigDecimal userWinningMoney = userResult.getWinningMoney();

        assertThat(userWinningMoney.setScale(0, RoundingMode.FLOOR)).isEqualTo("-1000");
    }

}
