package blackjack.state.started.ready;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.card.Card;
import blackjack.card.CardDeck;
import blackjack.card.CardRank;
import blackjack.card.CardSymbol;
import blackjack.card.Hand;
import blackjack.state.State;
import blackjack.state.started.finished.Blackjack;
import blackjack.state.started.running.Hit;
import fixture.CardDeckFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InitialDealingTest {

    @DisplayName("InitialDealing 상태는 initialdeal 이후 기본적으로 Hit 상태를 반환한다")
    @Test
    void initialDealingToHit() {
        // given
        CardDeck cardDeck = CardDeckFixture.createCardDeck(Card.of(CardSymbol.SPADE, CardRank.EIGHT),
                Card.of(CardSymbol.SPADE, CardRank.SEVEN));
        InitialDealing initialDealing = new InitialDealing(new Hand());

        // when
        State actual = initialDealing.initialDeal(cardDeck);

        // then
        assertThat(actual).isInstanceOf(Hit.class);
    }

    @DisplayName("InitialDealing 상태는 initialdeal 이후 블랙잭이라면 Blackjack 상태를 반환한다")
    @Test
    void initialDealingToBlackjack() {
        // given
        CardDeck cardDeck = CardDeckFixture.createCardDeck(Card.of(CardSymbol.SPADE, CardRank.JACK),
                Card.of(CardSymbol.SPADE, CardRank.ACE));
        InitialDealing initialDealing = new InitialDealing(new Hand());

        // when
        State actual = initialDealing.initialDeal(cardDeck);

        // then
        assertThat(actual).isInstanceOf(Blackjack.class);
    }

}