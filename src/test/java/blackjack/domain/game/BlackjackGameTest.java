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
    @DisplayName("딜러는 카드를 더 받아야한다")
    void countDealerHit() {
        BlackjackGame blackjackGame = new BlackjackGame(new Participants(List.of(new Player("마루"))));
        Dealer dealer = blackjackGame.getParticipants().getDealer();

        dealer.initCards(List.of(new Card(Suit.DIAMOND, Denomination.THREE),
            new Card(Suit.HEART, Denomination.FIVE)));

        assertThat(blackjackGame.playDealerTurnAndReturnTurnCount()).isGreaterThanOrEqualTo(1);
    }
}
