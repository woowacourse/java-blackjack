package blackjack.domain.game;

import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatNoException;

public class BlackjackGameTest {
    private Dealer dealer;
    private Participants participants;
    private Deck deck;

    @BeforeEach
    void setting() {
        dealer = new Dealer(new ArrayList<>());
        participants = new Participants(dealer, List.of("pobi", "crong"), new ArrayList<>());
        deck = new Deck();
    }

    @Test
    @DisplayName("생성자 테스트")
    void constructorTest() {
        assertThatNoException().isThrownBy(() -> new BlackjackGame(participants, deck));
    }

    @Test
    @DisplayName("모두에게 카드 두장을 나누어 주는지 테스트")
    void giveTwoCardEveryoneTest() {
        // given
        BlackjackGame blackjackGame = new BlackjackGame(participants, deck);

        // when
        blackjackGame.initialCardsToAllParticipant();

        // then
        assertThat(dealer.getHand().size()).isEqualTo(2);
        participants.getPlayers().forEach(player -> {
            assertThat(player.getHand().size()).isEqualTo(2);
        });
    }

    @Test
    @DisplayName("카드를 한 장만 뽑는 테스트")
    void drawCardTest() {
        // given
        BlackjackGame blackjackGame = new BlackjackGame(participants, deck);

        // when
        blackjackGame.drawCard(dealer);

        // then
        assertThat(dealer.getHand().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("카드를 여러장 뽑을 수 있는지 테스트")
    void drawMultipleCardTest() {
        // given
        BlackjackGame blackjackGame = new BlackjackGame(participants, deck);

        // when
        blackjackGame.drawCard(dealer, 10);

        // then
        assertThat(dealer.getHand().size()).isEqualTo(10);
    }

    @Test
    @DisplayName("참가자들 반환 테스트")
    void getParticipantsTest() {
        BlackjackGame blackjackGame = new BlackjackGame(participants, deck);

        assertThat(blackjackGame.getParticipants()).isEqualTo(participants);
    }
}
