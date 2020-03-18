package domain;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardSuitSymbol;
import domain.player.Dealer;
import domain.player.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class ProfitTest {
    @DisplayName("유저와 딜러 둘 다 블랙잭인 경우 배팅금액을 돌려받는지 테스트")
    @Test
    void getBlackJackProfitTest() {
        Player player = new Player("pobi", new ArrayList<>(Arrays.asList(
                new Card(CardNumber.ACE, CardSuitSymbol.CLUB),
                new Card(CardNumber.KING, CardSuitSymbol.CLUB)
        )), 10_000d
        );
        Dealer dealer = new Dealer(new ArrayList<>(Arrays.asList(
                new Card(CardNumber.ACE, CardSuitSymbol.DIAMOND),
                new Card(CardNumber.KING, CardSuitSymbol.DIAMOND)
        ))
        );

        Assertions.assertThat(Profit.calculateProfit(player, dealer)).isEqualTo(0);
    }

    @DisplayName("플레이어만 블랙잭일 때 갖고있는 돈의 1.5배 반환 테스트")
    @Test
    void onlyPlayerBlackJackProfitTest() {
        Player player = new Player("pobi", new ArrayList<>(Arrays.asList(
                new Card(CardNumber.ACE, CardSuitSymbol.CLUB),
                new Card(CardNumber.KING, CardSuitSymbol.CLUB)
        )), 10_000d);
        Dealer dealer = new Dealer(new ArrayList<>(Arrays.asList(
                new Card(CardNumber.THREE, CardSuitSymbol.DIAMOND),
                new Card(CardNumber.KING, CardSuitSymbol.DIAMOND)
        )));

        Assertions.assertThat(Profit.calculateProfit(player, dealer)).isEqualTo(15_000);
    }

    @DisplayName("플레이어와 딜러 둘 다 블랙잭이 아니고 플레이어가 이긴 경우 테스트")
    @Test
    void playerWinProfitTest() {
        Player player = new Player("pobi", new ArrayList<>(Arrays.asList(
                new Card(CardNumber.THREE, CardSuitSymbol.CLUB),
                new Card(CardNumber.KING, CardSuitSymbol.CLUB),
                new Card(CardNumber.FOUR, CardSuitSymbol.CLUB)
        )), 10_000d);
        Dealer dealer = new Dealer(new ArrayList<>(Arrays.asList(
                new Card(CardNumber.THREE, CardSuitSymbol.DIAMOND),
                new Card(CardNumber.KING, CardSuitSymbol.DIAMOND)
        )));

        Assertions.assertThat(Profit.calculateProfit(player, dealer)).isEqualTo(10_000);
    }

    @DisplayName("플레이어와 딜러 둘 다 블랙잭이 아니고 플레이어가 진 경우 테스트")
    @Test
    void playerLooseProfitTest() {
        Player player = new Player("pobi", new ArrayList<>(Arrays.asList(
                new Card(CardNumber.TWO, CardSuitSymbol.CLUB),
                new Card(CardNumber.KING, CardSuitSymbol.CLUB)
        )), 10_000d);
        Dealer dealer = new Dealer(new ArrayList<>(Arrays.asList(
                new Card(CardNumber.THREE, CardSuitSymbol.DIAMOND),
                new Card(CardNumber.KING, CardSuitSymbol.DIAMOND)
        )));

        Assertions.assertThat(Profit.calculateProfit(player, dealer)).isEqualTo(-10_000);
    }
}
