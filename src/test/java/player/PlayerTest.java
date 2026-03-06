package player;

import domain.card.*;
import domain.dealer.Dealer;
import domain.player.Player;
import domain.player.PlayerName;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PlayerTest {

    @Test
    void 플레이어가_카드를_1장_받는다() {
        CardDeck cardDeck = new CardDeckBuilder()
                .cards(Card.of(CardDenomination.EIGHT, CardEmblem.CLOVER), Card.of(CardDenomination.NINE, CardEmblem.SPADE),
                        Card.of(CardDenomination.TWO, CardEmblem.SPADE), Card.of(CardDenomination.THREE, CardEmblem.HEART))
                .build();
        Dealer dealer = Dealer.of(cardDeck);
        Player player = Player.from(PlayerName.from("test"));

        dealer.hitCardToPlayer(player);
        Card card = Card.of(CardDenomination.EIGHT, CardEmblem.CLOVER);

        Assertions.assertThat(player.hasCard(card)).isTrue();
    }

    @Test
    void 덱에_카드가_없으면_플레이어는_카드를_받을_수_없다() {
        CardDeck cardDeck = new CardDeckBuilder()
                .build();

        Dealer dealer = Dealer.of(cardDeck);
        Player player = Player.from(PlayerName.from("test"));

        Assertions.assertThatThrownBy(() -> {
            dealer.hitCardToPlayer(player);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 플레이어를_생성한다() {
        String name = "test1";
        Player player = Player.from(PlayerName.from(name));

        Assertions.assertThat(player.toDisplayMyName()).isEqualTo(name);
    }

    @Test
    void 이름이_5자가_넘으면_예외가_발생한다() {
        String overFiveLengthName = "testtest";

        Assertions.assertThatThrownBy(() -> {
            Player.from(PlayerName.from(overFiveLengthName));
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 플레이어가_가진_카드의_합계를_계산한다() {
        Card clover = Card.of(CardDenomination.EIGHT, CardEmblem.CLOVER);
        Card spade = Card.of(CardDenomination.NINE, CardEmblem.SPADE);
        CardDeck cardDeck = new CardDeckBuilder()
                .cards(clover, spade)
                .build();
        CardBundle origin = CardBundle.from(List.of(clover, spade));
        Dealer dealer = Dealer.of(cardDeck);
        Player player = Player.from(PlayerName.from("test"));

        CardBundle result = dealer.dealCardToPlayer(player);

        Assertions.assertThat(result.getBasicScore()).isEqualTo(origin.getBasicScore());
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
        Dealer dealer = Dealer.of(cardDeck);
        Player player = Player.from(PlayerName.from("test"));

        dealer.dealCardToPlayer(player);
        dealer.hitCardToPlayer(player);

        Assertions.assertThat(player.isBusted()).isTrue();
    }

}
