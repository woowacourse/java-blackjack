package domain.game;

import domain.card.Deck;
import domain.participant.Participants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class GameManagerTest {

    private Deck deck;
    private Participants participants;
    private GameManager gameManager;

    @BeforeEach
    void init() {
        final List<String> playerNames = List.of("pobi", "conan");
        deck = Deck.create(targetCard -> targetCard);
        participants = Participants.create(playerNames);
        gameManager = GameManager.create(deck, participants);
    }

    @Test
    @DisplayName("create()는 덱과 참가자 정보를 받으면 게임 관리자를 생성한다")
    void create_givenDeckAndParticipants_thenSuccess() {
        final GameManager gameManager = assertDoesNotThrow(() -> GameManager.create(deck, participants));

        assertThat(gameManager)
                .isExactlyInstanceOf(GameManager.class);
    }

    @Test
    @DisplayName("giveCards()는 참가자의 순서를 받으면, 카드를 건네준다")
    void giveCards_givenParticipantOrder_thenSuccess() {
        assertThatCode(() -> gameManager.giveCards(0, 2))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("canDealerGiveCar는 호출하면 딜러가 카드를 한 장 더 받을지 여부를 반환한다")
    void canDealerGiveCard_whenCall_thenReturnCanGiveCard() {
        assertThatCode(gameManager::canDealerGiveCard)
                .doesNotThrowAnyException();
    }
}
