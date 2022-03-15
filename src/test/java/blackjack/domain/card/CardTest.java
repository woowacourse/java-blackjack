package blackjack.domain.card;

import static blackjack.domain.card.CardSymbol.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CardTest {

    @ParameterizedTest
    @CsvSource(value = {"CLUB:ACE:클로버:A", "SPADE:KING:스페이드:K"}, delimiter = ':')
    @DisplayName("캐싱한 카드를 가져온다.")
    void of(CardSymbol symbol, CardNumber number, String expectedSymbol, String expectedNumber) {
        // when
        final Card card = Card.of(symbol, number);

        // then
        assertThat(card.getSymbolName()).isEqualTo(expectedSymbol);
        assertThat(card.getNumberName()).isEqualTo(expectedNumber);
    }

    @Test
    @DisplayName("카드의 총 개수는 52장이다.")
    void getAllCards() {
        // give
        final List<Card> allCards = Card.getAllCards();

        // when
        final int actual = allCards.size();

        // then
        assertThat(actual).isEqualTo(52);
    }

    @ParameterizedTest
    @DisplayName("카드의 숫자가 ACE인지 확인한다.")
    @CsvSource(value = {"ACE:true", "KING:false"}, delimiter = ':')
    void isAce(CardNumber cardNumber, boolean expected) {
        // give
        final Card card = Card.of(CLUB, cardNumber);

        // when
        final boolean actual = card.isAce();

        // then
        assertThat(actual).isEqualTo(expected);
    }
}