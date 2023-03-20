package domain.blackjack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import common.TestDataGenerator;
import domain.card.Card;
import domain.card.Cards;
import domain.card.TrumpCardNumber;
import domain.card.TrumpCardType;
import domain.participant.Participant;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {

    private static final Card CLUB_KING = new Card(TrumpCardType.CLUB, TrumpCardNumber.KING);
    private static final Card CLUB_SIX = new Card(TrumpCardType.CLUB, TrumpCardNumber.SIX);
    private static final Card CLUB_SEVEN = new Card(TrumpCardType.CLUB, TrumpCardNumber.SEVEN);

    @DisplayName("게임 시작 시, 모든 플레이어와 딜러에게 두 장의 카드를 나눠준다.")
    @Test
    void handOutInitialCardsSuccessTest() {
        BlackjackGame blackjackGame = TestDataGenerator.getShuffledBlackjackGame();
        List<Participant> participants = blackjackGame.getParticipants();

        blackjackGame.handOutInitialCards();

        assertParticipantsCardSize(participants, 2);
    }

    private void assertParticipantsCardSize(List<Participant> participants, int size) {
        participants.stream()
                .map(Participant::getCards)
                .forEach(cards -> assertThat(cards.getCards()).hasSize(size));
    }

    @DisplayName("딜러는 카드의 합이 17보다 낮으면 카드를 추가로 받는다.")
    @Test
    void handOutAdditionalCardToDealerSuccessTestWhenUnderMoreCardLimit() {
        BlackjackGame blackjackGame = TestDataGenerator.getShuffledBlackjackGame();
        Participant dealer = blackjackGame.getDealer();
        dealer.startWith(Cards.of(CLUB_KING, CLUB_SIX));

        blackjackGame.handOutAdditionalCardToDealer();

        assertThat(dealer.getAdditionalCardsAmount())
                .isGreaterThan(0);
    }

    @DisplayName("딜러는 카드의 합이 17보다 높으면 카드를 받을 수 없다.")
    @Test
    void handOutAdditionalCardToDealerSuccessTestWhenOverMoreCardLimit() {
        BlackjackGame blackjackGame = TestDataGenerator.getShuffledBlackjackGame();
        Participant dealer = blackjackGame.getDealer();
        dealer.startWith(Cards.of(CLUB_KING, CLUB_SEVEN));

        blackjackGame.handOutAdditionalCardToDealer();

        assertThat(dealer.getAdditionalCardsAmount())
                .isEqualTo(0);
    }

    @DisplayName("게임에 참여하지 않은 참가자는 플레이 할 수 없다.")
    @Test
    void playByActionWithNotParticipateFailTest() {
        BlackjackGame blackjackGame = TestDataGenerator.getShuffledBlackjackGame();
        Participant otherPlayer = TestDataGenerator.getPlayerWithName("other");

        assertThatThrownBy(() -> blackjackGame.playByAction(otherPlayer, BlackjackAction.HIT))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("Action이 HIT인 경우 카드를 한 장 받는다.")
    @Test
    void playByActionHit() {
        BlackjackGame blackjackGame = TestDataGenerator.getShuffledBlackjackGame();
        Participant player = blackjackGame.getPlayers().getPlayers().get(0);
        player.startWith(Cards.of(CLUB_KING, CLUB_SEVEN));
        assertThat(player.isAbleToReceiveCard()).isTrue();
        blackjackGame.playByAction(player, BlackjackAction.HIT);

        assertThat(player.getAdditionalCardsAmount()).isEqualTo(1);
    }

    @DisplayName("Action이 HOLD인 경우 카드를 받지 않는다.")
    @Test
    void playByActionHold() {
        BlackjackGame blackjackGame = TestDataGenerator.getShuffledBlackjackGame();
        Participant player = blackjackGame.getPlayers().getPlayers().get(0);
        player.startWith(Cards.of(CLUB_KING, CLUB_SEVEN));

        assertThat(player.isAbleToReceiveCard()).isTrue();
        blackjackGame.playByAction(player, BlackjackAction.HOLD);

        assertThat(player.getAdditionalCardsAmount()).isEqualTo(0);
    }

    @Nested
    @DisplayName("플레이어의 상태와 액션으로 게임 진행 가능 여부를 확인 할 수 있다.")
    class isAbleToContinueTest {
        BlackjackGame blackjackGame;
        Participant gamePlayer;

        @BeforeEach
        void setUp() {
            blackjackGame = TestDataGenerator.getShuffledBlackjackGame();
            gamePlayer = blackjackGame.getPlayers().getPlayers().get(0);
        }

        @DisplayName("액션이 HIT이고 player가 카드를 받을 수 있는 상태에만 진행 할 수 있다.")
        @Test
        void isAbleToContinueTrueTest() {
            gamePlayer.startWith(Cards.of(
                    new Card(TrumpCardType.CLUB, TrumpCardNumber.KING),
                    new Card(TrumpCardType.CLUB, TrumpCardNumber.QUEEN))
            );
            assertThat(gamePlayer.isAbleToReceiveCard()).isTrue();

            boolean ableToContinue = blackjackGame.isAbleToContinue(gamePlayer, BlackjackAction.HIT);
            assertThat(ableToContinue).isTrue();
        }

        @DisplayName("player가 카드를 받을 수 있는 상태가 아니면 진행 할 수 없다.")
        @Test
        void isAbleToContinueFalseTestWhenPlayerIsNotAbleToReceiveCard() {
            gamePlayer.startWith(Cards.of(
                    new Card(TrumpCardType.CLUB, TrumpCardNumber.KING),
                    new Card(TrumpCardType.CLUB, TrumpCardNumber.QUEEN)
            ));
            gamePlayer.receive(new Card(TrumpCardType.CLUB, TrumpCardNumber.JACK));

            assertThat(gamePlayer.isAbleToReceiveCard()).isFalse();

            boolean ableToContinue = blackjackGame.isAbleToContinue(gamePlayer, BlackjackAction.HIT);
            assertThat(ableToContinue).isFalse();
        }

        @DisplayName("player가 카드를 받을 수 있지만 액션이 HOLD인 경우 진행 할 수 없다.")
        @Test
        void isAbleToContinueFalseTestWhenActionIsHold() {
            gamePlayer.startWith(Cards.of(
                    new Card(TrumpCardType.CLUB, TrumpCardNumber.KING),
                    new Card(TrumpCardType.CLUB, TrumpCardNumber.QUEEN))
            );
            assertThat(gamePlayer.isAbleToReceiveCard()).isTrue();

            boolean ableToContinue = blackjackGame.isAbleToContinue(gamePlayer, BlackjackAction.HOLD);
            assertThat(ableToContinue).isFalse();
        }
    }

}
