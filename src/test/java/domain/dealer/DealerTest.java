package domain.dealer;

import domain.card.*;
import domain.card.exception.CardException;
import domain.gamer.Dealer;
import domain.gamer.Player;
import domain.gamer.PlayerName;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.CardDeckBuilder;

import java.util.List;

public class DealerTest {

    private final PlayerName testPlayerName  = new PlayerName("test");
    private final Player testPlayer = Player.from(testPlayerName);;

    @Test
    void 카드를_2장_나눠준다() {
        CardDeck cardDeck = new CardDeckBuilder()
                .cards(Card.of(CardDenomination.EIGHT, CardEmblem.CLOVER), Card.of(CardDenomination.NINE, CardEmblem.SPADE))
                .build();

        Dealer dealer = Dealer.from(cardDeck);
        CardBundle cardBundle = dealer.dealCardToPlayer(testPlayer);

        Assertions.assertThat(cardBundle)
                .isEqualTo(CardBundle.from(List.of(
                        Card.of(CardDenomination.EIGHT, CardEmblem.CLOVER),
                        Card.of(CardDenomination.NINE, CardEmblem.SPADE)))
                );
    }

    @Test
    void 카드를_1장_나눠준다() {
        CardDeck cardDeck = new CardDeckBuilder()
                .cards(Card.of(CardDenomination.EIGHT, CardEmblem.CLOVER), Card.of(CardDenomination.NINE, CardEmblem.SPADE))
                .build();

        Dealer dealer = Dealer.from(cardDeck);
        CardBundle cardBundle = dealer.hitCardToPlayer(testPlayer);

        Assertions.assertThat(cardBundle)
                .isEqualTo(CardBundle.from(List.of(Card.of(CardDenomination.EIGHT, CardEmblem.CLOVER))
                ));
    }

    @Test
    void 카드덱에_카드가없으면_딜러가_카드를_나눠줄_수_없다() {
        CardDeck cardDeck = new CardDeckBuilder()
                .cards()
                .build();

        Dealer dealer = Dealer.from(cardDeck);

        Assertions.assertThatThrownBy(() -> {
            dealer.dealCardToPlayer(testPlayer);
        }).isInstanceOf(CardException.class);
    }

    @Test
    void 딜러가_스스로_카드_2장을_뽑는다() {
        Card clover8 = Card.of(CardDenomination.EIGHT, CardEmblem.CLOVER);
        Card clover9 = Card.of(CardDenomination.NINE, CardEmblem.CLOVER);
        List<Card> cards = List.of(clover8, clover9);

        CardDeck cardDeck = new CardDeckBuilder()
                .cards(cards)
                .build();

        Dealer dealer = Dealer.from(cardDeck);
        dealer.dealMyself();

        CardBundle expectedCardBundle = CardBundle.from(cards);
        Assertions.assertThat(dealer.getCardBundle())
                .isEqualTo(expectedCardBundle);
    }

    @Test
    void 덱에_카드가_없는_경우_카드를_뽑을_수_없다() {
        Card clover8 = Card.of(CardDenomination.EIGHT, CardEmblem.CLOVER);
        List<Card> cards = List.of(clover8);

        CardDeck cardDeck = new CardDeckBuilder()
                .cards(cards)
                .build();

        Dealer dealer = Dealer.from(cardDeck);

        Assertions.assertThatThrownBy(() -> {
            dealer.dealMyself();
        }).isInstanceOf(CardException.class);
    }

    @Test
    void 딜러의_카드합이_16이하인경우_한장_더_뽑는다() {
        Card cloverQueen = Card.of(CardDenomination.QUEEN, CardEmblem.CLOVER);
        Card clover6 = Card.of(CardDenomination.SIX, CardEmblem.CLOVER);
        Card clover2 = Card.of(CardDenomination.TWO, CardEmblem.CLOVER);
        List<Card> cards = List.of(cloverQueen, clover6, clover2);

        CardDeck cardDeck = new CardDeckBuilder()
                .cards(cards)
                .build();

        Dealer dealer = Dealer.from(cardDeck);
        dealer.dealMyself();

        Assertions.assertThat(dealer.hitIfRequired())
                .isTrue();
    }

}
