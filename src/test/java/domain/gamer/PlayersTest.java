package domain.gamer;

import domain.card.*;
import domain.gamer.dto.GamerHandDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.CardDeckBuilder;

import java.util.List;

class PlayersTest {

    private final PlayerName testPlayerName  = new PlayerName("test");
    private final Player testPlayer = Player.from(testPlayerName);;

    @Test
    void 게임_시작시_플레이어들에게_카드를_두장씩_나누어준다() {
        Players players = Players.from(List.of(testPlayer));
        Card clover8 = Card.of(CardDenomination.EIGHT, CardEmblem.CLOVER);
        Card clover9 = Card.of(CardDenomination.NINE, CardEmblem.CLOVER);

        CardDeck cardDeck = new CardDeckBuilder()
                .cards(clover8, clover9)
                .build();
        Dealer dealer = Dealer.from(cardDeck);

        players.dealCardBundle(dealer);
        List<GamerHandDto> playersHand = players.getPlayers().stream()
                .map(GamerHandDto::from)
                .toList();

        Assertions.assertThat(playersHand.getFirst())
                .isEqualTo(GamerHandDto.from(testPlayer));
    }

}
