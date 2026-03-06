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
        Player songsong = Player.from(PlayerName.from("송송"));
        Players players = Players.from(List.of(songsong));
        Card clover8 = Card.of(CardDenomination.EIGHT, CardEmblem.CLOVER);
        Card clover9 = Card.of(CardDenomination.NINE, CardEmblem.CLOVER);

        CardDeck cardDeck = new CardDeckBuilder()
                .cards(clover8, clover9)
                .build();
        Dealer dealer = Dealer.from(cardDeck);

        players.dealCardBundle(dealer);
        List<PlayerHandDto> playersHand = players.getPlayersHand();
        Assertions.assertThat(playersHand.getFirst()).isEqualTo(PlayerHandDto.of(songsong));
    }

}
