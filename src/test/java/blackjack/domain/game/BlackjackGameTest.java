package blackjack.domain.game;

import blackjack.domain.card.Deck;
import blackjack.domain.card.DeckMaker;
import blackjack.domain.cardPicker.TestCardPicker;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatNoException;

public class BlackjackGameTest {
    private Dealer dealer;
    private Participants participants;
    private Deck deck;
    private static final DeckMaker DECK_MAKER = new DeckMaker();

    @BeforeEach
    void setting() {
        dealer = new Dealer();
        participants = new Participants(dealer, List.of("pobi", "crong"));
        deck = new Deck(DECK_MAKER.makeDeck(), new TestCardPicker());
    }

    @Test
    @DisplayName("생성자 테스트")
    void constructorTest() {
        assertThatNoException().isThrownBy(() -> new BlackjackGame(participants, deck));
    }

    @Test
    @DisplayName("모두에게 카드 두장을 나누어 주는지 테스트")
    void giveTwoCardEveryoneTest() {
        BlackjackGame blackjackGame = new BlackjackGame(participants, deck);
        blackjackGame.dealTwoCardsToAllParticipant();
        assertThat(dealer.getCards().size()).isEqualTo(2);
        participants.getPlayers().forEach(player -> {
            assertThat(player.getCards().size()).isEqualTo(2);
        });
    }

    @Test
    @DisplayName("카드를 뽑는 테스트")
    void drawCardTest() {
        BlackjackGame blackjackGame = new BlackjackGame(participants, deck);
        blackjackGame.drawCard(dealer);

        assertThat(dealer.getCards().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("참가자들 반환 테스트")
    void getParticipantsTest() {
        BlackjackGame blackjackGame = new BlackjackGame(participants, deck);

        assertThat(blackjackGame.getParticipants()).isEqualTo(participants);
    }
}
