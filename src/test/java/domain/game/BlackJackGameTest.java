package domain.game;

import domain.card.Card;
import domain.card.Number;
import domain.card.Shape;
import domain.player.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class BlackJackGameTest {
    @Test
    @DisplayName("게임 시작 시 플레이어들에게 카드를 2장씩 나눠준다.")
    void whenStartingGame_thenPerPlayerHavingTwoCard() {
        BlackJackGame blackJackGame = new BlackJackGame("여우,아벨", cards -> cards);
        blackJackGame.giveTwoCardToPlayers();
    
        Player dealer = blackJackGame.getDealer();
        List<Player> participants = blackJackGame.getParticipants();
        
        assertAll(
                () -> assertThat(dealer.getCards())
                        .containsExactly(new Card(Shape.DIAMOND, Number.JACK), new Card(Shape.DIAMOND, Number.QUEEN)),
                () -> assertThat(participants.get(0).getCards())
                        .containsExactly(new Card(Shape.DIAMOND, Number.KING), new Card(Shape.DIAMOND, Number.TEN)),
                () -> assertThat(participants.get(1).getCards())
                        .containsExactly(new Card(Shape.DIAMOND, Number.NINE), new Card(Shape.DIAMOND, Number.EIGHT))
        );
    }
}