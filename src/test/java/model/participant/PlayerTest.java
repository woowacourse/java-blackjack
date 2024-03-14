package model.participant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import model.card.Card;
import model.card.Emblem;
import model.card.Number;
import model.casino.MatchResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class PlayerTest {

    @Test
    @DisplayName("플레이어는 카드 덱이 Bust되지 않았으면 hit할 수 있다.")
    void canHit_ShouldReturnTrue_WhenCardDeckIsNotBust() {
        Participant player = new Player(new Name("name"));

        player.hitCard(Card.from(Number.TEN, Emblem.SPADE));
        player.hitCard(Card.from(Number.NINE, Emblem.HEART));
        player.hitCard(Card.from(Number.TWO, Emblem.HEART));

        assertTrue(player.canHit());
    }

    @Test
    @DisplayName("플레이어는 카드 덱이 Bust되면 hit할 수 없다.")
    void canHit_ShouldReturnFalse_WhenCardDeckIsBust() {
        Participant player = new Player(new Name("name"));

        player.hitCard(Card.from(Number.TEN, Emblem.SPADE));
        player.hitCard(Card.from(Number.NINE, Emblem.HEART));
        player.hitCard(Card.from(Number.THREE, Emblem.HEART));

        assertFalse(player.canHit());
    }

    @Test
    @DisplayName("Bust되지 않은 딜러와 Player일 때, 딜러의 점수가 높으면 LOSE을 반환한다.")
    void calculateMatchResult_ShouldReturnLOSE_WhenNotBustDealerWins() {
        // Given
        Participant player = new Player(new Name("도비"));
        player.hitCard(Card.from(Number.TEN, Emblem.SPADE));
        player.hitCard(Card.from(Number.SEVEN, Emblem.HEART));

        // When
        int dealerHand = 18;

        // Then
        assertEquals(MatchResult.LOSE, player.calculateMatchResult(dealerHand));
    }

    @Test
    @DisplayName("Bust된 Player일 때, 딜러의 Bust 여부와 상관없이 LOSE을 반환한다.")
    void calculateMatchResult_ShouldReturnLOSE_WhenPlayerBust() {
        // Given
        Participant player = new Player(new Name("도비"));
        player.hitCard(Card.from(Number.EIGHT, Emblem.HEART));
        player.hitCard(Card.from(Number.EIGHT, Emblem.SPADE));
        player.hitCard(Card.from(Number.EIGHT, Emblem.DIAMOND));

        // When
        int dealerHand = 22;

        // Then
        assertEquals(MatchResult.LOSE, player.calculateMatchResult(dealerHand));
    }

    @ParameterizedTest
    @EnumSource(value = Number.class)
    @DisplayName("Player와 딜러가 Bust되지 않았을 때, Hand가 같으면 DRAW를 반환한다.")
    void calculateMatchResult_ShouldReturnDRAW_WhenSameHandAndBothNotBust(Number number) {
        // Given
        Participant dealer = new Dealer();
        dealer.hitCard(Card.from(number, Emblem.HEART));
        dealer.hitCard(Card.from(number, Emblem.SPADE));

        Participant player = new Player(new Name("도비"));
        player.hitCard(Card.from(number, Emblem.HEART));
        player.hitCard(Card.from(number, Emblem.SPADE));

        // When
        int dealerHand = dealer.cardDeck.calculateHand();

        // Then
        assertEquals(MatchResult.DRAW, player.calculateMatchResult(dealerHand));
    }

    @ParameterizedTest
    @EnumSource(value = Number.class)
    @DisplayName("Player와 Bust되지 않았을 때, Dealer가 Bust이면 WIN를 반환한다.")
    void calculateMatchResult_ShouldReturnWIN_WhenDealerBustAndPlayerNotBust(Number number) {
        // Given
        Participant dealer = new Dealer();
        dealer.hitCard(Card.from(Number.TEN, Emblem.HEART));
        dealer.hitCard(Card.from(Number.TEN, Emblem.SPADE));
        dealer.hitCard(Card.from(Number.TWO, Emblem.SPADE));

        Participant player = new Player(new Name("도비"));
        player.hitCard(Card.from(number, Emblem.HEART));

        // When
        int dealerHand = dealer.cardDeck.calculateHand();

        // Then
        assertEquals(MatchResult.WIN, dealer.calculateMatchResult(dealerHand));
    }

    @ParameterizedTest
    @EnumSource(value = Number.class)
    @DisplayName("Player와 딜러가 Bust되지 않았을 때, 딜러 Hand가 Player보다 작으면 WIN를 반환한다.")
    void calculateMatchResult_ShouldReturnWIN_WhenBothNotBustAndSmallerDealerHand(Number number) {
        // Given
        Participant dealer = new Dealer();
        dealer.hitCard(Card.from(number, Emblem.HEART));

        Participant player = new Player(new Name("도비"));
        player.hitCard(Card.from(number, Emblem.HEART));
        player.hitCard(Card.from(number, Emblem.DIAMOND));

        // When
        int dealerHand = dealer.cardDeck.calculateHand();

        // Then
        assertEquals(MatchResult.WIN, player.calculateMatchResult(dealerHand));
    }
}
