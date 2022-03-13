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
    @ParameterizedTest(name = "딜러 {0}-{1} vs 겜블러 {2}-{3} -> 예상 {4}")
    @CsvSource(value = {
            "SPADES,TEN,CLUBS,NINE,LOSE",
            "HEARTS,SEVEN,DIAMONDS,EIGHT,WIN",
            "DIAMONDS,FIVE,HEARTS,FIVE,DRAW,",
            "CLUBS,ACE,SPADES,TEN,LOSE"})
    @DisplayName("딜러 VS 겜블러 경기 결과 판정 테스트")
    void findMatchResult(Suit dealerSuit, Denomination dealerDenomination,
                         Suit gamblerSuit, Denomination gamblerDenomination,
                         MatchResult expected) {
        // given
        Dealer dealer = new Dealer();
        dealer.addCard(Card.of(dealerSuit, dealerDenomination));
        Gambler gambler = new Gambler("리차드");
        gambler.addCard(Card.of(gamblerSuit, gamblerDenomination));

        // when
        MatchResult actual = MatchResult.of(dealer, gambler);

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
