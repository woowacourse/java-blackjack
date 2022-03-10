package blackjack.domain.human;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.CardDeck;
import java.util.List;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class PlayersTest {

    @Test
    public void 생성() {
        Player player = Player.of("test");
        Players players = Players.of(List.of(player));
        assertThat(players.size()).isEqualTo(1);
    }

    @Test
    public void 플레이어_카드_배분_테스트() {
        Player player1 = Player.of("pobi");
        Player player2 = Player.of("jason");
        Players players = Players.of(List.of(player1, player2));
        CardDeck cardDeck = CardDeck.getInstance();
        players.giveCard(cardDeck);
        players.giveCard(cardDeck);
        assertThat(player1.getCards().size()).isEqualTo(2);
        assertThat(player2.getCards().size()).isEqualTo(2);
    }

    @Test
    public void 플레이어_이름() {
        Player player1 = Player.of("pobi");
        Player player2 = Player.of("jason");
        Players players = Players.of(List.of(player1, player2));
        CardDeck cardDeck = CardDeck.getInstance();
        players.giveCard(cardDeck);
        players.giveCard(cardDeck);
        assertThat(players.getPlayerNames())
                .isEqualTo("pobi, jason");
    }
}