package dealer;

import domain.card.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import domain.dealer.Dealer;

import java.util.List;

public class DealerTest {

    // THINK @BeforeEach 생각해볼 것.

    @Test
    void 카드를_2장_나눠준다() {
        CardDeck cardDeck = new CardDeckBuilder()
                .cards(Card.of(CardEmblem.EIGHT, CardDenomination.CLOVER), Card.of(CardEmblem.NINE, CardDenomination.SPADE))
                .build();

        Dealer dealer = Dealer.of(cardDeck);
        CardBundle cardBundle = dealer.handOutCard(2);

        Assertions.assertThat(cardBundle)
                .isEqualTo(CardBundle.of(List.of(
                        Card.of(CardEmblem.EIGHT, CardDenomination.CLOVER),
                        Card.of(CardEmblem.NINE, CardDenomination.SPADE)))
                );
    }

    @Test
    void 카드를_1장_나눠준다() {
        CardDeck cardDeck = new CardDeckBuilder()
                .cards(Card.of(CardEmblem.EIGHT, CardDenomination.CLOVER), Card.of(CardEmblem.NINE, CardDenomination.SPADE))
                .build();

        Dealer dealer = Dealer.of(cardDeck);
        CardBundle cardBundle = dealer.handOutCard(1);

        Assertions.assertThat(cardBundle)
                .isEqualTo(CardBundle.of(List.of(Card.of(CardEmblem.EIGHT, CardDenomination.CLOVER))
                ));
    }

    @Test
    void 카드덱에_카드가없으면_딜러가_카드를_나눠줄_수_없다() {
        CardDeck cardDeck = new CardDeckBuilder()
                .cards(Card.of(CardEmblem.EIGHT, CardDenomination.CLOVER))
                .build();

        Dealer dealer = Dealer.of(cardDeck);

        Assertions.assertThatThrownBy(() -> {
            CardBundle cardBundle = dealer.handOutCard(2);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 딜러가_스스로_카드_2장을_뽑는다() {
        Card clover8 = Card.of(CardEmblem.EIGHT, CardDenomination.CLOVER);
        Card clover9 = Card.of(CardEmblem.NINE, CardDenomination.CLOVER);
        List<Card> cards = List.of(clover8, clover9);

        CardDeck cardDeck = new CardDeckBuilder()
                .cards(cards)
                .build();

        Dealer dealer = Dealer.of(cardDeck);
        dealer.drawMySelf(2);

        CardBundle cardBundle = CardBundle.of(cards);
        Assertions.assertThat(dealer.disPlayMyCardBundle())
                .isEqualTo(cardBundle.toDisplay());
    }

    @Test
    void 덱에_카드가_없는_경우_카드를_뽑을_수_없다() {
        Card clover8 = Card.of(CardEmblem.EIGHT, CardDenomination.CLOVER);
        List<Card> cards = List.of(clover8);

        CardDeck cardDeck = new CardDeckBuilder()
                .cards(cards)
                .build();

        Dealer dealer = Dealer.of(cardDeck);

        Assertions.assertThatThrownBy(() -> {
            dealer.drawMySelf(2);
        }).isInstanceOf(IllegalArgumentException.class);
    }

}
