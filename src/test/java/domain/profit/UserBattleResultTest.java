package domain.profit;

import static domain.fixture.BlackjackCardFixture.KING_HEART;
import static domain.fixture.BlackjackCardFixture.NINE_HEART;
import static domain.fixture.BlackjackCardFixture.SEVEN_HEART;
import static domain.fixture.BlackjackCardFixture.TEN_HEART;
import static domain.fixture.StateFixture.BLACKJACK_STATE;
import static domain.fixture.StateFixture.BUST_STATE;
import static domain.fixture.StateFixture.STAY_STATE;
import static domain.fixture.StateFixture.USER_HITTABLE_STATE;

import domain.BlackjackGameBoard;
import domain.card.Cards;
import domain.card.Deck;
import domain.player.Dealer;
import domain.player.User;
import domain.state.State;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class UserBattleResultTest {

    @Test
    void 유저가_버스트_상태고_딜러가_버스트_상태면_유저의_패배다() {
        // given
        State user = BUST_STATE;
        State dealer = BUST_STATE;

        // when
        UserBattleResult result = UserBattleResult.determineUserBattleResult(user, dealer);

        // then
        Assertions.assertThat(result).isEqualTo(UserBattleResult.LOSE);
    }

    @Test
    void 유저가_버스트_상태고_딜러가_블랙잭_상태면_유저의_패배다() {
        // given
        State user = BUST_STATE;
        State dealer = BLACKJACK_STATE;

        // when
        UserBattleResult result = UserBattleResult.determineUserBattleResult(user, dealer);

        // then
        Assertions.assertThat(result).isEqualTo(UserBattleResult.LOSE);
    }

    @Test
    void 유저가_버스트_상태고_딜러가_스테이_상태면_유저의_패배다() {
        // given
        State user = BUST_STATE;
        State dealer = STAY_STATE;

        // when
        UserBattleResult result = UserBattleResult.determineUserBattleResult(user, dealer);

        // then
        Assertions.assertThat(result).isEqualTo(UserBattleResult.LOSE);
    }

    @Test
    void 유저가_블랙잭_상태고_딜러가_버스트_상태면_유저의_블랙잭_승리다() {
        // given
        State user = BLACKJACK_STATE;
        State dealer = BUST_STATE;

        // when
        UserBattleResult result = UserBattleResult.determineUserBattleResult(user, dealer);

        // then
        Assertions.assertThat(result).isEqualTo(UserBattleResult.BLACKJACK_WIN);
    }

    @Test
    void 유저가_블랙잭_상태고_딜러가_블랙잭_상태면_무승부다() {
        // given
        State user = BLACKJACK_STATE;
        State dealer = BLACKJACK_STATE;

        // when
        UserBattleResult result = UserBattleResult.determineUserBattleResult(user, dealer);

        // then
        Assertions.assertThat(result).isEqualTo(UserBattleResult.DRAW);
    }

    @Test
    void 유저가_블랙잭_상태고_딜러가_스테이_상태면_유저의_블랙잭_승리다() {
        // given
        State user = BLACKJACK_STATE;
        State dealer = STAY_STATE;

        // when
        UserBattleResult result = UserBattleResult.determineUserBattleResult(user, dealer);

        // then
        Assertions.assertThat(result).isEqualTo(UserBattleResult.BLACKJACK_WIN);
    }

    @Test
    void 유저가_스테이_상태고_딜러가_버스트_상태면_유저의_일반_승리다() {
        // given
        State user = STAY_STATE;
        State dealer = BUST_STATE;

        // when
        UserBattleResult result = UserBattleResult.determineUserBattleResult(user, dealer);

        // then
        Assertions.assertThat(result).isEqualTo(UserBattleResult.NORMAL_WIN);
    }

    @Test
    void 유저가_스테이_상태고_딜러가_블랙잭_상태면_유저의_패배다() {
        // given
        State user = STAY_STATE;
        State dealer = BLACKJACK_STATE;

        // when
        UserBattleResult result = UserBattleResult.determineUserBattleResult(user, dealer);

        // then
        Assertions.assertThat(result).isEqualTo(UserBattleResult.LOSE);
    }

    @Test
    void 유저가_스테이_상태고_딜러가_스테이_상태면_카드_점수의_합으로_승부를_결정한다() {
        // given
        Deck deck = new Deck(List.of(
                TEN_HEART(), NINE_HEART(),    // user
                KING_HEART(), SEVEN_HEART()  // dealer
        ));
        Dealer dealer = Dealer.createDefaultDealer();
        User user = User.of("수양", 2000);

        BlackjackGameBoard gameBoard = new BlackjackGameBoard(deck);
        gameBoard.distributeInitialCards(dealer);
        gameBoard.distributeInitialCards(user);

        Function<User, Boolean> alwaysNo = u -> false;
        BiConsumer<String, Cards> userOnnHit = (u, c) -> {
        };
        Runnable dealerOnHit = () -> {
        };
        gameBoard.hitUntilStay(user, alwaysNo, userOnnHit);
        gameBoard.hitUntilNotHittable(dealer, dealerOnHit);

        // when
        UserBattleResult result = UserBattleResult.determineUserBattleResult(user.getState(), dealer.getState());

        // then
        Assertions.assertThat(result).isEqualTo(UserBattleResult.NORMAL_WIN);
    }

    @Test
    void 유저_혹은_딜러가_종료_상태가_아닌데_승부_계산을_하려고_하면_예외가_발생한다() {
        // given
        State user = USER_HITTABLE_STATE;
        State dealer = BLACKJACK_STATE;

        // when & then
        Assertions.assertThatThrownBy(() -> UserBattleResult.determineUserBattleResult(user, dealer))
                .isInstanceOf(IllegalStateException.class);
    }
}