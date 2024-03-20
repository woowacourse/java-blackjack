package model.participant;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import model.card.Card;
import model.card.Emblem;
import model.card.Number;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EntrantTest {
    private Entrant entrant;
    private Card initiaCard1;
    private Card initialCard2;
    private Card additionalCard1;
    private Card additionalCardForBust;

    @BeforeEach
    void setUp() {
        // Given
        initiaCard1 = Card.from(Number.NINE, Emblem.SPADE);
        initialCard2 = Card.from(Number.THREE, Emblem.SPADE);

        additionalCard1 = Card.from(Number.FOUR, Emblem.SPADE);
        additionalCardForBust = Card.from(Number.TEN, Emblem.HEART);

        Names names = new Names(List.of("도비"));

        entrant = generateEntrant(names, initiaCard1, initialCard2);
    }

    private Entrant generateEntrant(Names names, Card card1, Card card2) {
        List<Player> players = names.getPlayerNames()
                .stream()
                .map(name -> new Player(name, 1000, card1, card2))
                .toList();
        Dealer dealer = new Dealer(card1, card2);
        return new Entrant(dealer, players);
    }

    @Test
    @DisplayName("딜러 hit 후 딜러의 카드가 증가했는지 확인")
    void hitDealer_ShouldIncreaseDealersCards() {
        // When
        entrant.hitDealer(additionalCard1);

        // Then
        assertEquals(3, entrant.getDealer()
                .getCards()
                .size());
    }

    @Test
    @DisplayName("플레이어 hit 후 플레이어의 카드가 증가했는지 확인한다.")
    void hitPlayer_ShouldIncreasePlayersCards() {
        // When
        entrant.hitPlayer(additionalCard1);

        // Then
        assertEquals(3, entrant.getPlayers()
                .get(0)
                .getCards()
                .size());
    }

    @Test
    @DisplayName("사용 가능한 플레이어가 존재할 때, true를 반환한다.")
    void hasAvailablePlayer_ShouldReturnTrueIfPlayerCanHit() {
        // Then
        assertTrue(entrant.hasAvailablePlayer());
    }

    @Test
    @DisplayName("모든 Player가 turnover 혹은 bust 되었을 때, false를 반환한다.")
    void hasAvailablePlayer_ShouldReturnFalse_WhenAllPlayersAreBust() {
        // When
        entrant.hitPlayer(additionalCardForBust);

        // Then
        assertFalse(entrant.hasAvailablePlayer());
    }

    @Test
    @DisplayName("딜러가 카드를 받을 수 있을 때, true를 반환해야 한다")
    void isDealerHitAllowed_ShouldReturnTrue_WhenDealerCanHit() {

        assertTrue(entrant.isDealerHitAllowed());
    }

    @Test
    @DisplayName("딜러가 카드를 받을 수 없을 때, false를 반환해야 한다")
    void isDealerHitAllowed_ShouldReturnFalse_WhenDealerCanNotHit() {
        // When
        entrant.hitDealer(additionalCardForBust);

        assertFalse(entrant.isDealerHitAllowed());
    }

    @Test
    @DisplayName("첫 번째 플레이어가 hit할 수 있을 때 첫 번째 플레이어를 반환해야 한다")
    void getNextAvailablePlayer_ShouldReturnFirstPlayer_WhenFirstPlayerCanHit() {
        Player nextAvailablePlayer = entrant.getNextAvailablePlayer();

        assertAll(() -> {
            assertNotNull(nextAvailablePlayer);
            assertEquals("도비", nextAvailablePlayer.getName()
                    .getValue());
        });
    }

    @Test
    @DisplayName("플레이어가 히트할 수 없을 때 false를 반환해야 한다")
    void hasAvailablePlayer_ShouldReturnFalse_WhenNoPlayersCanHit() {
        // When
        entrant.hitPlayer(additionalCardForBust);

        // Then
        assertFalse(entrant.hasAvailablePlayer());
    }

    @Test
    @DisplayName("모든 플레이어의 배팅 결과가 집계를 통해 업데이트 되어야 한다")
    void aggregateBettingResult_ShouldUpdateMatchResultsForAllPlayers_WhenCalled() {

        assertDoesNotThrow(() -> entrant.aggregateBettingResult());
    }
}

