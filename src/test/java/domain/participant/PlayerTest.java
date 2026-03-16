package domain.participant;

import static config.BlackjackGameConstant.DEFAULT_CARD_DRAW_COUNT;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.CardDeckBuilder;
import domain.card.CardDenomination;
import domain.card.CardEmblem;
import domain.card.Hand;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    void 플레이어가_카드를_1장_받는다() {
        //given
        CardDeck cardDeck = new CardDeckBuilder()
                .cards(Card.of(CardDenomination.EIGHT, CardEmblem.CLOVER),
                        Card.of(CardDenomination.NINE, CardEmblem.SPADE),
                        Card.of(CardDenomination.TWO, CardEmblem.SPADE),
                        Card.of(CardDenomination.THREE, CardEmblem.HEART))
                .build();
        Player player = Player.from(ParticipantName.from("test"));
        Card card = Card.of(CardDenomination.EIGHT, CardEmblem.CLOVER);

        //when
        Hand hand = player.drawCards(cardDeck, DEFAULT_CARD_DRAW_COUNT);

        //then
        Assertions.assertThat(hand).isEqualTo(Hand.from(List.of(card)));
    }

    @Test
    void 덱에_카드가_없으면_플레이어는_카드를_받을_수_없다() {
        // given
        CardDeck cardDeck = new CardDeckBuilder()
                .build();
        Player player = Player.from(ParticipantName.from("test"));

        // when & then
        Assertions.assertThatThrownBy(() -> {
            player.drawCards(cardDeck, DEFAULT_CARD_DRAW_COUNT);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 플레이어를_생성한다() {
        // given
        String name = "test1";

        // when
        Player player = Player.from(ParticipantName.from(name));

        // then
        Assertions.assertThat(player.getName().name()).isEqualTo(name);
    }

    @Test
    void 이름이_5자가_넘으면_예외가_발생한다() {
        // given
        String overFiveLengthName = "testtest";

        // when & then
        Assertions.assertThatThrownBy(() -> ParticipantName.from(overFiveLengthName))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 플레이어가_가진_카드의_합계를_계산한다() {
        // given
        Card clover = Card.of(CardDenomination.EIGHT, CardEmblem.CLOVER);
        Card spade = Card.of(CardDenomination.NINE, CardEmblem.SPADE);
        CardDeck cardDeck = new CardDeckBuilder()
                .cards(clover, spade)
                .build();
        Hand origin = Hand.from(List.of(clover, spade));
        Player player = Player.from(ParticipantName.from("test"));

        // when
        Hand result = player.drawCards(cardDeck, 2);

        // then
        Assertions.assertThat(result.getBasicScore()).isEqualTo(origin.getBasicScore());
    }

    @Test
    void 플레이어가_가진_카드의_합계가_21을_넘으면_버스트_된다() {
        // given
        Card king = Card.of(CardDenomination.KING, CardEmblem.CLOVER);
        Card jack = Card.of(CardDenomination.JACK, CardEmblem.SPADE);
        Card nine = Card.of(CardDenomination.NINE, CardEmblem.HEART);
        CardDeck cardDeck = new CardDeckBuilder()
                .cards(List.of(king, jack, nine))
                .build();
        Player player = Player.from(ParticipantName.from("test"));

        // when
        player.drawCards(cardDeck, 3);

        // then
        Assertions.assertThat(player.isBusted()).isTrue();
    }

}
