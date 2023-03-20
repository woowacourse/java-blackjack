package domain.result;

import domain.card.Card;
import domain.card.CardType;
import domain.card.CardValue;
import domain.card.Cards;
import domain.participant.Money;
import domain.participant.Name;
import domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ProfitTest {

    @Test
    @DisplayName("수익을 입력하면 Profit 객체가 정상적으로 생성된다")
    void generateProfit() {
        Name name = new Name("roy");
        Cards cards = new Cards(List.of(new Card(CardType.SPADE, CardValue.ACE), new Card(CardType.HEART, CardValue.KING)));
        Money money = new Money(1000);
        Player player = new Player(name, cards, money);

        assertDoesNotThrow(() -> Profit.winnerProfit(player));
    }

    @Test
    @DisplayName("플레이가 게임에서 이기면 베팅한 금액만큼 수익을 얻는다")
    void makeProfitOfBettingMoney() {
        Name name = new Name("roy");
        Cards cards = new Cards(List.of(new Card(CardType.SPADE, CardValue.ACE), new Card(CardType.HEART, CardValue.TWO)));
        Money money = new Money(1000);
        Player player = new Player(name, cards, money);
        double expectedWinningMoney = 1000;

        Profit winnerProfit = Profit.winnerProfit(player);

        assertThat(winnerProfit.getProfit()).isEqualTo(expectedWinningMoney);
    }

    @Test
    @DisplayName("플레이가 블랙잭일 경우 게임에서 이기면 베팅한 금액의 1.5배를 수익으로 얻는다")
    void makeProfitOfBlackJackMoney() {
        Name name = new Name("roy");
        Cards cards = new Cards(List.of(new Card(CardType.SPADE, CardValue.ACE), new Card(CardType.HEART, CardValue.KING)));
        Money money = new Money(1000);
        Player player = new Player(name, cards, money);
        double expectedWinningMoney = 1500;

        Profit winnerProfit = Profit.winnerProfit(player);

        assertThat(winnerProfit.getProfit()).isEqualTo(expectedWinningMoney);
    }

    @Test
    @DisplayName("플레이어가 게임에서 패배할 경우 베팅한 금액만큼 손실을 입는다.")
    void makeLossOfBettingMoney() {
        Name name = new Name("roy");
        Cards cards = new Cards(List.of(new Card(CardType.SPADE, CardValue.ACE), new Card(CardType.HEART, CardValue.TWO)));
        Money money = new Money(1000);
        Player player = new Player(name, cards, money);
        double expectedLoss = -1000;

        Profit loserProfit = Profit.looserProfit(player);

        assertThat(loserProfit.getProfit()).isEqualTo(expectedLoss);
    }

    @Test
    @DisplayName("무승부일 경우 플레이어는 아무런 수익을 얻지 못한다.")
    void makeNonProfitWhenGameEndedInTie() {
        double expectedProfit = 0;

        Profit tieProfit = Profit.tieProfit();

        assertThat(tieProfit.getProfit()).isEqualTo(expectedProfit);
    }

    @Test
    @DisplayName("플레이어들의 수익 합계의 반대의 금액만큼 딜러의 수익이 생성된다")
    void makeDealerProfit() {
        double playersProfit = 3000;
        double dealerProfit = -playersProfit;

        assertDoesNotThrow(() -> Profit.dealerProfit(dealerProfit));
    }
}
