import static org.assertj.core.api.Assertions.assertThat;

import domain.BlackjackGame;
import domain.Card;
import domain.Participant;
import domain.Participants;
import domain.TrumpCardNumber;
import domain.TrumpCardType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {
    @DisplayName("게임 참가자에게 카드를 나눠줄 수 있다.")
    @Test
    void handOutCardToSuccessTest() {
        BlackjackGame blackjackGame = TestDataManager.getShuffledBlackjackGame();
        Participant pobi = blackjackGame.getPlayers().get(0);
        assertPlayerCardSize(pobi, 0);

        blackjackGame.handOutCardTo(pobi, 2);

        assertPlayerCardSize(pobi, 2);
    }

    @DisplayName("게임 시작 시, 모든 플레이어에게 두 장의 카드를 나눠준다.")
    @Test
    void handOutInitialCardsSuccessTest() {
        BlackjackGame blackjackGame = TestDataManager.getShuffledBlackjackGame();
        Participants participants = blackjackGame.getParticipants();
        assertPlayersCardSize(participants, 0);

        blackjackGame.handOutInitialCards();

        assertPlayersCardSize(participants, 2);
    }

    private void assertPlayerCardSize(Participant player, int size) {
        assertThat(player.getCards())
                .hasSize(size);
    }

    private void assertPlayersCardSize(Participants participants, int size) {
        participants.getParticipants().stream()
                .map(Participant::getCards)
                .forEach(cards -> assertThat(cards).hasSize(size));
    }

    @DisplayName("딜러는 카드의 합이 17보다 낮으면 카드를 추가로 받는다.")
    @Test
    void handOutAdditionalCardToDealerSuccessTestWhenUnderMoreCardLimit() {
        BlackjackGame blackjackGame = TestDataManager.getShuffledBlackjackGame();
        Participant dealer = blackjackGame.getDealer();
        dealer.receive(new Card(TrumpCardType.CLUB, TrumpCardNumber.KING));
        dealer.receive(new Card(TrumpCardType.CLUB, TrumpCardNumber.SIX));
        assertPlayerCardSize(dealer, 2);

        blackjackGame.handOutAdditionalCardToDealer();

        assertPlayerCardSize(dealer, 3);
    }

    @DisplayName("딜러는 카드의 합이 17보다 높으면 카드를 받을 수 없다.")
    @Test
    void handOutAdditionalCardToDealerSuccessTestWhenOverMoreCardLimit() {
        BlackjackGame blackjackGame = TestDataManager.getShuffledBlackjackGame();
        Participant dealer = blackjackGame.getDealer();
        dealer.receive(new Card(TrumpCardType.CLUB, TrumpCardNumber.KING));
        dealer.receive(new Card(TrumpCardType.CLUB, TrumpCardNumber.SEVEN));
        assertPlayerCardSize(dealer, 2);

        blackjackGame.handOutAdditionalCardToDealer();

        assertPlayerCardSize(dealer, 2);
    }
}
