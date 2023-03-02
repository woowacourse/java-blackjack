package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

class BlackjackGameTest {

    private Dealer dealer;
    private Player player1;
    private Player player2;
    private Participants participants;
    private BlackjackGame blackjackGame;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
        player1 = new Player("a");
        player2 = new Player("b");
        participants = new Participants(List.of(dealer, player1, player2));
        blackjackGame = new BlackjackGame(participants);
    }

    @Test
    @DisplayName("시작하면 Participants 모두에게 2장의 카드를 나눠준다.")
    void distributeTwoCardsTest() {
        // when
        blackjackGame.distributeTwoCards();

        // then
        assertAll(
                () -> assertThat(dealer.getCards().getCards().size()).isEqualTo(2),
                () -> assertThat(player1.getCards().getCards().size()).isEqualTo(2),
                () -> assertThat(player2.getCards().getCards().size()).isEqualTo(2)
        );
    }

    @Test
    @DisplayName("참가자에게 한 장의 카드를 더 주는 기능 테스트")
    void giveOneMoreCardTest() {
        // when
        blackjackGame.giveOneMoreCard(player1);

        // then
        assertThat(player1.getCards().getCards().size()).isEqualTo(1);
    }
}
