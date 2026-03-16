package domain.participant;

import domain.betting.BetAmount;
import domain.card.Card;
import domain.card.CardDeck;
import domain.card.CardDeckBuilder;
import domain.card.CardDenomination;
import domain.card.CardEmblem;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @Test
    void 게임_시작시_플레이어들에게_카드를_두장씩_나누어준다() {
        // given
        Players players = Players.from(List.of(
                ParticipantInitialInformation.of(ParticipantName.from("송송"), BetAmount.from(0)),
                ParticipantInitialInformation.of(ParticipantName.from("라텔"), BetAmount.from(0))
        ));
        CardDeck cardDeck = new CardDeckBuilder()
                .cards(Card.of(CardDenomination.ACE, CardEmblem.CLOVER),
                        Card.of(CardDenomination.TWO, CardEmblem.CLOVER),
                        Card.of(CardDenomination.EIGHT, CardEmblem.CLOVER),
                        Card.of(CardDenomination.NINE, CardEmblem.CLOVER))
                .build();

        // when
        Players afterInitialize = players.drawCards(cardDeck, 2);

        // then
        Assertions.assertThat(afterInitialize).isEqualTo(players);
    }
}
