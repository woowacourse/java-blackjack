package domain;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardSuitSymbol;
import domain.player.Dealer;
import domain.player.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class BlackJackRuleTest {
    private BlackJackRule blackJackRule;

    @BeforeEach
    private void setUp() {
        blackJackRule = new BlackJackRule();
    }

    @DisplayName("플레이어의 카드 값에 따라 반환하는 Boolean 값 테스트")
    @Test
    void isHitTest() {
        Player blackJackPlayer = new Player("pobi", new ArrayList<>(Arrays.asList(
                Card.of(CardNumber.ACE, CardSuitSymbol.CLUB),
                Card.of(CardNumber.JACK, CardSuitSymbol.CLUB))),
                10_000);
        Player overBlackJackPlayer = new Player("json", new ArrayList<>(Arrays.asList(
                Card.of(CardNumber.ACE, CardSuitSymbol.CLUB),
                Card.of(CardNumber.SEVEN, CardSuitSymbol.CLUB),
                Card.of(CardNumber.KING, CardSuitSymbol.SPACE),
                Card.of(CardNumber.KING, CardSuitSymbol.DIAMOND))),
                10_000);
        Player hitPlayer = new Player("lavine", new ArrayList<>(Arrays.asList(
                Card.of(CardNumber.KING, CardSuitSymbol.SPACE),
                Card.of(CardNumber.TWO, CardSuitSymbol.SPACE))),
                10_000);

        Assertions.assertThat(blackJackRule.isHit(blackJackPlayer)).isTrue();
        Assertions.assertThat(blackJackRule.isHit(overBlackJackPlayer)).isFalse();
        Assertions.assertThat(blackJackRule.isHit(hitPlayer)).isTrue();
    }

    @DisplayName("딜러가 16을 기준으로 카드를 받는지 결정하는 메서드")
    @Test
    void insertCardTest() {
        Card aceClub = Card.of(CardNumber.ACE, CardSuitSymbol.CLUB);
        Card fiveClub = Card.of(CardNumber.FIVE, CardSuitSymbol.CLUB);
        Dealer dealerWithAceUnderSixteen = new Dealer(new ArrayList<>(Arrays.asList(aceClub, fiveClub)));

        Card queenDiamond = Card.of(CardNumber.QUEEN, CardSuitSymbol.DIAMOND);
        Dealer dealerWithoutAceUnderSixteen = new Dealer(new ArrayList<>(Arrays.asList(fiveClub, queenDiamond)));

        Card nineClub = Card.of(CardNumber.NINE, CardSuitSymbol.CLUB);
        Dealer dealerOverSixteen = new Dealer(new ArrayList<>(Arrays.asList(queenDiamond, nineClub)));

        Assertions.assertThat(blackJackRule.isHit(dealerWithAceUnderSixteen)).isTrue();
        Assertions.assertThat(blackJackRule.isHit(dealerWithoutAceUnderSixteen)).isTrue();
        Assertions.assertThat(blackJackRule.isHit(dealerOverSixteen)).isFalse();
    }

    @DisplayName("딜러가 ace 와 10 을 갖고 있을 때 더 카드를 받지 않도록 하는지 테스트")
    @Test
    void isUnderSixteenWithAceTest() {
        Dealer dealer = new Dealer(new ArrayList<>(Arrays.asList(
                Card.of(CardNumber.ACE, CardSuitSymbol.CLUB),
                Card.of(CardNumber.JACK, CardSuitSymbol.CLUB))));

        Assertions.assertThat(blackJackRule.isHit(dealer)).isFalse();
    }

    @DisplayName("딜러가 ace 를 갖고 10 을 갖지 않을 때 더 카드를 받도록 하는지 테스트")
    @Test
    void isUnderSixteenWithAceWithoutTenTest() {
        Dealer dealer = new Dealer(new ArrayList<>(Arrays.asList(
                Card.of(CardNumber.ACE, CardSuitSymbol.CLUB),
                Card.of(CardNumber.FIVE, CardSuitSymbol.CLUB))));

        Assertions.assertThat(blackJackRule.isHit(dealer)).isTrue();
    }

    @DisplayName("딜러가 ace 가 없고 카드의 합이 17이상일 때 더 카드를 받지 않도록 하는지 테스트")
    @Test
    void isUnderSixteenWithoutAceGetCardTest() {
        Dealer dealer = new Dealer(new ArrayList<>(Arrays.asList(
                Card.of(CardNumber.JACK, CardSuitSymbol.CLUB),
                Card.of(CardNumber.SEVEN, CardSuitSymbol.CLUB))));

        Assertions.assertThat(blackJackRule.isHit(dealer)).isFalse();
    }

    @DisplayName("딜러가 ace 가 없고 카드의 합이 16이하일 때 카드를 받도록 하는지 테스트")
    @Test
    void isUnderSixteenWithoutAceTest() {
        Dealer dealer = new Dealer(new ArrayList<>(Arrays.asList(
                Card.of(CardNumber.JACK, CardSuitSymbol.CLUB),
                Card.of(CardNumber.SIX, CardSuitSymbol.CLUB))));

        Assertions.assertThat(blackJackRule.isHit(dealer)).isTrue();
    }
}
