package domain.player;

import domain.BlackJackRule;
import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardSuitSymbol;
import domain.card.CardDeck;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class DealerTest {
    private CardDeck cardDeck;
    private BlackJackRule blackJackRule;

    @BeforeEach
    private void setUp() {
        cardDeck = new CardDeck();
        blackJackRule = new BlackJackRule();
    }

    @DisplayName("16을 기준으로 카드를 받는지 결정하는 메서드")
    @Test
    void insertCardTest() {
        Card card1 = Card.of(CardNumber.ACE, CardSuitSymbol.CLUB);
        Card card2 = Card.of(CardNumber.FIVE, CardSuitSymbol.CLUB);
        Dealer dealerWithAceUnderSixteen = new Dealer(new ArrayList<>(Arrays.asList(card1, card2)));
        if (dealerWithAceUnderSixteen.getCard().isCardsSumUnderSixteen()) {
            blackJackRule.hit(dealerWithAceUnderSixteen, cardDeck.drawCard());
        }

        Card card3 = Card.of(CardNumber.QUEEN, CardSuitSymbol.DIAMOND);
        Dealer dealerWithoutAceUnderSixteen = new Dealer(new ArrayList<>(Arrays.asList(card2, card3)));
        if (dealerWithoutAceUnderSixteen.getCard().isCardsSumUnderSixteen()) {
            blackJackRule.hit(dealerWithoutAceUnderSixteen, cardDeck.drawCard());
        }

        Card card4 = Card.of(CardNumber.NINE, CardSuitSymbol.CLUB);
        Dealer dealerOverSixteen = new Dealer(new ArrayList<>(Arrays.asList(card3, card4)));
        if (dealerOverSixteen.getCard().isCardsSumUnderSixteen()) {
            blackJackRule.hit(dealerOverSixteen, cardDeck.drawCard());
        }

        Assertions.assertThat(dealerWithAceUnderSixteen.getCard().getCards().size()).isEqualTo(3);
        Assertions.assertThat(dealerWithoutAceUnderSixteen.getCard().getCards().size()).isEqualTo(3);
        Assertions.assertThat(dealerOverSixteen.getCard().getCards().size()).isEqualTo(2);
    }

    @DisplayName("딜러가 ace 와 10 을 갖고 있을 때 더 카드를 받지 않도록 하는지 테스트")
    @Test
    void isUnderSixteenWithAceTest() {
        Dealer dealer = new Dealer(new ArrayList<>(Arrays.asList(
                Card.of(CardNumber.ACE, CardSuitSymbol.CLUB),
                Card.of(CardNumber.JACK, CardSuitSymbol.CLUB))));
        if (dealer.getCard().isCardsSumUnderSixteen()) {
            blackJackRule.hit(dealer, cardDeck.drawCard());
        }

        Assertions.assertThat(dealer.getCard().getCards().size()).isEqualTo(2);
    }

    @DisplayName("딜러가 ace 를 갖고 10 을 갖지 않을 때 더 카드를 받도록 하는지 테스트")
    @Test
    void isUnderSixteenWithAceWithoutTenTest() {
        Dealer dealer = new Dealer(new ArrayList<>(Arrays.asList(
                Card.of(CardNumber.ACE, CardSuitSymbol.CLUB),
                Card.of(CardNumber.FIVE, CardSuitSymbol.CLUB))));
        if (dealer.getCard().isCardsSumUnderSixteen()) {
            blackJackRule.hit(dealer, cardDeck.drawCard());
        }

        Assertions.assertThat(dealer.getCard().getCards().size()).isEqualTo(3);
    }

    @DisplayName("딜러가 ace 가 없고 카드의 합이 17이상일 때 더 카드를 받지 않도록 하는지 테스트")
    @Test
    void isUnderSixteenWithoutAceGetCardTest() {
        Dealer dealer = new Dealer(new ArrayList<>(Arrays.asList(
                Card.of(CardNumber.JACK, CardSuitSymbol.CLUB),
                Card.of(CardNumber.SEVEN, CardSuitSymbol.CLUB))));
        if (dealer.getCard().isCardsSumUnderSixteen()) {
            blackJackRule.hit(dealer, cardDeck.drawCard());
        }

        Assertions.assertThat(dealer.getCard().getCards().size()).isEqualTo(2);
    }

    @DisplayName("딜러가 ace 가 없고 카드의 합이 16이하일 때 카드를 받도록 하는지 테스트")
    @Test
    void isUnderSixteenWithoutAceTest() {
        Dealer dealer = new Dealer(new ArrayList<>(Arrays.asList(
                Card.of(CardNumber.JACK, CardSuitSymbol.CLUB),
                Card.of(CardNumber.SIX, CardSuitSymbol.CLUB))));
        if (dealer.getCard().isCardsSumUnderSixteen()) {
            blackJackRule.hit(dealer, cardDeck.drawCard());
        }

        Assertions.assertThat(dealer.getCard().getCards().size()).isEqualTo(3);
    }
}
