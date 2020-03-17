package domain;

import domain.card.*;
import domain.player.Dealer;
import domain.player.User;
import domain.player.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardCalculatorTest {
    private Cards cards;
    private Card card1;
    private Card card2;

    @BeforeEach
    private void setUp() {
        cards = new Cards();
    }

    @DisplayName("딜러가 ace 와 10 을 갖고 있을 때 더 카드를 받지 않도록 하는지 테스트")
    @Test
    void isUnderSixteenWithAceTest() {
        card1 = new Card(CardNumber.ACE, CardSuitSymbol.CLUB);
        card2 = new Card(CardNumber.KING, CardSuitSymbol.CLUB);
        List<Card> startDeck = new ArrayList<>(Arrays.asList(card1, card2));

        Dealer dealer = new Dealer(startDeck);
        dealer.isAdditionalCard(cards.giveCard());

        Assertions.assertThat(dealer.getCard().size()).isEqualTo(2);
    }

    @DisplayName("딜러가 ace 를 갖고 10 을 갖지 않을 때 더 카드를 받도록 하는지 테스트")
    @Test
    void isUnderSixteenWithAceWithoutTenTest() {
        card1 = new Card(CardNumber.ACE, CardSuitSymbol.CLUB);
        card2 = new Card(CardNumber.FIVE, CardSuitSymbol.CLUB);
        List<Card> startDeck = new ArrayList<>(Arrays.asList(card1, card2));

        Dealer dealer = new Dealer(startDeck);
        dealer.isAdditionalCard(cards.giveCard());

        Assertions.assertThat(dealer.getCard().size()).isEqualTo(3);
    }

    @DisplayName("딜러가 ace 가 없고 카드의 합이 17이상일 때 더 카드를 받지 않도록 하는지 테스트")
    @Test
    void isUnderSixteenWithoutAceGetCardTest() {
        card1 = new Card(CardNumber.JACK, CardSuitSymbol.CLUB);
        card2 = new Card(CardNumber.SEVEN, CardSuitSymbol.CLUB);
        List<Card> startDeck = new ArrayList<>(Arrays.asList(card1, card2));

        Dealer dealer = new Dealer(startDeck);
        dealer.isAdditionalCard(cards.giveCard());

        Assertions.assertThat(dealer.getCard().size()).isEqualTo(2);
    }

    @DisplayName("딜러가 ace 가 없고 카드의 합이 16이하일 때 카드를 받도록 하는지 테스트")
    @Test
    void isUnderSixteenWithoutAceTest() {
        card1 = new Card(CardNumber.JACK, CardSuitSymbol.CLUB);
        card2 = new Card(CardNumber.SIX, CardSuitSymbol.CLUB);
        List<Card> startDeck = new ArrayList<>(Arrays.asList(card1, card2));

        Dealer dealer = new Dealer(startDeck);
        dealer.isAdditionalCard(cards.giveCard());

        Assertions.assertThat(dealer.getCard().size()).isEqualTo(3);
    }

    @DisplayName("블랙잭(총 합이 21) 인지 판단하는 메서드 테스트")
    @Test
    void isBlackjackTest() {
        card1 = new Card(CardNumber.ACE, CardSuitSymbol.CLUB);
        card2 = new Card(CardNumber.KING, CardSuitSymbol.CLUB);
        List<Card> startDeck = new ArrayList<>(Arrays.asList(card1, card2));

        User user = new Player("subway", startDeck);

        Assertions.assertThat(CardCalculator.isBlackJack(user.getCard())).isTrue();
    }
}
