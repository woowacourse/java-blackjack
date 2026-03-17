package domain;

import domain.card.Cards;
import domain.game.WinningStatus;
import domain.participant.Dealer;
import domain.participant.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static domain.game.BlackjackRule.DEALER_NAME;
import static org.assertj.core.api.Assertions.assertThat;

class WinningStatusTest {
    Player pobi;
    Dealer dealer;

    @BeforeEach
    void setUp() {
        pobi = new Player("pobi");
        dealer = new Dealer(DEALER_NAME);
    }

    @Test
    void 플레이어의_카드의_합이_21을_초과하는_경우_플레이어가_패배한다() {
        Cards playerCards = CardFixture.twentyTwoCards();
        playerCards.cards().forEach(pobi::receive);

        Cards dealerCards = CardFixture.seventeenCards();
        dealerCards.cards().forEach(dealer::receive);

        WinningStatus status = WinningStatus.of(pobi, dealer);

        assertThat(status).isEqualTo(WinningStatus.LOSE);
    }

    @Test
    void 딜러의_카드의_합이_21을_초과하는_경우_플레이어가_승리한다() {
        Cards playerCards = CardFixture.seventeenCards();
        playerCards.cards().forEach(pobi::receive);

        Cards dealerCards = CardFixture.twentyTwoCards();
        dealerCards.cards().forEach(dealer::receive);

        WinningStatus status = WinningStatus.of(pobi, dealer);

        assertThat(status).isEqualTo(WinningStatus.WIN);
    }

    @Test
    void 플레이어가_처음_받은_카드_두_장의_합이_21이고_딜러는_아닐_경우_플레이어가_승리한다() {
        Cards playerCards = CardFixture.blackjackCards();
        playerCards.cards().forEach(pobi::receive);

        Cards dealerCards = CardFixture.seventeenCards();
        dealerCards.cards().forEach(dealer::receive);

        WinningStatus status = WinningStatus.of(pobi, dealer);

        assertThat(status).isEqualTo(WinningStatus.WIN);
    }

    @Test
    void 딜러가_처음_받은_카드_두_장의_합이_21이고_플레이어는_아닐_경우_딜러가_승리한다() {
        Cards playerCards = CardFixture.seventeenCards();
        playerCards.cards().forEach(pobi::receive);

        Cards dealerCards = CardFixture.blackjackCards();
        dealerCards.cards().forEach(dealer::receive);

        WinningStatus status = WinningStatus.of(pobi, dealer);

        assertThat(status).isEqualTo(WinningStatus.LOSE);
    }

    @Test
    void 딜러와_플레이어의_카드의_합이_동일한_경우_무승부로_처리한다() {
        Cards playerCards = CardFixture.seventeenCards();
        playerCards.cards().forEach(pobi::receive);

        Cards dealerCards = CardFixture.seventeenCards();
        dealerCards.cards().forEach(dealer::receive);

        WinningStatus status = WinningStatus.of(pobi, dealer);

        assertThat(status).isEqualTo(WinningStatus.TIE);
    }

    @Test
    void 딜러의_카드의_합이_플레이어의_카드의_합보다_큰_경우_플레이어가_패배한다() {
        Cards playerCards = CardFixture.fifteenCards();
        playerCards.cards().forEach(pobi::receive);

        Cards dealerCards = CardFixture.seventeenCards();
        dealerCards.cards().forEach(dealer::receive);

        WinningStatus status = WinningStatus.of(pobi, dealer);

        assertThat(status).isEqualTo(WinningStatus.LOSE);
    }

    @Test
    void 딜러의_카드의_합이_플레이어의_카드의_합보다_작을_경우_플레이어가_승리한다() {
        Cards playerCards = CardFixture.seventeenCards();
        playerCards.cards().forEach(pobi::receive);

        Cards dealerCards = CardFixture.fifteenCards();
        dealerCards.cards().forEach(dealer::receive);

        WinningStatus status = WinningStatus.of(pobi, dealer);

        assertThat(status).isEqualTo(WinningStatus.WIN);
    }
}
