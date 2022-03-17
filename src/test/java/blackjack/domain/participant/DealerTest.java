package blackjack.domain.participant;

import static blackjack.domain.card.CardNumber.*;
import static blackjack.domain.card.CardSymbol.*;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import blackjack.domain.PlayRecord;
import blackjack.domain.PlayStatus;
import blackjack.domain.TestDeck;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.deckstrategy.DeckStrategy;

class DealerTest {

    @Test
    @DisplayName("조건에 만족할 때 까지 카드를 뽑는다. (버스트)")
    void drawCards_BUST() {
        // give
        Dealer dealer = new Dealer(List.of());
        CardDeck cardDeck = new CardDeck(new TestDeck());
        dealer.init(cardDeck);

        // when
        dealer.drawCards(cardDeck);
        PlayStatus actual = dealer.getStatus();

        // then
        assertThat(actual).isEqualTo(PlayStatus.BUST);
    }

    @Test
    @DisplayName("조건에 만족할 때 까지 카드르 뽑는다. (BUST X)")
    void drawCards_NOT_BUST() {
        // give
        Dealer dealer = new Dealer(List.of());
        CardDeck cardDeck = new CardDeck(new TestNoBustDeck());
        dealer.init(cardDeck);

        // when
        dealer.drawCards(cardDeck);
        PlayStatus actual = dealer.getStatus();

        // then
        assertThat(actual).isEqualTo(PlayStatus.HIT);
    }

    @Test
    @DisplayName("처음 받은 카드 중에 한 장의 카드를 공개한다.")
    void openCard() {
        // give
        Dealer dealer = new Dealer(List.of());
        CardDeck cardDeck = new CardDeck(new TestDeck());
        dealer.init(cardDeck);

        // when
        Card actual = dealer.openCard();

        // then
        assertThat(actual).isEqualTo(new Card(CLUB, FIVE));
    }

    @ParameterizedTest
    @CsvSource(value = {"SEVEN:false", "SIX:true"}, delimiter = ':')
    @DisplayName("딜러가 카드를 추가로 뽑았는지 아닌지 검증한다.")
    void isDraw(CardNumber cardNumber, boolean expected) {
        // give
        Dealer dealer = new Dealer(List.of());
        CardDeck cardDeck = new CardDeck(() -> new ArrayDeque<>(List.of(
            new Card(DIAMOND, ACE),
            new Card(DIAMOND, QUEEN), new Card(CLUB, cardNumber))));
        dealer.init(cardDeck);
        // when
        boolean actual = dealer.drawCards(cardDeck).isDraw();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    private static class TestNoBustDeck implements DeckStrategy {
        @Override
        public Deque<Card> create() {
            // start, Dealer
            return new ArrayDeque<>(List.of(
                new Card(DIAMOND, QUEEN), new Card(CLUB, SEVEN)));
        }
    }

    @Test
    @DisplayName("딜러의 수익을 반환한다.")
    void dealerRevenue() {
        //given
        Dealer dealer = new Dealer(List.of(new Betting(Name.of("pobi"), 10000),
            new Betting(Name.of("jason"), 20000)));

        //when
        long actual = dealer.getRevenues(Map.of(Name.of("pobi"), PlayRecord.WIN,
            Name.of("jason"), PlayRecord.LOSS)).get(Name.of("딜러"));
        //then
        assertThat(actual).isEqualTo(10000);
    }
}