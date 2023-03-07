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
    @DisplayName("getParticipantSize()는 참가자의 수를 반환한다.")
    void getParticipantSize_whenCall_thenReturnParticipantSize() {
        assertThat(gameManager.getParticipantSize())
                .isEqualTo(3);
    }

    @Test
    @DisplayName("giveCards()는 참가자의 순서를 받으면, 카드를 건네준다")
    void giveCards_givenParticipantOrder_thenSuccess() {
        assertThatCode(() -> gameManager.handFirstCards(0))
                .doesNotThrowAnyException();
    }
}
