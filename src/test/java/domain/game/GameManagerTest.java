package domain.game;

import domain.card.Card;
import domain.card.Deck;
import domain.card.RandomUniqueCardSelector;
import domain.participant.Participants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class GameManagerTest {

    private final Deck deck = Deck.create(new RandomUniqueCardSelector());
    private final Participants participants = Participants.create(List.of("a", "b"));

    @Test
    @DisplayName("create()는 덱과 참가자 정보를 받으면 게임 관리자를 생성한다")
    void create_givenDeckAndParticipants_thenSuccess() {
        final GameManager gameManager = assertDoesNotThrow(() -> GameManager.create(deck, participants));

        assertThat(gameManager)
                .isExactlyInstanceOf(GameManager.class);
    }

    @Test
    @DisplayName("giveStartCards()는 호출하면 모든 참가자들에게 카드를 2장씩 건네준다")
    void giveCards_whenCall_thenSuccess() {
        final GameManager gameManager = GameManager.create(deck, participants);

        assertThatCode(gameManager::giveStartCards)
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("givePlayerCard()는 플레이어 순서를 전달하면 해당 플레이어에게 카드를 한 장 건네준다")
    void givePlayerCard_givenPlayerOrder_thenAddCardToPlayer() {
        // given
        final GameManager gameManager = GameManager.create(deck, participants);

        // when
        gameManager.givePlayerCard(0);

        // then
        final List<Card> playerCard = gameManager.findPlayerCardsByOrder(0);
        assertThat(playerCard)
                .hasSize(1);
    }

    @RepeatedTest(10)
    @DisplayName("drawCardsForDealer()은 호출하면 딜러에게 카드를 주고 그 수를 반환한다")
    void drawCardsForDealer_whenCall_thenReturnAddCardSize() {
        // given
        final GameManager gameManager = GameManager.create(deck, participants);

        // when
        final int actual = gameManager.drawCardsForDealer();

        // then
        assertThat(actual)
                .isGreaterThanOrEqualTo(2);
    }

    @Nested
    @DisplayName("canPlayerDrawByOrder() 테스트")
    class CanPlayerDrawByOrderMethodTest {

        private final GameManager gameManager = GameManager.create(deck, participants);

        @Test
        @DisplayName("플레이어의 카드가 Bust 상태가 아니라면 카드를 더 뽑을 수 있음을 알려준다")
        void canDrawByPlayerOrder_whenCallNotBustPlayerOrder_thenReturnTrue() {
            final boolean actual = gameManager.canPlayerDrawByOrder(0);

            assertThat(actual)
                    .isTrue();
        }

        @Test
        @DisplayName("플레이어의 카드가 Bust 상태라면 카드를 더 뽑을 수 없음을 알려준다")
        void canDrawByPlayerOrder_whenCallBustPlayerOrder_thenReturnFalse() {
            // given
            int count = 0;
            while (count++ < 52) {
                gameManager.givePlayerCard(0);
            }

            // when
            final boolean actual = gameManager.canPlayerDrawByOrder(0);

            // then
            assertThat(actual)
                    .isFalse();
        }
    }

    @ParameterizedTest(name = "findPlayerNameByOrder()은 플레이어 순서를 건네주면 이름을 반환한다")
    @CsvSource(value = {"0:a", "1:b"}, delimiter = ':')
    void findPlayerNameByOrder_givenPlayerOrder_thenReturnName(final int playerOrder, final String expected) {
        final GameManager gameManager = GameManager.create(deck, participants);
        final String actual = gameManager.findPlayerNameByOrder(playerOrder);

        assertThat(actual)
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("findDealerName()은 호출하면 딜러의 이름을 반환한다")
    void findDealerName_whenCall_thenReturnDealerName() {
        // given
        final GameManager gameManager = GameManager.create(deck, participants);

        // when
        final String dealerName = gameManager.findDealerName();

        // then
        assertThat(dealerName)
                .isEqualTo("딜러");
    }

    @Test
    @DisplayName("findPlayerCardsByOrder()은 플레이어 순서를 건네주면 해당 플레이어의 카드를 반환한다")
    void findPlayerCardsByOrder_givenPlayerOrder_thenReturnPlayerCards() {
        // given
        final GameManager gameManager = GameManager.create(deck, participants);
        gameManager.givePlayerCard(0);

        // when
        final List<Card> playerCards = gameManager.findPlayerCardsByOrder(0);

        // then
        assertThat(playerCards)
                .hasSize(1);
    }
}
