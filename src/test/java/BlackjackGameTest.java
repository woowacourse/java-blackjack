import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.BlackjackAction;
import domain.BlackjackGame;
import domain.Card;
import domain.Cards;
import domain.Participant;
import domain.Participants;
import domain.TrumpCardNumber;
import domain.TrumpCardType;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {
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

    @DisplayName("게임에 참여하지 않은 참가자는 플레이 할 수 없다.")
    @Test
    void playByActionWithNotParticipateFailTest() {
        BlackjackGame blackjackGame = TestDataManager.getShuffledBlackjackGame();
        Participant otherPlayer = TestDataManager.getPlayerWithName("other");

        assertThatThrownBy(() -> blackjackGame.playByAction(otherPlayer, BlackjackAction.HIT))
                .isInstanceOf(IllegalStateException.class);
    }

    @Nested
    @DisplayName("플레이어의 상태와 액션으로 게임 진행 가능 여부를 확인 할 수 있다.")
    class isAbleToContinueTest {
        @DisplayName("액션이 HIT이고 player가 카드를 받을 수 있는 상태에만 진행 할 수 있다.")
        @Test
        void isAbleToContinueTrueTest() {
            BlackjackGame blackjackGame = TestDataManager.getShuffledBlackjackGame();
            Participant player = blackjackGame.getPlayers().get(0);
            assertThat(player.isAbleToReceiveCard()).isTrue();

            boolean ableToContinue = blackjackGame.isAbleToContinue(player, BlackjackAction.HIT);
            assertThat(ableToContinue).isTrue();
        }

        @DisplayName("player가 카드를 받을 수 있는 상태가 아니면 진행 할 수 없다.")
        @Test
        void isAbleToContinueFalseTestWhenPlayerIsNotAbleToReceiveCard() {
            BlackjackGame blackjackGame = TestDataManager.getShuffledBlackjackGame();
            Participant player = blackjackGame.getPlayers().get(0);
            player.receive(Cards.of(
                    new Card(TrumpCardType.CLUB, TrumpCardNumber.KING),
                    new Card(TrumpCardType.CLUB, TrumpCardNumber.QUEEN),
                    new Card(TrumpCardType.CLUB, TrumpCardNumber.JACK))
            );
            assertThat(player.isAbleToReceiveCard()).isFalse();

            boolean ableToContinue = blackjackGame.isAbleToContinue(player, BlackjackAction.HIT);
            assertThat(ableToContinue).isFalse();
        }

        @DisplayName("player가 카드를 받을 수 있지만 액션이 HOLD인 경우 진행 할 수 없다.")
        @Test
        void isAbleToContinueFalseTestWhenActionIsHold() {
            BlackjackGame blackjackGame = TestDataManager.getShuffledBlackjackGame();
            Participant player = blackjackGame.getPlayers().get(0);
            assertThat(player.isAbleToReceiveCard()).isTrue();

            boolean ableToContinue = blackjackGame.isAbleToContinue(player, BlackjackAction.HOLD);
            assertThat(ableToContinue).isFalse();
        }
    }

    @DisplayName("Action이 HIT인 경우 카드를 한 장 받는다.")
    @Test
    void playByActionHit() {
        BlackjackGame blackjackGame = TestDataManager.getShuffledBlackjackGame();
        Participant player = blackjackGame.getPlayers().get(0);
        List<Card> playerCards = player.getCards();
        assertThat(playerCards).hasSize(0);

        blackjackGame.playByAction(player, BlackjackAction.HIT);

        assertThat(playerCards).hasSize(1);
    }

    @DisplayName("Action이 HOLD인 경우 카드를 받지 않는다.")
    @Test
    void playByActionHold() {
        BlackjackGame blackjackGame = TestDataManager.getShuffledBlackjackGame();
        Participant player = blackjackGame.getPlayers().get(0);
        List<Card> playerCards = player.getCards();
        assertThat(playerCards).hasSize(0);

        blackjackGame.playByAction(player, BlackjackAction.HOLD);

        assertThat(playerCards).hasSize(0);
    }

}
