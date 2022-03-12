package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameMachineTest {

    private GameMachine gameMachine = new GameMachine(CardDeckGenerator.createCardDeckByCardNumber());

    @Test
    @DisplayName("유저들을 생성을 확인한다.")
    void createUsers() {
        final List<String> users = Arrays.asList("a", "b", "c", "d", "e", "f", "g");
        final int expected = 7;

        final int actual = gameMachine.createUsers(users).size();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("유저 생성 인원이 7명이 넘을 시 에러를 확인한다.")
    void createMoreThanEightUsers() {
        final List<String> users = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h");
        assertThatThrownBy(() ->
                gameMachine.createUsers(users))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("게임에 참여할 수 있는 유저는 최대 7명입니다.");
    }

    @Test
    @DisplayName("딜러의 점수가 16이하일 때 딜러가 카드를 받는지 확인한다.")
    void checkDealerReceiveCard() {
        final Dealer dealer = new Dealer(new ArrayList<>(Arrays.asList(new Card(CardPattern.DIAMOND, CardNumber.KING),
                new Card(CardPattern.HEART, CardNumber.SIX))));
        final boolean expected = true;

        final boolean actual = gameMachine.checkPlayerReceiveCard(dealer);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("딜러의 점수가 16 초과일 때 카드를 받지 않는지 확인한다.")
    void checkDealerNotReceiveCard() {
        final Dealer dealer = new Dealer(new ArrayList<>(Arrays.asList(new Card(CardPattern.DIAMOND, CardNumber.KING),
                new Card(CardPattern.HEART, CardNumber.SEVEN))));
        final boolean expected = false;

        final boolean actual = gameMachine.checkPlayerReceiveCard(dealer);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("블랙잭인 플레이어가 있는지 확인한다.")
    void haveBlackJack() {
        final Dealer dealer = new Dealer(new ArrayList<>(Arrays.asList(new Card(CardPattern.DIAMOND, CardNumber.KING),
                new Card(CardPattern.HEART, CardNumber.SEVEN))));
        final List<User> users = Arrays.asList(
                new User("pobi", new ArrayList<>(Arrays.asList(new Card(CardPattern.DIAMOND, CardNumber.KING),
                        new Card(CardPattern.HEART, CardNumber.ACE)))),
                new User("jun", new ArrayList<>(Arrays.asList(new Card(CardPattern.DIAMOND, CardNumber.KING),
                        new Card(CardPattern.HEART, CardNumber.SEVEN)))));
        final boolean expected = true;

        final boolean actual = gameMachine.haveBlackJack(users, dealer);
        assertThat(actual).isEqualTo(expected);
    }
}
