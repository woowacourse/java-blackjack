package domain.dealer;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.CardDeckBuilder;
import domain.card.CardDenomination;
import domain.card.CardEmblem;
import domain.participant.Dealer;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

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
