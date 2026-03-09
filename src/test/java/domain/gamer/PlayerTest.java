package domain.gamer;

import domain.card.*;
import domain.card.exception.CardException;
import domain.gamer.exception.PlayerException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.CardDeckBuilder;

import java.util.List;

public class PlayerTest {

    PlayerName testPlayerName;
    Player testPlayer;

    @BeforeEach
    void setUp() {
        testPlayerName = new PlayerName("test");
        testPlayer = Player.from(testPlayerName);
    }

    @Test
    void 플레이어가_카드를_1장_받는다() {
        CardDeck cardDeck = new CardDeckBuilder()
                .cards(Card.of(CardDenomination.EIGHT, CardEmblem.CLOVER), Card.of(CardDenomination.NINE, CardEmblem.SPADE),
                        Card.of(CardDenomination.TWO, CardEmblem.SPADE), Card.of(CardDenomination.THREE, CardEmblem.HEART))
                .build();
        Dealer dealer = Dealer.from(cardDeck);

        dealer.hitCardToPlayer(testPlayer);
        Card card = Card.of(CardDenomination.EIGHT, CardEmblem.CLOVER);
        CardBundle playerCardBundle = testPlayer.getCardBundle();
        CardBundle answerCardBundle = CardBundle.from(List.of(card));

        Assertions.assertThat(playerCardBundle)
                .isEqualTo(answerCardBundle);
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
        Assertions.assertThat(testPlayer.getMyName())
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
        CardBundle origin = CardBundle.from(List.of(clover, spade));
        Dealer dealer = Dealer.from(cardDeck);

        CardBundle result = dealer.dealCardToPlayer(testPlayer);

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
        Dealer dealer = Dealer.from(cardDeck);

        dealer.dealCardToPlayer(testPlayer);
        dealer.hitCardToPlayer(testPlayer);

        Assertions.assertThat(testPlayer.isBusted()).isTrue();
    }

}
