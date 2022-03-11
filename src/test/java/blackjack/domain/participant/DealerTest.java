package blackjack.domain.participant;

import static blackjack.domain.card.CardNumber.*;
import static blackjack.domain.card.CardSymbol.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.PlayStatus;

class DealerTest {

    @Test
    @DisplayName("조건에 만족할 때 까지 카드르 뽑는다. (버스트)")
    void drawCards_BUST() {
        // give
        final Dealer dealer = new Dealer();
        final CardDeck cardDeck = CardDeck.createNoShuffle();
        List<Card> cards = List.of(new Card(DIAMOND, QUEEN), new Card(CLUB, FIVE));
        dealer.init(cards);

        // when
        dealer.drawCards(cardDeck);
        final PlayStatus actual = dealer.getStatus();

        // then
        assertThat(actual).isEqualTo(PlayStatus.BUST);
    }

    @Test
    @DisplayName("조건에 만족할 때 까지 카드르 뽑는다. (BUST X)")
    void drawCards_NOT_BUST() {
        // give
        final Dealer dealer = new Dealer();
        final CardDeck cardDeck = CardDeck.createNoShuffle();
        dealer.init(cardDeck);

        // when
        dealer.drawCards(cardDeck);
        final PlayStatus actual = dealer.getStatus();

        // then
        assertThat(actual).isEqualTo(PlayStatus.HIT);
    }

    @Test
    @DisplayName("처음 받은 카드 중에 한 장의 카드를 공개한다.")
    void openCard() {
        // give
        final Dealer dealer = new Dealer();
        final CardDeck cardDeck = CardDeck.createNoShuffle();
        dealer.init(cardDeck);

        // when
        final Card actual = dealer.openCard();

        // then
        assertThat(actual).isEqualTo(new Card(CLUB, KING));
    }
}