package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BlackjackGameTest {

    private BlackjackGame blackjackGame;
    private Players players;

    @BeforeEach
    void setUp() {
        players = Players.from(Names.from(List.of("pobi", "crong")));
        blackjackGame = new BlackjackGame(players);
    }

    @DisplayName("게임 참가자에게 카드를 나눠줄 수 있다.")
    @Test
    void giveCardToSuccessTest() {
        Player pobi = players.getPlayers().get(0);

        blackjackGame.giveCardTo(pobi);

        assertThat(pobi.cardSize()).isEqualTo(1);
    }

    @DisplayName("게임 시작 시, 모든 플레이어에게 두 장의 카드를 나눠준다.")
    @Test
    void giveInitialCardsSuccessTest() {
        Player pobi = players.getPlayers().get(0);
        Player crong = players.getPlayers().get(1);
        int initialCountOfCards = 2;

        blackjackGame.giveInitialCards();

        assertThat(pobi.cardSize()).isEqualTo(initialCountOfCards);
        assertThat(crong.cardSize()).isEqualTo(initialCountOfCards);
        assertThat(blackjackGame.getDealer().cardSize()).isEqualTo(initialCountOfCards);
    }

    @DisplayName("딜러는 카드의 합이 17이 넘을때 까지 추가 카드를 받아야 한다.")
    @Test
    void giveAdditionalCardToDealerSuccessTest() {
        blackjackGame.giveInitialCards();
        blackjackGame.giveAdditionalCardToDealer();

        Participant dealer = blackjackGame.getDealer();

        assertThat(dealer.calculateScore())
                .isGreaterThanOrEqualTo(17);
    }
}
