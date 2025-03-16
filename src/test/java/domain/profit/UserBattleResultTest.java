package domain.profit;

import static domain.fixture.BlackjackCardFixture.ACE_HEART;
import static domain.fixture.BlackjackCardFixture.FIVE_HEART;
import static domain.fixture.BlackjackCardFixture.JACK_HEART;
import static domain.fixture.BlackjackCardFixture.KING_HEART;
import static domain.fixture.BlackjackCardFixture.NINE_HEART;
import static domain.fixture.BlackjackCardFixture.SEVEN_HEART;
import static domain.fixture.BlackjackCardFixture.SIX_HEART;
import static domain.fixture.BlackjackCardFixture.TEN_HEART;

import domain.BlackjackGameBoard;
import domain.card.Cards;
import domain.card.Deck;
import domain.player.Dealer;
import domain.player.User;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class UserBattleResultTest {

    @Test
    void 유저가_블랙잭이고_딜러가_블랙잭이면_비긴다() {
        // given
        Deck deck = new Deck(List.of(
                KING_HEART(), ACE_HEART(),  // user
                TEN_HEART(), ACE_HEART()    // dealer
        ));
        Dealer dealer = Dealer.createDefaultDealer();
        User user = User.of("부기", 2000);

        BlackjackGameBoard gameBoard = new BlackjackGameBoard(deck);
        gameBoard.distributeInitialCards(dealer);
        gameBoard.distributeInitialCards(user);

        // when
        UserBattleResult result = UserBattleResult.compare(user.getState(), dealer.getState());

        // then
        Assertions.assertThat(result).isEqualTo(UserBattleResult.DRAW);
    }

    @Test
    void 유저가_블랙잭이고_딜러가_블랙잭이_아니면_유저의_블랙잭_승리다() {
        // given
        Deck deck = new Deck(List.of(
                FIVE_HEART(),
                KING_HEART(), ACE_HEART(),  // user
                TEN_HEART(), SIX_HEART()    // dealer
        ));
        Dealer dealer = Dealer.createDefaultDealer();
        User user = User.of("칼리", 2000);

        BlackjackGameBoard gameBoard = new BlackjackGameBoard(deck);
        gameBoard.distributeInitialCards(dealer);
        gameBoard.distributeInitialCards(user);
        Runnable onHit = () -> {
        };
        gameBoard.hitUntilUnder16(dealer, onHit);

        // when
        UserBattleResult result = UserBattleResult.compare(user.getState(), dealer.getState());

        // then
        Assertions.assertThat(result).isEqualTo(UserBattleResult.BLACKJACK_WIN);
    }

    @Test
    void 유저의_카드합이_딜러보다_높고_블랙잭_상태가_아니면_유저의_일반_승리다() {
        // given
        Deck deck = new Deck(List.of(
                KING_HEART(), TEN_HEART(),  // user
                TEN_HEART(), SEVEN_HEART()    // dealer
        ));
        Dealer dealer = Dealer.createDefaultDealer();
        User user = User.of("포스티", 2000);

        BlackjackGameBoard gameBoard = new BlackjackGameBoard(deck);
        gameBoard.distributeInitialCards(dealer);
        gameBoard.distributeInitialCards(user);

        // when
        UserBattleResult result = UserBattleResult.compare(user.getState(), dealer.getState());

        // then
        Assertions.assertThat(result).isEqualTo(UserBattleResult.NORMAL_WIN);
    }

    @Test
    void 유저의_카드합이_딜러의_카드합보다_낮으면_유저의_패배다() {
        // given
        Deck deck = new Deck(List.of(
                TEN_HEART(), NINE_HEART(),    // user
                KING_HEART(), JACK_HEART()  // dealer
        ));
        Dealer dealer = Dealer.createDefaultDealer();
        User user = User.of("부기", 2000);

        BlackjackGameBoard gameBoard = new BlackjackGameBoard(deck);
        gameBoard.distributeInitialCards(dealer);
        gameBoard.distributeInitialCards(user);

        // when
        UserBattleResult result = UserBattleResult.compare(user.getState(), dealer.getState());

        // then
        Assertions.assertThat(result).isEqualTo(UserBattleResult.LOSE);
    }

    @Test
    void 유저가_버스트_상태고_딜러가_버스트_상태면_유저의_패배다() {
        // given
        Deck deck = new Deck(List.of(
                TEN_HEART(), TEN_HEART(),
                TEN_HEART(), NINE_HEART(),    // user
                KING_HEART(), JACK_HEART()  // dealer
        ));
        Dealer dealer = Dealer.createDefaultDealer();
        User user = User.of("부기", 2000);

        BlackjackGameBoard gameBoard = new BlackjackGameBoard(deck);
        gameBoard.distributeInitialCards(dealer);
        gameBoard.distributeInitialCards(user);

        Function<User, Boolean> alwaysYes = u -> true;
        BiConsumer<User, Cards> userOnnHit = (u, c) -> {
        };
        Runnable dealerOnHit = () -> {
        };
        gameBoard.hitUntilStay(user, alwaysYes, userOnnHit);
        gameBoard.hitUntilUnder16(dealer, dealerOnHit);

        // when
        UserBattleResult result = UserBattleResult.compare(user.getState(), dealer.getState());

        // then
        Assertions.assertThat(result).isEqualTo(UserBattleResult.LOSE);
    }
}