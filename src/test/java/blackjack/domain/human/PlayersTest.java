package blackjack.domain.human;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.group.CardDeck;
import blackjack.domain.human.group.Players;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {
    
    @Test
    @DisplayName("플레이어 모음 생성 기능 검사")
    public void createTest() {
        Player player = Player.from("test");
        Players players = Players.from(List.of(player));
        assertThat(players.size()).isEqualTo(1);
    }
    
    @Test
    @DisplayName("복사 후 반환기능 검사")
    public void getTest() {
        // given
        Player player1 = Player.from("pobi");
        Player player2 = Player.from("jason");
        Players players = Players.from(List.of(player1, player2));
        CardDeck cardDeck = CardDeck.newInstance();
        
        // when
        player2 = Player.from("제이슨");
        players.giveCard(cardDeck);
        players.giveCard(cardDeck);
        
        // then
        assertThat(players.get())
                .extracting("name")
                .contains("pobi", "jason");
    }
    
    @Test
    @DisplayName("플레이어들 카드 배분 기능 검사")
    public void giveCardTest() {
        // given
        Player player1 = Player.from("pobi");
        Player player2 = Player.from("jason");
        Players players = Players.from(List.of(player1, player2));
        CardDeck cardDeck = CardDeck.newInstance();
        
        // when
        players.giveCard(cardDeck);
        players.giveCard(cardDeck);
        
        // then
        assertThat(player1.getCards().size()).isEqualTo(2);
        assertThat(player2.getCards().size()).isEqualTo(2);
    }
    
    @Test
    @DisplayName("플레이어들 이름 반환 기능 검사")
    public void getPlayerNamesTest() {
        // given
        Player player1 = Player.from("pobi");
        Player player2 = Player.from("jason");
        Players players = Players.from(List.of(player1, player2));
        CardDeck cardDeck = CardDeck.newInstance();
        
        // when
        players.giveCard(cardDeck);
        players.giveCard(cardDeck);
        
        // then
        assertThat(players.getPlayerNames()).contains("pobi", "jason");
    }
}
