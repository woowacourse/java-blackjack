package domain.result;


import static org.assertj.core.api.Assertions.assertThat;
import static result.GameStatus.*;

import card.Card;
import card.CardNumberType;
import card.CardType;
import card.Hand;
import java.util.List;
import participant.Money;
import participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import result.PlayerResult;

class PlayerResultTest {
    Hand blackJack = new Hand(List.of(
            new Card(CardNumberType.JACK, CardType.CLOVER),
            new Card(CardNumberType.ACE, CardType.CLOVER)));
    Player blackjackPlayer = new Player("blackJack", blackJack, Money.bet(10000));
    Player winner = new Player("winner", Hand.createEmpty(), Money.bet(10000));
    Player drawer = new Player("drawer", Hand.createEmpty(), Money.bet(20000));
    Player loser = new Player("loser", Hand.createEmpty(), Money.bet(30000));

//  - [ ] 플레이어는 블랙잭으로 승리할 경우 베팅 금액의 1.5배를 얻는다
//  - [ ] 플레이어는 승리할 경우 베팅 금액을 얻는다
//  - [ ] 플레이어는 무승부일 경우 베팅 금액을 돌려받는다
//  - [ ] 플레이어는 패배할 경우 베팅 금액을 잃는다
//  - [ ] 딜러의 수익은 모든 플레이어들의 수익 합의 반대 값이다

    @DisplayName("플레이어는 블랙잭으로 승리할 경우 베팅 금액의 1.5배를 얻는다")
    @Test
    void test10() {
        //given
        PlayerResult playerResult = new PlayerResult(blackjackPlayer, WIN);

        //when
        Money profit = playerResult.calculateProfit();

        //then
        assertThat(profit).isEqualTo(new Money(15000));
    }
}
