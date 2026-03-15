package domain.gamer;

import domain.card.*;
import domain.card.exception.CardException;
import domain.gamer.exception.PlayerException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.CardDeckBuilder;

import java.util.List;

public class PlayerTest {

    private final PlayerName testPlayerName  = new PlayerName("test");
    private final Player testPlayer = Player.from(testPlayerName);;

    @Test
    void 플레이어가_카드를_1장_받는다() {
        CardDeck cardDeck = new CardDeckBuilder()
                .cards(Card.of(CardDenomination.EIGHT, CardEmblem.CLOVER), Card.of(CardDenomination.NINE, CardEmblem.SPADE),
                        Card.of(CardDenomination.TWO, CardEmblem.SPADE), Card.of(CardDenomination.THREE, CardEmblem.HEART))
                .build();
        Dealer dealer = Dealer.from(cardDeck);

        dealer.hitCardToPlayer(testPlayer);
        Card card = Card.of(CardDenomination.EIGHT, CardEmblem.CLOVER);
        CardBundle actualCardBundle = testPlayer.getCardBundle();
        CardBundle expectedCardBundle = CardBundle.from(List.of(card));

        Assertions.assertThat(actualCardBundle)
                .isEqualTo(expectedCardBundle);
    }

    @Test
    void 덱에_카드가_없으면_플레이어는_카드를_받을_수_없다() {
        CardDeck cardDeck = new CardDeckBuilder()
                .build();

        Dealer dealer = Dealer.from(cardDeck);

        Assertions.assertThatThrownBy(() -> {
            dealer.hitCardToPlayer(testPlayer);
        }).isInstanceOf(CardException.class);
    }

    @Test
    void 플레이어를_생성한다() {
        Assertions.assertThat(testPlayer.getName())
                .isEqualTo(testPlayerName.name());
    }

    @Test
    void 이름이_5자가_넘으면_예외가_발생한다() {
        String overFiveLengthName = "testtest";

        Assertions.assertThatThrownBy(() -> {
            Player.from(new PlayerName(overFiveLengthName));
        }).isInstanceOf(PlayerException.class);
    }

    @Test
    void 플레이어가_가진_카드의_합계를_계산한다() {
        Card clover = Card.of(CardDenomination.EIGHT, CardEmblem.CLOVER);
        Card spade = Card.of(CardDenomination.NINE, CardEmblem.SPADE);
        CardDeck cardDeck = new CardDeckBuilder()
                .cards(clover, spade)
                .build();
        Dealer dealer = Dealer.from(cardDeck);

        int actualScore = dealer.dealCardToPlayer(testPlayer)
                .getBasicScore();

        int expectedScore = CardBundle.from(List.of(clover, spade))
                .getBasicScore();
        Assertions.assertThat(actualScore)
                .isEqualTo(expectedScore);
    }

    @Test
    void 플레이어가_가진_카드의_합계가_21을_넘으면_버스트_된다() {
        Card king = Card.of(CardDenomination.KING, CardEmblem.CLOVER);
        Card jack = Card.of(CardDenomination.JACK, CardEmblem.SPADE);
        Card nine = Card.of(CardDenomination.NINE, CardEmblem.HEART);
        List<Card> cards = List.of(king, jack, nine);
        CardDeck cardDeck = new CardDeckBuilder()
                .cards(cards)
                .build();
        Dealer dealer = Dealer.from(cardDeck);

        dealer.dealCardToPlayer(testPlayer);
        dealer.hitCardToPlayer(testPlayer);

        Assertions.assertThat(testPlayer.isBusted()).isTrue();
    }

}
