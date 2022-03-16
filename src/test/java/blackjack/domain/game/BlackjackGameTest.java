package blackjack.domain.game;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;

public class BlackjackGameTest {

    @Test
    @DisplayName("블랙잭 게임 생성")
    void createBlackjackGame() {
        assertThatCode(() -> new BlackjackGame(new Participants(
            List.of(new Player("마루"), new Player("엔젤앤지")))))
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("플레이어 턴이 끝났는지 판별 - 딜러가 블랙잭일 때")
    void isPlayersTurnEnd() {
        Participants participants = new Participants(List.of(new Player("player")));
        Dealer dealer = participants.getDealer();
        dealer.initCards(List.of(new Card(Suit.DIAMOND, Denomination.ACE),
            new Card(Suit.HEART, Denomination.TEN)));

        BlackjackGame blackjackGame = new BlackjackGame(participants);

        assertThat(blackjackGame.isPlayersTurnEnd()).isTrue();
    }

    @Test
    @DisplayName("플레이어 턴이 끝났는지 판별 - 플레이어 턴이 모두 끝났을 때")
    void isPlayersTurnEnd2() {
        Player player = new Player("player");
        player.stay();
        Participants participants = new Participants(List.of(player));
        participants.getDealer().initCards(List.of(new Card(Suit.DIAMOND, Denomination.TEN),
            new Card(Suit.HEART, Denomination.TEN)));

        BlackjackGame blackjackGame = new BlackjackGame(participants);

        assertThat(blackjackGame.isPlayersTurnEnd()).isTrue();
    }

    @Test
    @DisplayName("딜러 턴이 끝났는지 판별")
    void isDealerTurnEnd() {
        Participants participants = new Participants(List.of(new Player("player")));
        participants.getDealer().initCards(List.of(new Card(Suit.DIAMOND, Denomination.TEN),
            new Card(Suit.HEART, Denomination.TEN)));

        BlackjackGame blackjackGame = new BlackjackGame(participants);

        assertThat(blackjackGame.isDealerTurnEnd()).isTrue();
    }
}
