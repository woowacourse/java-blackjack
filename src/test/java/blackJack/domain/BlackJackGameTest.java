package blackJack.domain;

import blackJack.domain.card.Deck;
import blackJack.domain.participant.Dealer;
import blackJack.domain.participant.Participants;
import blackJack.domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BlackJackGameTest {

    private final Player player = new Player("kth990303");
    private final Dealer dealer = new Dealer();

    @Test
    @DisplayName("BlackJackGame 생성 테스트")
    void createBlackJackGame() {
        assertThat(initializeBlackJackGame()).isNotNull();
    }

    @Test
    @DisplayName("딜러가 카드를 받을 수 있는 경우 계속 카드를 받는지 테스트")
    void doDealerGame() {
        BlackJackGame blackJackGame = initializeBlackJackGame();
        Dealer dealerWhoFinishedGame = blackJackGame.doDealerGame();
        assertThat(dealerWhoFinishedGame.getScore()).isGreaterThan(16);
    }

    @Test
    @DisplayName("게임 시작시 최초 카드 분배 기능 테스트")
    void firstCardDispensing() {
        BlackJackGame blackJackGame = initializeBlackJackGame();

        blackJackGame.firstCardDispensing();

        assertThat(player.getCards().size()).isEqualTo(2);
    }

    private BlackJackGame initializeBlackJackGame() {
        Participants participants = new Participants(dealer, List.of(player));
        return new BlackJackGame(participants, Deck.createDeck());
    }
}
