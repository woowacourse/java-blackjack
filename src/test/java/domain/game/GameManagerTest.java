package domain.game;

import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class GameManagerTest {

    private Dealer dealer;
    private GameManager gameManager;
    private Map<Player, BettingMoney> bettingInfo;

    @BeforeEach
    void init() {
        dealer = Participant.createDealer();
        bettingInfo = Map.ofEntries(Map.entry(Player.create("pobi"), BettingMoney.create("10000")),
                Map.entry(Player.create("crong"), BettingMoney.create("20000")));
        gameManager = GameManager.create(dealer, bettingInfo, (card) -> card);
    }

    @Test
    @DisplayName("create()는 덱과 참가자 정보를 받으면 게임 관리자를 생성한다")
    void create_givenDeckAndParticipants_thenSuccess() {
        final GameManager gameManager = assertDoesNotThrow(() -> GameManager.create(dealer, bettingInfo, (card) -> card));

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
