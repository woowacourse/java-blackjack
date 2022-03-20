package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ResultTest {

    @Test
    @DisplayName("유저와 딜러 모두 21점이 넘는 경우 유저는 패배한다.")
    void dealerAndUserExceedTest() {
        Participant user = new User("Kim");
        Participant dealer = new Dealer();

        user.receiveCard(Card.of(CardNumber.KING, CardType.SPADE));
        user.receiveCard(Card.of(CardNumber.KING, CardType.CLOVER));
        user.receiveCard(Card.of(CardNumber.KING, CardType.HEART));

        dealer.receiveCard(Card.of(CardNumber.QUEEN, CardType.SPADE));
        dealer.receiveCard(Card.of(CardNumber.QUEEN, CardType.CLOVER));
        dealer.receiveCard(Card.of(CardNumber.QUEEN, CardType.HEART));

        assertThat(UserResult.checkUserResult(user, dealer)).isEqualTo(UserResult.LOSE);
    }

    @Test
    @DisplayName("딜러가 21점이 넘고 유저는 넘지 않는 경우 유저는 승리한다.")
    void dealerExceedTest() {
        Participant user = new User("Kim");
        Participant dealer = new Dealer();

        user.receiveCard(Card.of(CardNumber.KING, CardType.SPADE));
        user.receiveCard(Card.of(CardNumber.KING, CardType.CLOVER));

        dealer.receiveCard(Card.of(CardNumber.QUEEN, CardType.SPADE));
        dealer.receiveCard(Card.of(CardNumber.QUEEN, CardType.CLOVER));
        dealer.receiveCard(Card.of(CardNumber.QUEEN, CardType.HEART));

        assertThat(UserResult.checkUserResult(user, dealer)).isEqualTo(UserResult.WIN);
    }

    @Test
    @DisplayName("딜러가 21점이 넘지 않고 유저가 21을 넘는 경우 유저는 패배한다.")
    void userExceedTest() {
        Participant user = new User("Kim");
        Participant dealer = new Dealer();

        user.receiveCard(Card.of(CardNumber.KING, CardType.SPADE));
        user.receiveCard(Card.of(CardNumber.KING, CardType.CLOVER));
        user.receiveCard(Card.of(CardNumber.KING, CardType.HEART));

        dealer.receiveCard(Card.of(CardNumber.QUEEN, CardType.CLOVER));
        dealer.receiveCard(Card.of(CardNumber.QUEEN, CardType.HEART));

        assertThat(UserResult.checkUserResult(user, dealer)).isEqualTo(UserResult.LOSE);
    }

    @Test
    @DisplayName("딜러가 21점이 넘지 않고 유저가 딜러 점수보다 낮은 경우 유저는 패배한다.")
    void userScoreLoseTest() {
        Participant user = new User("Kim");
        Participant dealer = new Dealer();

        user.receiveCard(Card.of(CardNumber.KING, CardType.SPADE));
        user.receiveCard(Card.of(CardNumber.EIGHT, CardType.CLOVER));

        dealer.receiveCard(Card.of(CardNumber.QUEEN, CardType.CLOVER));
        dealer.receiveCard(Card.of(CardNumber.QUEEN, CardType.HEART));
        assertThat(UserResult.checkUserResult(user, dealer)).isEqualTo(UserResult.LOSE);
    }

    @Test
    @DisplayName("딜러가 21점이 넘지 않고 유저가 딜러 점수보다 높은 경우 유저는 승리한다.")
    void userScoreWinTest() {
        Participant user = new User("Kim");
        Participant dealer = new Dealer();

        user.receiveCard(Card.of(CardNumber.KING, CardType.SPADE));
        user.receiveCard(Card.of(CardNumber.EIGHT, CardType.CLOVER));

        dealer.receiveCard(Card.of(CardNumber.QUEEN, CardType.CLOVER));
        dealer.receiveCard(Card.of(CardNumber.TWO, CardType.HEART));

        assertThat(UserResult.checkUserResult(user, dealer)).isEqualTo(UserResult.WIN);
    }

    @Test
    @DisplayName("딜러가 21점이 넘지 않고 유저가 딜러 점수와 같은 경우 무승부이다.")
    void userScoreDrawTest() {
        Participant user = new User("Kim");
        Participant dealer = new Dealer();

        user.receiveCard(Card.of(CardNumber.KING, CardType.SPADE));
        user.receiveCard(Card.of(CardNumber.EIGHT, CardType.CLOVER));

        dealer.receiveCard(Card.of(CardNumber.KING, CardType.CLOVER));
        dealer.receiveCard(Card.of(CardNumber.EIGHT, CardType.HEART));

        assertThat(UserResult.checkUserResult(user, dealer)).isEqualTo(UserResult.DRAW);
    }

    @Test
    @DisplayName("유저와 딜러가 같은 21이면서, 딜러가 블랙잭인 경우 딜러가 승리한다.")
    void blackjackDealerTest() {
        Participant user = new User("Kim");
        Participant dealer = new Dealer();

        user.receiveCard(Card.of(CardNumber.KING, CardType.SPADE));
        user.receiveCard(Card.of(CardNumber.EIGHT, CardType.CLOVER));
        user.receiveCard(Card.of(CardNumber.THREE, CardType.HEART));

        dealer.receiveCard(Card.of(CardNumber.ACE, CardType.CLOVER));
        dealer.receiveCard(Card.of(CardNumber.TEN, CardType.HEART));

        assertThat(UserResult.checkUserResult(user, dealer)).isEqualTo(UserResult.LOSE);
    }

    @Test
    @DisplayName("유저와 딜러가 같은 21이면서, 유저가 블랙잭인 경우 유저가 승리한다.")
    void blackjackUserTest() {
        Participant user = new User("Kim");
        Participant dealer = new Dealer();

        user.receiveCard(Card.of(CardNumber.KING, CardType.SPADE));
        user.receiveCard(Card.of(CardNumber.ACE, CardType.CLOVER));

        dealer.receiveCard(Card.of(CardNumber.QUEEN, CardType.CLOVER));
        dealer.receiveCard(Card.of(CardNumber.TEN, CardType.HEART));
        dealer.receiveCard(Card.of(CardNumber.ACE, CardType.SPADE));

        assertThat(UserResult.checkUserResult(user, dealer)).isEqualTo(UserResult.WIN);
    }


    @Test
    @DisplayName("유저와 딜러가 같은 블랙잭이라면 무승부이다.")
    void blackjackDrawTest() {
        Participant user = new User("Kim");
        Participant dealer = new Dealer();

        user.receiveCard(Card.of(CardNumber.KING, CardType.SPADE));
        user.receiveCard(Card.of(CardNumber.ACE, CardType.CLOVER));

        dealer.receiveCard(Card.of(CardNumber.QUEEN, CardType.CLOVER));
        dealer.receiveCard(Card.of(CardNumber.ACE, CardType.HEART));

        assertThat(UserResult.checkUserResult(user, dealer)).isEqualTo(UserResult.DRAW);
    }

    @Test
    @DisplayName("입력으로 Lose를 받을 때 Win이 나온다.")
    void swapLoseToWinTest() {
        assertThat(UserResult.swap(UserResult.LOSE)).isEqualTo(UserResult.WIN);
    }

    @Test
    @DisplayName("입력으로 Win를 받을 때 Lose가 나온다.")
    void swapWinToLoseTest() {
        assertThat(UserResult.swap(UserResult.WIN)).isEqualTo(UserResult.LOSE);
    }

    @Test
    @DisplayName("입력으로 DRAW를 받을 때 DRAW이 나온다.")
    void swapDrawToDrawTest() {
        assertThat(UserResult.swap(UserResult.DRAW)).isEqualTo(UserResult.DRAW);
    }
}
