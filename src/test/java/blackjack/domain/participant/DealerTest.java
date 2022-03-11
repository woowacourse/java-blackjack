package blackjack.domain.participant;

import static blackjack.domain.card.CardNumber.FIVE;
import static blackjack.domain.card.CardNumber.KING;
import static blackjack.domain.card.CardNumber.QUEEN;
import static blackjack.domain.card.CardSymbol.CLUB;
import static blackjack.domain.card.CardSymbol.DIAMOND;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardFactory;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Status;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("조건에 만족할 때 까지 카드르 뽑는다. (버스트)")
    void drawCards_BUST() {
        // give
        final Dealer dealer = new Dealer();
        final CardFactory cardFactory = CardFactory.createNoShuffle();
        List<Card> cards = List.of(new Card(DIAMOND, QUEEN), new Card(CLUB, FIVE));
        dealer.init(cards);

        // when
        dealer.drawCards(cardFactory);
        final Status actual = dealer.getStatus();

        // then
        assertThat(actual).isEqualTo(Status.BUST);
    }

    @Test
    @DisplayName("조건에 만족할 때 까지 카드르 뽑는다. (BUST X)")
    void drawCards_NOT_BUST() {
        // give
        final Dealer dealer = new Dealer();
        final CardFactory cardFactory = CardFactory.createNoShuffle();
        dealer.init(cardFactory);

        // when
        dealer.drawCards(cardFactory);
        final Status actual = dealer.getStatus();

        // then
        assertThat(actual).isEqualTo(Status.HIT);
    }

    @Test
    @DisplayName("처음 받은 카드 중에 한 장의 카드를 공개한다.")
    void openCard() {
        // give
        final Dealer dealer = new Dealer();
        final CardFactory cardFactory = CardFactory.createNoShuffle();
        dealer.init(cardFactory);

        // when
        final Card actual = dealer.openCard();

        // then
        assertThat(actual.getNumber()).isEqualTo(KING);
        assertThat(actual.getSymbol()).isEqualTo(CLUB);
    }
}