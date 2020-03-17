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
import java.util.List;

public class DecisionWinnerTest {
    private Card card1;
    private Card card2;
    private Card card3;
    private List<Card> startDeck;

    @DisplayName("딜러가 블랙잭이고 유저도 블랙잭인 경우 유저가 승")
    @Test
    void userAndDealerBothBlackjackTest() {
        card1 = new Card(CardNumber.ACE, CardSuitSymbol.CLUB);
        card2 = new Card(CardNumber.KING, CardSuitSymbol.CLUB);
        startDeck = new ArrayList<>(Arrays.asList(card1, card2));

        Player player = new Player("subway", startDeck);
        Dealer dealer = new Dealer(startDeck);

        Assertions.assertThat(DecisionWinner.compareWinner(player, dealer)).isTrue();
    }

    @DisplayName("유저가 블랙잭인 경우 유저가 승")
    @Test
    void onlyUserBlackjackTest() {
        card1 = new Card(CardNumber.ACE, CardSuitSymbol.CLUB);
        card2 = new Card(CardNumber.KING, CardSuitSymbol.CLUB);
        card3 = new Card(CardNumber.FIVE, CardSuitSymbol.CLUB);

        startDeck = new ArrayList<>(Arrays.asList(card1, card2));
        Player player = new Player("subway", startDeck);
        startDeck = new ArrayList<>(Arrays.asList(card1, card3));
        Dealer dealer = new Dealer(startDeck);

        Assertions.assertThat(DecisionWinner.compareWinner(player, dealer)).isTrue();
    }

    @DisplayName("딜러가 블랙잭이고 유저가 블랙잭이 아닌 경우 유저 패")
    @Test
    void onlyDealerBlackjackTest() {
        card1 = new Card(CardNumber.FIVE, CardSuitSymbol.CLUB);
        card2 = new Card(CardNumber.KING, CardSuitSymbol.CLUB);
        card3 = new Card(CardNumber.ACE, CardSuitSymbol.CLUB);

        startDeck = new ArrayList<>(Arrays.asList(card1,card2));
        Player player = new Player("subway", startDeck);
        startDeck = new ArrayList<>(Arrays.asList(card2,card3));
        Dealer dealer = new Dealer(startDeck);

        Assertions.assertThat(DecisionWinner.compareWinner(player, dealer)).isFalse();
    }

    @DisplayName("유저와 딜러 둘 다 블랙잭이 아니고 딜러의 카드 합이 더 큰 경우 유저 패")
    @Test
    void dealerWinWithoutBlackjackTest() {
        card1 = new Card(CardNumber.SIX, CardSuitSymbol.CLUB);
        card2 = new Card(CardNumber.SEVEN, CardSuitSymbol.CLUB);
        card3 = new Card(CardNumber.KING, CardSuitSymbol.CLUB);

        startDeck = new ArrayList<>(Arrays.asList(card1,card2));
        Player player = new Player("subway", startDeck);
        startDeck = new ArrayList<>(Arrays.asList(card2,card3));
        Dealer dealer = new Dealer(startDeck);

        Assertions.assertThat(DecisionWinner.compareWinner(player, dealer)).isFalse();
    }
}
