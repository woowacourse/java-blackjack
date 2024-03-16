package domain.gamer;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardPattern;
import domain.card.Cards;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RefereeTest {

    @BeforeEach
    void setUp() {

    }

    @DisplayName("심판을 카드를 받아 블랙잭인지 계산할 수 있다.")
    @Test
    void makePlayerAndDealerScore() {
        Cards playerCards = new Cards();
        playerCards.addCard(new Card(CardNumber.KING, CardPattern.CLOVER_PATTERN));
        playerCards.addCard(new Card(CardNumber.ACE, CardPattern.SPADE_PATTERN));

        Cards dealerCards = new Cards();
        dealerCards.addCard(new Card(CardNumber.TWO, CardPattern.CLOVER_PATTERN));
        dealerCards.addCard(new Card(CardNumber.KING, CardPattern.HEART_PATTERN));

        Referee referee = new Referee();

        Assertions.assertAll(
                () -> assertThat(referee.isBlackJack(playerCards)).isTrue(),
                () -> assertThat(referee.isBlackJack(dealerCards)).isFalse()
        );
    }
}
