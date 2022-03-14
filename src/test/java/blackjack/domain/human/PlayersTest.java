package blackjack.domain.human;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardDeckGenerator;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @Test
    @DisplayName("Players 객체 생성 테스트")
    public void createPlayersTest() {
        Player player = Player.of(Name.of("test"));
        Players players = Players.of(List.of(player));
        assertThat(players.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("Players 카드 배분 테스트")
    public void giveCardToPlayersTest() {
        CardDeck cardDeck = CardDeckGenerator.generate();
        Player player1 = Player.of(Name.of("pobi"));
        Player player2 = Player.of(Name.of("jason"));
        Players players = Players.of(List.of(player1, player2));
        players.distributeCard(cardDeck);
        players.distributeCard(cardDeck);
        assertThat(player1.getCards().size()).isEqualTo(2);
        assertThat(player2.getCards().size()).isEqualTo(2);
    }
}
