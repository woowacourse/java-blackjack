package domain.board;

import domain.PlayerStatus;
import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import domain.user.Dealer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class DealerBoardTest {

    @DisplayName("딜러와 딜러의 점수를 가진 딜러현황판을 생성한다.")
    @Test
    void create() {
        Dealer dealer = new Dealer();
        DealerBoard dealerBoard = new DealerBoard(dealer, PlayerStatus.HIT_ABLE);
        Assertions.assertThat(dealerBoard.getDealer()).isEqualTo(dealer);
        Assertions.assertThat(dealerBoard.getPoint()).isEqualTo(0);
    }

    @DisplayName("딜러의 점수를 업데이트한다.")
    @Nested
    class updatedealerBoardByPlayerTurnAction {

        @DisplayName("점수가 21보다 낮을 때")
        @Test
        void updateUnderBlackJack() {
            Dealer dealer = new Dealer();
            DealerBoard dealerBoard = new DealerBoard(dealer, PlayerStatus.HIT_ABLE);
            dealerBoard.update();
            Assertions.assertThat(dealerBoard).extracting("status").isEqualTo(PlayerStatus.HIT_ABLE);
        }

        @DisplayName("카드를 받고 점수가 21일 때")
        @Test
        void updateBlackJack() {
            Dealer dealer = new Dealer();
            DealerBoard dealerBoard = new DealerBoard(dealer, PlayerStatus.HIT_ABLE);
            dealer.dealt(new Card(Denomination.JACK, Suit.DIAMOND));
            dealer.dealt(new Card(Denomination.ACE, Suit.DIAMOND));
            dealerBoard.update();
            Assertions.assertThat(dealerBoard).extracting("status").isEqualTo(PlayerStatus.BLACK_JACK);
        }

        @DisplayName("카드를 받고 점수가 21보다 클 때")
        @Test
        void updateBust() {
            Dealer dealer = new Dealer();
            DealerBoard dealerBoard = new DealerBoard(dealer, PlayerStatus.HIT_ABLE);
            dealer.dealt(new Card(Denomination.JACK, Suit.DIAMOND));
            dealer.dealt(new Card(Denomination.JACK, Suit.DIAMOND));
            dealer.dealt(new Card(Denomination.JACK, Suit.DIAMOND));
            dealerBoard.update();
            Assertions.assertThat(dealerBoard).extracting("status").isEqualTo(PlayerStatus.BUST);
        }
    }

    @DisplayName("딜러의 점수가 16점 이하인지 확인한다.")
    @Nested
    class NeedMoreCard {

        @DisplayName("점수가 16이하 일 때")
        @Test
        void underSixteenOrSixteen() {
            Dealer dealer = new Dealer();
            DealerBoard dealerBoard = new DealerBoard(dealer, PlayerStatus.HIT_ABLE);
            dealer.dealt(new Card(Denomination.JACK, Suit.DIAMOND));
            dealer.dealt(new Card(Denomination.SIX, Suit.DIAMOND));
            dealerBoard.update();
            Assertions.assertThat(dealerBoard.needMoreCard()).isTrue();
        }

        @DisplayName("점수가 16초과 일 때")
        @Test
        void overSixTeen() {
            Dealer dealer = new Dealer();
            DealerBoard dealerBoard = new DealerBoard(dealer, PlayerStatus.HIT_ABLE);
            dealer.dealt(new Card(Denomination.JACK, Suit.DIAMOND));
            dealer.dealt(new Card(Denomination.JACK, Suit.DIAMOND));
            dealerBoard.update();
            Assertions.assertThat(dealerBoard.needMoreCard()).isFalse();
        }
    }
}
