package domain.player;

import domain.card.*;
import domain.player.dto.PlayerHandDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import domain.dealer.Dealer;

import java.util.List;

class PlayersTest {

    @Test
    void 게임_시작시_플레이어들에게_카드를_두장씩_나누어준다() {
        Players players = Players.from(List.of(PlayerName.from("송송"), PlayerName.from("라텔")));
        Card cloverAce = Card.of(CardDenomination.ACE, CardEmblem.CLOVER);
        Card clover2 = Card.of(CardDenomination.TWO, CardEmblem.CLOVER);
        Card clover8 = Card.of(CardDenomination.EIGHT, CardEmblem.CLOVER);
        Card clover9 = Card.of(CardDenomination.NINE, CardEmblem.CLOVER);

        CardDeck cardDeck = new CardDeckBuilder()
                .cards(cloverAce, clover2, clover8, clover9)
                .build();
        Dealer dealer = Dealer.of(cardDeck);

        Players afterInitialize = players.giveInitialCardBundle(dealer);
        Assertions.assertThat(afterInitialize).isEqualTo(players);
    }
}
