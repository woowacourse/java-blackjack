package domain.participant;


import domain.card.Card;
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
        Player player = Player.from(ParticipantName.from("test"));
        Card card = Card.of(CardDenomination.EIGHT, CardEmblem.CLOVER);

        //when
        Hand hand = player.drawCards(List.of(card));

        //then
        Assertions.assertThat(hand).isEqualTo(Hand.from(List.of(card)));
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
        List<Card> cards = List.of(clover, spade);
        Hand origin = Hand.from(List.of(clover, spade));
        Player player = Player.from(ParticipantName.from("test"));

        // when
        Hand result = player.drawCards(cards);

        // then
        Assertions.assertThat(result.getBasicScore()).isEqualTo(origin.getBasicScore());
    }

    @Test
    void 플레이어가_가진_카드의_합계가_21을_넘으면_버스트_된다() {
        // given
        Card king = Card.of(CardDenomination.KING, CardEmblem.CLOVER);
        Card jack = Card.of(CardDenomination.JACK, CardEmblem.SPADE);
        Card nine = Card.of(CardDenomination.NINE, CardEmblem.HEART);
        List<Card> cards = List.of(king, jack, nine);
        Player player = Player.from(ParticipantName.from("test"));

        // when
        player.drawCards(cards);

        // then
        Assertions.assertThat(player.isBusted()).isTrue();
    }

}
