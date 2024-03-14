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

class DealerTest {

    @Test
    @DisplayName("딜러는 카드 덱의 핸드가 16이하이면 hit할 수 있다.")
    void canHit_ShouldReturnTrue_WhenCardDeckIsNotBust() {
        Participant dealer = new Dealer();

        dealer.hitCard(Card.from(Number.TEN, Emblem.SPADE));
        dealer.hitCard(Card.from(Number.SIX, Emblem.HEART));

        assertTrue(dealer.canHit());
    }

    @Test
    @DisplayName("딜러는 카드 덱의 핸드가 16을 초과하면 hit 할 수 없다.")
    void canHit_ShouldReturnFalse_WhenCardDeckIsBust() {

        Participant dealer = new Dealer();
        dealer.hitCard(Card.from(Number.TEN, Emblem.SPADE));
        dealer.hitCard(Card.from(Number.SEVEN, Emblem.HEART));

        assertFalse(dealer.canHit());
    }

    @Test
    @DisplayName("Bust되지 않은 딜러와 Player일 때, 딜러의 점수가 높으면 WIN을 반환한다.")
    void calculateMatchResult_ShouldReturnWIN_WhenNotBustDealerWins() {
        // Given
        Participant dealer = new Dealer();
        dealer.hitCard(Card.from(Number.TEN, Emblem.SPADE));
        dealer.hitCard(Card.from(Number.SEVEN, Emblem.HEART));

        // When
        int playerHand = 16;

        // Then
        assertEquals(MatchResult.WIN, dealer.calculateMatchResult(playerHand));
    }

    @ParameterizedTest
    @EnumSource(value = Number.class)
    @DisplayName("Bust된 Player일 때, 딜러의 Bust 여부와 상관없이 WIN을 반환한다.")
    void calculateMatchResult_ShouldReturnWIN_WhenPlayerBust(Number number) {
        // Given
        Participant dealer = new Dealer();
        dealer.hitCard(Card.from(number, Emblem.HEART));
        dealer.hitCard(Card.from(number, Emblem.SPADE));
        dealer.hitCard(Card.from(number, Emblem.DIAMOND));

        // When
        int playerHand = 22;

        // Then
        assertEquals(MatchResult.WIN, dealer.calculateMatchResult(playerHand));
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
        int playerHand = player.cardDeck.calculateHand();

        // Then
        assertEquals(MatchResult.DRAW, dealer.calculateMatchResult(playerHand));
    }

    @ParameterizedTest
    @EnumSource(value = Number.class)
    @DisplayName("Player와 Bust되지 않았을 때, Dealer가 Bust이면 LOSE를 반환한다.")
    void calculateMatchResult_ShouldReturnLOSE_WhenDealerBustAndPlayerNotBust(Number number) {
        // Given
        Participant dealer = new Dealer();
        dealer.hitCard(Card.from(Number.TEN, Emblem.HEART));
        dealer.hitCard(Card.from(Number.TEN, Emblem.SPADE));
        dealer.hitCard(Card.from(Number.TWO, Emblem.SPADE));

        Participant player = new Player(new Name("도비"));
        player.hitCard(Card.from(number, Emblem.HEART));

        // When
        int playerHand = player.cardDeck.calculateHand();

        // Then
        assertEquals(MatchResult.LOSE, dealer.calculateMatchResult(playerHand));
    }

    @ParameterizedTest
    @EnumSource(value = Number.class)
    @DisplayName("Player와 딜러가 Bust되지 않았을 때, 딜러 Hand가 Player보다 작으면 LOSE를 반환한다.")
    void calculateMatchResult_ShouldReturnLOSE_WhenBothNotBustAndSmallerDealerHand(Number number) {
        // Given
        Participant dealer = new Dealer();
        dealer.hitCard(Card.from(number, Emblem.HEART));

        Participant player = new Player(new Name("도비"));
        player.hitCard(Card.from(number, Emblem.HEART));
        player.hitCard(Card.from(number, Emblem.DIAMOND));

        // When
        int playerHand = player.cardDeck.calculateHand();

        // Then
        assertEquals(MatchResult.LOSE, dealer.calculateMatchResult(playerHand));
    }
}
