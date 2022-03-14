package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import domain.player.Dealer;
import domain.player.Gambler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MatchResultTest {
    @ParameterizedTest(name = "{0}-{1} {2}-{3} vs {4}-{5} {6}-{7} -> {8}")
    @CsvSource(value = {
            "SPADES,TEN,CLUBS,NINE,SPADES,TEN,CLUBS,EIGHT,LOSE",
            "SPADES,TEN,CLUBS,NINE,SPADES,TEN,CLUBS,NINE,DRAW",
            "SPADES,TEN,CLUBS,TEN,SPADES,TEN,CLUBS,ACE,WIN"})
    @DisplayName("딜러 VS 겜블러 경기 결과 판정 테스트")
    void findMatchResult(Suit dealerSuit, Denomination dealerDenomination,
                         Suit dealerSuit2, Denomination dealerDenomination2,
                         Suit gamblerSuit, Denomination gamblerDenomination,
                         Suit gamblerSuit2, Denomination gamblerDenomination2,
                         MatchResult expected) {
        // given
        Dealer dealer = new Dealer();
        dealer.addCard(Card.of(dealerSuit, dealerDenomination));
        dealer.addCard(Card.of(dealerSuit2, dealerDenomination2));
        Gambler gambler = new Gambler("리차드");
        gambler.addCard(Card.of(gamblerSuit, gamblerDenomination));
        gambler.addCard(Card.of(gamblerSuit2, gamblerDenomination2));

        // when
        MatchResult actual = MatchResult.of(dealer, gambler);

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
