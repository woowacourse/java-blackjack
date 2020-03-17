package domain;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardSuitSymbol;
import domain.player.Dealer;
import domain.player.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DecisionWinnerTest {
    @DisplayName("딜러가 블랙잭이고 유저도 블랙잭인 경우 유저가 승")
    @Test
    void userAndDealerBothBlackjackTest() {
        Player player = new Player("subway", new Card(CardNumber.ACE, CardSuitSymbol.CLUB),
                new Card(CardNumber.KING, CardSuitSymbol.CLUB));
        Dealer dealer = new Dealer(new Card(CardNumber.ACE, CardSuitSymbol.CLUB),
                new Card(CardNumber.KING, CardSuitSymbol.CLUB));

        Assertions.assertThat(DecisionWinner.compareWinner(player, dealer)).isTrue();
    }

    @DisplayName("유저가 블랙잭인 경우 유저가 승")
    @Test
    void onlyUserBlackjackTest() {
        Player player = new Player("subway",new Card(CardNumber.ACE, CardSuitSymbol.CLUB),
                new Card(CardNumber.KING, CardSuitSymbol.CLUB));
        Dealer dealer = new Dealer(new Card(CardNumber.ACE, CardSuitSymbol.CLUB),
                new Card(CardNumber.FIVE, CardSuitSymbol.CLUB));

        Assertions.assertThat(DecisionWinner.compareWinner(player, dealer)).isTrue();
    }

    @DisplayName("딜러가 블랙잭이고 유저가 블랙잭이 아닌 경우 유저 패")
    @Test
    void onlyDealerBlackjackTest() {
        Player player = new Player("subway",new Card(CardNumber.FIVE, CardSuitSymbol.CLUB),
                new Card(CardNumber.KING, CardSuitSymbol.CLUB));
        Dealer dealer = new Dealer(new Card(CardNumber.ACE, CardSuitSymbol.CLUB),
                new Card(CardNumber.KING, CardSuitSymbol.CLUB));

        Assertions.assertThat(DecisionWinner.compareWinner(player, dealer)).isFalse();
    }

    @DisplayName("유저와 딜러 둘 다 블랙잭이 아니고 딜러의 카드 합이 더 큰 경우 유저 패")
    @Test
    void dealerWinWithoutBlackjackTest() {
        Player player = new Player("subway",new Card(CardNumber.SIX, CardSuitSymbol.CLUB),
                new Card(CardNumber.KING, CardSuitSymbol.CLUB));
        Dealer dealer = new Dealer(new Card(CardNumber.SEVEN, CardSuitSymbol.CLUB),
                new Card(CardNumber.KING, CardSuitSymbol.CLUB));

        Assertions.assertThat(DecisionWinner.compareWinner(player, dealer)).isFalse();
    }
}
