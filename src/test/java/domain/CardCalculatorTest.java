package domain;

import domain.card.*;
import domain.player.Dealer;
import domain.player.User;
import domain.player.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardCalculatorTest {
    private Cards cards;

    @BeforeEach
    private void setUp() {
        cards = new Cards();
    }

    @DisplayName("딜러가 ace 와 10 을 갖고 있을 때 더 카드를 받지 않도록 하는지 테스트")
    @Test
    void isUnderSixteenWithAceTest() {
        Dealer dealer = new Dealer(Card.of(CardNumber.ACE, CardSuitSymbol.CLUB),
                Card.of(CardNumber.TEN, CardSuitSymbol.CLUB));
        dealer.isAdditionalCard(cards.giveCard());

        Assertions.assertThat(dealer.getCard().size()).isEqualTo(2);
    }

    @DisplayName("딜러가 ace 를 갖고 10 을 갖지 않을 때 더 카드를 받도록 하는지 테스트")
    @Test
    void isUnderSixteenWithAceWithoutTenTest() {
        Dealer dealer = new Dealer(Card.of(CardNumber.ACE, CardSuitSymbol.CLUB),
                Card.of(CardNumber.FIVE, CardSuitSymbol.CLUB));
        dealer.isAdditionalCard(cards.giveCard());

        Assertions.assertThat(dealer.getCard().size()).isEqualTo(3);
    }

    @DisplayName("딜러가 ace 가 없고 카드의 합이 17이상일 때 더 카드를 받지 않도록 하는지 테스트")
    @Test
    void isUnderSixteenWithoutAceGetCardTest() {
        Dealer dealer = new Dealer(Card.of(CardNumber.JACK, CardSuitSymbol.CLUB),
                Card.of(CardNumber.SEVEN, CardSuitSymbol.CLUB));
        dealer.isAdditionalCard(cards.giveCard());

        Assertions.assertThat(dealer.getCard().size()).isEqualTo(2);
    }

    @DisplayName("딜러가 ace 가 없고 카드의 합이 16이하일 때 카드를 받도록 하는지 테스트")
    @Test
    void isUnderSixteenWithoutAceTest() {
        Dealer dealer = new Dealer(Card.of(CardNumber.JACK, CardSuitSymbol.CLUB),
                Card.of(CardNumber.SIX, CardSuitSymbol.CLUB));
        dealer.isAdditionalCard(cards.giveCard());

        Assertions.assertThat(dealer.getCard().size()).isEqualTo(3);
    }

    @DisplayName("블랙잭(총 합이 21) 인지 판단하는 메서드 테스트")
    @Test
    void isBlackjackTest() {
        User user = new Player("subway",Card.of(CardNumber.ACE, CardSuitSymbol.CLUB),
                Card.of(CardNumber.TEN, CardSuitSymbol.CLUB));

        Assertions.assertThat(CardCalculator.isBlackJack(user.getCard())).isTrue();
    }
}
