package domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import domain.card.Card;
import domain.card.Deck;
import domain.card.RandomUniqueCardSelector;
import domain.participant.Participants;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class GameManagerTest {

    private final Deck deck = Deck.create(new RandomUniqueCardSelector());
    private final Participants participants = Participants.create(List.of("a", "b"), ignored -> 1000);

    @Test
    @DisplayName("create()는 덱과 참가자 정보를 받으면 게임 관리자를 생성한다")
    void create_givenDeckAndParticipants_thenSuccess() {
        final GameManager gameManager = assertDoesNotThrow(() -> GameManager.create(deck, participants));

        assertThat(gameManager)
                .isExactlyInstanceOf(GameManager.class);
    }

    @ParameterizedTest(name = "giveStartCardsForPlayer()는 플레이어의 이름을 전달하면 플레이어에게 카드를 2장씩 건네준다")
    @ValueSource(strings = {"a", "b"})
    void giveStartCardsForPlayer_givenPlayerName_thenSuccess(final String playerName) {
        final GameManager gameManager = GameManager.create(deck, participants);

        assertThatCode(() -> gameManager.giveStartCardsForPlayer(playerName))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("giveStartCardsForDealer()는 호출하면 딜러에게 카드를 2장 건네준다")
    void giveStartCardsForDealer_whenCall_thenSuccess() {
        final GameManager gameManager = GameManager.create(deck, participants);

        assertThatCode(gameManager::giveStartCardsForDealer)
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("givePlayerCard()는 플레이어 이름를 전달하면 해당 플레이어에게 카드를 한 장 건네준다")
    void givePlayerCard_givenPlayerName_thenAddCardToPlayer() {
        // given
        final GameManager gameManager = GameManager.create(deck, participants);

        // when
        gameManager.addCardForPlayer("a");

        // then
        final List<Card> playerCard = gameManager.getPlayerCard("a");

        assertThat(playerCard)
                .hasSize(1);
    }

    @RepeatedTest(10)
    @DisplayName("drawCardsForDealer()은 호출하면 딜러에게 카드를 주고 그 수를 반환한다")
    void drawCardsForDealer_whenCall_thenReturnAddCardSize() {
        // given
        final GameManager gameManager = GameManager.create(deck, participants);

        // when
        final int actual = gameManager.playDealerTurn();

        // then
        assertThat(actual)
                .isPositive();
    }

    @Nested
    @DisplayName("canDraw() 테스트")
    class CanPlayerDrawByOrderMethodTest {

        private final GameManager gameManager = GameManager.create(deck, participants);
        private final String playerName = "a";

        @Test
        @DisplayName("플레이어의 카드가 Bust 상태가 아니라면 카드를 더 뽑을 수 있음을 알려준다")
        void canDrawByPlayerOrder_whenCallNotBustPlayerOrder_thenReturnTrue() {
            final boolean actual = gameManager.canDraw(playerName);

            assertThat(actual)
                    .isTrue();
        }

        @Test
        @DisplayName("플레이어의 카드가 Bust 상태라면 카드를 더 뽑을 수 없음을 알려준다")
        void canDrawByPlayerOrder_whenCallBustPlayerOrder_thenReturnFalse() {
            // given
            int count = 0;
            while (count++ < 52) {
                gameManager.addCardForPlayer(playerName);
            }

            // when
            final boolean actual = gameManager.canDraw(playerName);

            // then
            assertThat(actual)
                    .isFalse();
        }
    }
}
