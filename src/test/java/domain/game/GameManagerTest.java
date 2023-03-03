package domain.game;

import domain.card.Deck;
import domain.participant.Participants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class GameManagerTest {

    private Deck deck;
    private List<String> playerNames;
    private Participants participants;

    @BeforeEach
    void init() {
        deck = Deck.create(targetCard -> targetCard);
        playerNames = List.of("pobi", "conan");
        participants = Participants.create(playerNames);
    }

    @Test
    @DisplayName("create()는 덱과 참가자 정보를 받으면 게임 관리자를 생성한다")
    void create_givenDeckAndParticipants_thenSuccess() {
        assertThatCode(() -> GameManager.create(deck, participants))
                .doesNotThrowAnyException();

        assertThat(GameManager.create(deck, participants))
                .isExactlyInstanceOf(GameManager.class);
    }

    @Test
    @DisplayName("giveCards()는 참가자의 순서를 받으면, 카드를 건네준다")
    void giveCards_givenParticipantOrder_thenSuccess() {
        // given
        GameManager gameManager = GameManager.create(deck, participants);

        // when, then
        assertThatCode(() -> gameManager.giveCards(0, 2))
                .doesNotThrowAnyException();
    }
}
