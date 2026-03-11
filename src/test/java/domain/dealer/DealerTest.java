package domain.dealer;

import static config.BlackjackGameConstant.*;

import domain.card.*;
import domain.participant.Dealer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class DealerTest {


    @Test
    void 딜러의_카드합이_16이하인경우_한장_더_뽑는다() {
        Card cloverQueen = Card.of(CardDenomination.QUEEN, CardEmblem.CLOVER);
        Card clover6 = Card.of(CardDenomination.SIX, CardEmblem.CLOVER);
        Card clover2 = Card.of(CardDenomination.TWO, CardEmblem.CLOVER);
        List<Card> cards = List.of(cloverQueen, clover6, clover2);

        CardDeck cardDeck = new CardDeckBuilder()
                .cards(cards)
                .build();

        Dealer dealer = Dealer.from();
        dealer.drawCards(cardDeck, 2);

        Assertions.assertThat(dealer.hitIfRequired(cardDeck)).isTrue();
    }

    @Test
    void 딜러의_카드합이_17이상인_경우_유지한다() {
        Card cloverQueen = Card.of(CardDenomination.QUEEN, CardEmblem.CLOVER);
        Card clover6 = Card.of(CardDenomination.KING, CardEmblem.CLOVER);
        List<Card> cards = List.of(cloverQueen, clover6);

        CardDeck cardDeck = new CardDeckBuilder()
                .cards(cards)
                .build();

        Dealer dealer = Dealer.from();
        dealer.drawCards(cardDeck, 2);

        Assertions.assertThat(dealer.hitIfRequired(cardDeck)).isFalse();
    }
}
