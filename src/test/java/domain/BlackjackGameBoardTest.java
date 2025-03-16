package domain;

import static domain.fixture.BlackjackCardFixture.ACE_HEART;
import static domain.fixture.BlackjackCardFixture.JACK_HEART;
import static domain.fixture.BlackjackCardFixture.KING_HEART;
import static domain.fixture.BlackjackCardFixture.NINE_HEART;
import static domain.fixture.BlackjackCardFixture.TEN_HEART;
import static domain.fixture.BlackjackCardFixture.TWO_HEART;

import domain.card.Cards;
import domain.card.Deck;
import domain.player.Dealer;
import domain.player.User;
import domain.player.Users;
import domain.profit.DefaultProfitStrategy;
import domain.profit.Profit;
import domain.state.Stay;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class BlackjackGameBoardTest {

    @Test
    void 초기_카드_분배_시_유저는_2장을_받는다() {
        // given
        Deck deck = new Deck(List.of(
                JACK_HEART(), KING_HEART(), TEN_HEART(),
                TWO_HEART(), ACE_HEART()    // initial cards
        ));
        User user = User.of("새로이", 1000);
        BlackjackGameBoard gameBoard = new BlackjackGameBoard(deck);

        // when
        gameBoard.distributeInitialCards(user);

        // when & then
        Assertions.assertThat(user.cards().size())
                .isEqualTo(2);
    }


    @Test
    void 초기_카드_분배_시_딜러는_2장을_받는다() {
        // given
        Deck deck = new Deck(List.of(
                JACK_HEART(), KING_HEART(), TEN_HEART(),
                TWO_HEART(), ACE_HEART()    // initial cards
        ));
        Dealer dealer = Dealer.createDefaultDealer();
        BlackjackGameBoard gameBoard = new BlackjackGameBoard(deck);

        // when
        gameBoard.distributeInitialCards(dealer);

        // when & then
        Assertions.assertThat(dealer.cards().size())
                .isEqualTo(2);
    }

    @Test
    void 초기_카드_공개_시_유저는_카드를_2장_공개한다() {
        // given
        Deck deck = new Deck(List.of(
                JACK_HEART(), KING_HEART(), TEN_HEART(),
                TWO_HEART(), ACE_HEART()    // initial cards
        ));
        User user = User.of("라젤", 2000);
        BlackjackGameBoard gameBoard = new BlackjackGameBoard(deck);
        gameBoard.distributeInitialCards(user);

        // when
        gameBoard.openInitialCards(user);

        // when & then
        Assertions.assertThat(user.openedCards().size())
                .isEqualTo(2);
    }

    @Test
    void 초기_카드_공개_시_딜러는_카드를_1장_공개한다() {
        // given
        Deck deck = new Deck(List.of(
                JACK_HEART(), KING_HEART(), TEN_HEART(),
                TWO_HEART(), ACE_HEART()    // initial cards
        ));
        Dealer dealer = Dealer.createDefaultDealer();
        BlackjackGameBoard gameBoard = new BlackjackGameBoard(deck);
        gameBoard.distributeInitialCards(dealer);

        // when
        gameBoard.openInitialCards(dealer);

        // when & then
        Assertions.assertThat(dealer.openedCards().size())
                .isEqualTo(1);
    }

    @Test
    void 유저가_버스트가_아닌_상태에서_스테이를_선언하면_스테이_상태가_된다() {
        // given
        Deck deck = new Deck(List.of(
                JACK_HEART(), KING_HEART(), TEN_HEART(),
                TWO_HEART(), ACE_HEART()    // initial cards
        ));
        User user = User.of("라젤", 2000);
        BlackjackGameBoard gameBoard = new BlackjackGameBoard(deck);
        gameBoard.distributeInitialCards(user);
        gameBoard.openInitialCards(user);

        Function<User, Boolean> alwaysNo = u -> false;
        BiConsumer<User, Cards> onHit = (u, c) -> {
        };

        // when
        gameBoard.hitUntilStay(user, alwaysNo, onHit);

        // then
        Assertions.assertThat(user.getState()).isInstanceOf(Stay.class);
    }

    @Test
    void 딜러는_카드합이_16이하인_동안_카드를_계속_뽑아야_한다() {
        // given
        Deck deck = new Deck(List.of(
                JACK_HEART(), KING_HEART(), TEN_HEART(),
                TWO_HEART(), ACE_HEART()    // initial cards
        ));
        Dealer dealer = Dealer.createDefaultDealer();
        BlackjackGameBoard gameBoard = new BlackjackGameBoard(deck);
        gameBoard.distributeInitialCards(dealer);
        gameBoard.openInitialCards(dealer);
        Runnable onHit = () -> {
        };

        // when
        gameBoard.hitUntilUnder16(dealer, onHit);

        // then
        Assertions.assertThat(dealer.computeOptimalSum()).isGreaterThan(16);
    }

    @Test
    void 딜러의_수익을_계산한다() {
        // given
        Deck deck = new Deck(List.of(
                ACE_HEART(), TEN_HEART(),   // 앤지: 블랙잭
                NINE_HEART(), TEN_HEART(),  // 헤일러: 19
                KING_HEART(), TEN_HEART()    // dealer: 20
        ));
        Dealer dealer = Dealer.createDefaultDealer();
        Users users = Users.from(Map.ofEntries(
                Map.entry("헤일러", 1000),
                Map.entry("앤지", 100000)
        ));
        BlackjackGameBoard gameBoard = new BlackjackGameBoard(deck);

        gameBoard.distributeInitialCards(dealer);
        for (User user : users.getUsers()) {
            gameBoard.distributeInitialCards(user);
        }

        // when
        Profit profit = gameBoard.computeDealerProfit(dealer, users, DefaultProfitStrategy.getInstance());

        // then
        Assertions.assertThat(profit.getValue()).isEqualTo(1000 - (int) (100000 * 1.5));
    }
}