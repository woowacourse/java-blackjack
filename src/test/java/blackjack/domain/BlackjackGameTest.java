package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BlackjackGameTest {

    private final Player player = new Player("kth990303", "1000");
    private final Dealer dealer = new Dealer();

    @Test
    @DisplayName("BlackJackGame 생성 테스트")
    void createBlackJackGame() {
        assertThat(initializeBlackJackGame()).isNotNull();
    }

    @Test
    @DisplayName("딜러가 카드를 받을 수 있는 경우 계속 카드를 받는지 테스트")
    void doDealerGame() {
        BlackjackGame blackjackGame = initializeBlackJackGame();
        Dealer dealerWhoFinishedGame = blackjackGame.doDealerGame();
        assertThat(dealerWhoFinishedGame.getScore()).isGreaterThan(16);
    }

    @Test
    @DisplayName("게임 시작시 최초 카드 분배 기능 테스트")
    void firstCardDispensing() {
        BlackjackGame blackjackGame = initializeBlackJackGame();

        blackjackGame.firstCardDispensing();

        assertThat(player.getCards().size()).isEqualTo(2);
    }

    private BlackjackGame initializeBlackJackGame() {
        Participants participants = new Participants(dealer, List.of(player));
        return new BlackjackGame(participants, Deck.create());
    }
}
