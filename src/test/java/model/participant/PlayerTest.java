package model.participant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import model.card.Card;
import model.card.Emblem;
import model.card.Number;
import model.participant.state.MatchState;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class PlayerTest {

    @Test
    @DisplayName("플레이어는 카드 덱이 Bust되지 않았으면 hit할 수 있다.")
    void canHit_ShouldReturnTrue_WhenCardDeckIsNotBust() {
        Card card1 = Card.from(Number.TEN, Emblem.SPADE);
        Card card2 = Card.from(Number.NINE, Emblem.HEART);
        Player player = new Player(new Name("name"),0, card1, card2);

        player.hitCard(Card.from(Number.TWO, Emblem.HEART));

        assertTrue(player.canHit());
    }

    @Test
    @DisplayName("플레이어는 카드 덱이 Bust되면 hit할 수 없다.")
    void canHit_ShouldReturnFalse_WhenCardDeckIsBust() {
        Card card1 = Card.from(Number.TEN, Emblem.SPADE);
        Card card2 = Card.from(Number.NINE, Emblem.HEART);
        Player player = new Player(new Name("name"),0, card1, card2);

        player.hitCard(Card.from(Number.THREE, Emblem.HEART));

        assertFalse(player.canHit());
    }

    @Test
    @DisplayName("Bust되지 않은 딜러와 Player일 때, 딜러의 점수가 높으면 LOSE을 반환한다.")
    void calculateMatchResult_ShouldReturnLOSE_WhenNotBustDealerWins() {
        // Given
        Card card1 = Card.from(Number.TEN, Emblem.SPADE);
        Card card2 = Card.from(Number.SEVEN, Emblem.HEART);
        Player player = new Player(new Name("name"),0, card1, card2);

        // When
        int dealerHand = 18;

        // Then
        assertEquals(MatchState.LOSE, player.determineMatchResult(dealerHand));
    }

    @Test
    @DisplayName("Bust된 Player일 때, 딜러의 Bust 여부와 상관없이 LOSE을 반환한다.")
    void calculateMatchResult_ShouldReturnLOSE_WhenPlayerBust() {
        // Given
        Card card1 = Card.from(Number.EIGHT, Emblem.SPADE);
        Card card2 = Card.from(Number.EIGHT, Emblem.HEART);
        Player player = new Player(new Name("name"),0, card1, card2);

        player.hitCard(Card.from(Number.EIGHT, Emblem.DIAMOND));

        // When
        int dealerHand = 22;

        // Then
        assertEquals(MatchState.LOSE, player.determineMatchResult(dealerHand));
    }

    @ParameterizedTest
    @EnumSource(value = Number.class)
    @DisplayName("Player와 딜러가 Bust되지 않았을 때, Hand가 같으면 DRAW를 반환한다.")
    void calculateMatchResult_ShouldReturnDRAW_WhenSameHandAndBothNotBust(Number number) {
        // Given
        Card card1 = Card.from(number, Emblem.HEART);
        Card card2 = Card.from(number, Emblem.SPADE);
        Dealer dealer = new Dealer(card1, card2);
        Player player = new Player(new Name("도비"),0, card1, card2);

        // When
        int dealerHand = dealer.cardDeck.calculateHand();

        // Then
        assertEquals(MatchState.DRAW, player.determineMatchResult(dealerHand));
    }

    @ParameterizedTest
    @EnumSource(value = Number.class)
    @DisplayName("Player와 Bust되지 않았을 때, Dealer가 Bust이면 WIN를 반환한다.")
    void calculateMatchResult_ShouldReturnWIN_WhenDealerBustAndPlayerNotBust(Number number) {
        // Given
        Card card1 = Card.from(Number.TEN, Emblem.HEART);
        Card card2 = Card.from(Number.TEN, Emblem.SPADE);
        Dealer dealer = new Dealer(card1, card2);
        dealer.hitCard(Card.from(Number.TWO, Emblem.SPADE));

        Player player = new Player(new Name("도비"),0, card1, card2);

        // When
        int dealerHand = dealer.cardDeck.calculateHand();

        // Then
        assertEquals(MatchState.WIN, player.determineMatchResult(dealerHand));
    }

    @ParameterizedTest
    @EnumSource(value = Number.class)
    @DisplayName("Player와 딜러가 Bust되지 않았을 때, 딜러 Hand가 Player보다 작으면 WIN를 반환한다.")
    void calculateMatchResult_ShouldReturnWIN_WhenBothNotBustAndSmallerDealerHand(Number number) {
        // Given
        Card card1 = Card.from(number, Emblem.HEART);
        Card card2 = Card.from(Number.TWO, Emblem.SPADE);
        Card card3 = Card.from(Number.THREE, Emblem.SPADE);
        Dealer dealer = new Dealer(card1, card2);
        Player player = new Player(new Name("도비"),0, card1, card3);

        // When
        int dealerHand = dealer.cardDeck.calculateHand();

        // Then
        assertEquals(MatchState.WIN, player.determineMatchResult(dealerHand));
    }
}
