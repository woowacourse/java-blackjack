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
        // given
        Card cloverQueen = Card.of(CardDenomination.QUEEN, CardEmblem.CLOVER);
        Card clover6 = Card.of(CardDenomination.SIX, CardEmblem.CLOVER);
        Card clover2 = Card.of(CardDenomination.TWO, CardEmblem.CLOVER);
        CardDeck cardDeck = new CardDeckBuilder()
                .cards(List.of(cloverQueen, clover6, clover2))
                .build();
        Dealer dealer = Dealer.create();
        dealer.drawCards(cardDeck, 2);

        // when & then
        Assertions.assertThat(dealer.canHit()).isTrue();
    }

    @Test
    void 딜러의_카드합이_17이상인_경우_유지한다() {
        // given
        Card cloverQueen = Card.of(CardDenomination.QUEEN, CardEmblem.CLOVER);
        Card clover6 = Card.of(CardDenomination.KING, CardEmblem.CLOVER);
        CardDeck cardDeck = new CardDeckBuilder()
                .cards(List.of(cloverQueen, clover6))
                .build();
        Dealer dealer = Dealer.create();
        dealer.drawCards(cardDeck, 2);

        // when & then
        Assertions.assertThat(dealer.canHit()).isFalse();
    }
}
