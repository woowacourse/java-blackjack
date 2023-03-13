package blackjack.domain.participant;

import static blackjack.domain.card.Denomination.ACE;
import static blackjack.domain.card.Denomination.TWO;
import static blackjack.domain.card.Suit.HEART;
import static blackjack.domain.card.Suit.SPADE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.money.Money;
import blackjack.domain.card.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

class DealerTest {

    private Dealer dealer;

    @BeforeEach
    void setUp() {
        this.dealer = new Dealer();
    }

    @Test
    @DisplayName("Bank에 플레이어의 배팅 금액을 저장한다.")
    void saveBettingMoney() {
        final Player player = new Player("player1");
        final Money money = new Money(1000);

        dealer.saveBettingMoney(player, money);

        assertThat(dealer.getBank().findMoney(player)).isEqualTo(new Money(1000));
    }

    @Test
    @DisplayName("플레이어와 본인에게 2장의 카드씩 나눠준다.")
    void settingCardsTest() {
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        Players players = new Players(List.of(player1, player2));

        assertThatNoException().isThrownBy(
                () -> dealer.settingCards(players)
        );
    }

    @Test
    @DisplayName("스스로 2장의 카드를 세팅한다.")
    void settingSelfTest() {
        dealer.settingSelf();

        assertThat(dealer.getHand().getHand().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("카드가 존재하지 않는다면 예외가 발생한다.")
    void showOneCardTest_no_card() {
        assertThatIllegalStateException().isThrownBy(
                () -> dealer.showOneCard()
        ).withMessage("딜러의 손에는 카드가 존재하지 않습니다.");
    }
    
    @Test
    @DisplayName("한 장의 카드를 반환한다.")
    void showOneCardTest() {
        dealer.receiveCard(new Card(ACE, HEART));

        assertThat(dealer.showOneCard()).isEqualTo(new Card(ACE, HEART));
    }

    @Test
    @DisplayName("16점 이하라면 16점 초과할 때까지 카드를 뽑는다.")
    void drawIfLowerOrEquals16() {
        dealer.receiveCard(new Card(TWO, HEART));
        dealer.receiveCard(new Card(TWO, SPADE));

        dealer.drawIfLowerOrEquals16();

        assertAll(
                () -> assertThat(dealer.totalScore()).isGreaterThan(16),
                () -> assertThat(dealer.getHand().getHand().size()).isNotEqualTo(2)
        );
    }
}
