package domain.game;

import domain.card.Deck;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Players;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class GameManagerTest {

    private Deck deck;
    private Dealer dealer;
    private Players players;
    private GameManager gameManager;

    @BeforeEach
    void init() {
        final List<String> playerNames = List.of("pobi", "conan");
        deck = Deck.create(targetCard -> targetCard);
        players = Players.create(playerNames);
        dealer = Participant.createDealer();
        gameManager = GameManager.create(dealer, players.getPlayers(), deck);
    }

    @Test
    @DisplayName("create()는 덱과 참가자 정보를 받으면 게임 관리자를 생성한다")
    void create_givenDeckAndParticipants_thenSuccess() {
        final GameManager gameManager = assertDoesNotThrow(() -> GameManager.create(dealer, players.getPlayers(), deck));

        assertThat(gameManager)
                .isExactlyInstanceOf(GameManager.class);
    }

    @Test
    @DisplayName("giveCards()는 호출하면, 카드를 건네준다")
    void giveCards_whenCall_thenSuccess() {
        assertThatCode(() -> gameManager.handFirstCards())
                .doesNotThrowAnyException();
    }
}
