package blackjack.domain.participant;

import static blackjack.domain.card.CardNumber.*;
import static blackjack.domain.card.CardSymbol.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.PlayRecord;
import blackjack.domain.TestDeck;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;

public class StateDealerTest {

    @Test
    @DisplayName("처음 받은 카드 중에 한 장의 카드를 공개한다.")
    void openCard() {
        // give
        Dealer dealer = new Dealer(List.of());
        CardDeck cardDeck = new CardDeck(new TestDeck());
        dealer.init(cardDeck.drawCard(), cardDeck.drawCard());

        // when
        Card actual = dealer.openCard();

        // then
        assertThat(actual).isEqualTo(new Card(CLUB, FIVE));
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
