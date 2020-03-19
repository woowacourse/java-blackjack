package domain;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardSuitSymbol;
import domain.player.Dealer;
import domain.player.Player;
import domain.player.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardCalculatorTest {


    @DisplayName("딜러가 ace 와 10 을 갖고 있을 때 더 카드를 받지 않도록 하는지 테스트")
    @Test
    void isUnderSixteenWithAceTest() {
        Card firstCard = new Card(CardNumber.ACE, CardSuitSymbol.CLUB);
        Card secondCard = new Card(CardNumber.KING, CardSuitSymbol.CLUB);
        List<Card> startDeck = new ArrayList<>(Arrays.asList(firstCard, secondCard));

        Dealer dealer = new Dealer(startDeck);
        dealer.isAdditionalCard(new Card(CardNumber.THREE, CardSuitSymbol.HEART));

        Assertions.assertThat(dealer.getCard().size()).isEqualTo(2);
    }

    @DisplayName("딜러가 ace 를 갖고 10 을 갖지 않을 때 더 카드를 받도록 하는지 테스트")
    @Test
    void isUnderSixteenWithAceWithoutTenTest() {
        Card firstCard = new Card(CardNumber.ACE, CardSuitSymbol.CLUB);
        Card secondCard = new Card(CardNumber.FIVE, CardSuitSymbol.CLUB);
        List<Card> startDeck = new ArrayList<>(Arrays.asList(firstCard, secondCard));

        Dealer dealer = new Dealer(startDeck);
        dealer.isAdditionalCard(new Card(CardNumber.THREE, CardSuitSymbol.HEART));

        Assertions.assertThat(dealer.getCard().size()).isEqualTo(3);
    }

    @DisplayName("딜러가 ace 가 없고 카드의 합이 17이상일 때 더 카드를 받지 않도록 하는지 테스트")
    @Test
    void isUnderSixteenWithoutAceGetCardTest() {
        Card firstCard = new Card(CardNumber.JACK, CardSuitSymbol.CLUB);
        Card secondCard = new Card(CardNumber.SEVEN, CardSuitSymbol.CLUB);
        List<Card> startDeck = new ArrayList<>(Arrays.asList(firstCard, secondCard));

        Dealer dealer = new Dealer(startDeck);
        dealer.isAdditionalCard(new Card(CardNumber.THREE, CardSuitSymbol.HEART));

        Assertions.assertThat(dealer.getCard().size()).isEqualTo(2);
    }

    @DisplayName("딜러가 ace 가 없고 카드의 합이 16이하일 때 카드를 받도록 하는지 테스트")
    @Test
    void isUnderSixteenWithoutAceTest() {
        Card firstCard = new Card(CardNumber.JACK, CardSuitSymbol.CLUB);
        Card secondCard = new Card(CardNumber.SIX, CardSuitSymbol.CLUB);
        List<Card> startDeck = new ArrayList<>(Arrays.asList(firstCard, secondCard));

        Dealer dealer = new Dealer(startDeck);
        dealer.isAdditionalCard(new Card(CardNumber.THREE, CardSuitSymbol.HEART));

        Assertions.assertThat(dealer.getCard().size()).isEqualTo(3);
    }

    @DisplayName("블랙잭(총 합이 21) 인지 판단하는 메서드 테스트")
    @Test
    void isBlackjackTest() {
        Card firstCard = new Card(CardNumber.ACE, CardSuitSymbol.CLUB);
        Card secondCard = new Card(CardNumber.KING, CardSuitSymbol.CLUB);
        List<Card> startDeck = new ArrayList<>(Arrays.asList(firstCard, secondCard));

        User user = new Player("subway", startDeck);

        Assertions.assertThat(user.isBlackJack()).isTrue();
    }
}
