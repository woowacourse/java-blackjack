package domain;

import domain.Card.Card;
import domain.Card.CardNumber;
import domain.Card.CardShape;
import domain.Card.Deck;
import domain.game.Game;
import domain.user.Dealer;
import domain.user.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTest {
    
    @Test
    @DisplayName("참가자에게 카드를 지급한다.")
    void dealTest() {
        Game game = new Game("echo", new Deck());
        Player player = game.getParticipants().getPlayers().get(0);
        game.deal(player);
        Assertions.assertThat(player.getCards().get(0)).isEqualTo(new Card(CardNumber.ACE, CardShape.SPADE));
    }
    
    @Test
    @DisplayName("게임이 준비완료된 상태를 반환한다.")
    void readyResultTest() {
        Game game = new Game("echo", new Deck());
        game.start();
        Dealer dealer = game.getParticipants().getDealer();
        Player player = game.getParticipants().getPlayers().get(0);
        Assertions.assertThat(dealer.getReadyCards().size()).isEqualTo(1);
        Assertions.assertThat(player.getReadyCards().size()).isEqualTo(2);
    }
}
