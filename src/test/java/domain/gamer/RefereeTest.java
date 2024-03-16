package domain.gamer;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardPattern;
import domain.card.Cards;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RefereeTest {

    Cards playerCards = new Cards();
    Cards dealerCards = new Cards();
    Referee referee = new Referee();


    @DisplayName("심판을 카드를 받아 블랙잭인지 계산할 수 있다.")
    @Test
    void makePlayerAndDealerScore() {
        playerCards.addCard(new Card(CardNumber.KING, CardPattern.CLOVER_PATTERN));
        playerCards.addCard(new Card(CardNumber.ACE, CardPattern.SPADE_PATTERN));

        dealerCards.addCard(new Card(CardNumber.TWO, CardPattern.CLOVER_PATTERN));
        dealerCards.addCard(new Card(CardNumber.KING, CardPattern.HEART_PATTERN));

        Assertions.assertAll(
                () -> assertThat(referee.isBlackJack(playerCards)).isTrue(),
                () -> assertThat(referee.isBlackJack(dealerCards)).isFalse()
        );
    }

    @DisplayName("플레이어가 블랙잭으로 이긴 경우 BLACKJACKWIN을 반환한다")
    @Test
    void makeBlackJackWinResult() {
        playerCards.addCard(new Card(CardNumber.KING, CardPattern.CLOVER_PATTERN));
        playerCards.addCard(new Card(CardNumber.ACE, CardPattern.SPADE_PATTERN));

        dealerCards.addCard(new Card(CardNumber.TWO, CardPattern.CLOVER_PATTERN));
        dealerCards.addCard(new Card(CardNumber.KING, CardPattern.HEART_PATTERN));

        referee.judgeResult(playerCards, dealerCards);
        assertThat(referee.judgeResult(playerCards, dealerCards)).isEqualTo(PlayerResult.BLACKJACKWIN);
    }

    @DisplayName("플레이어가 블랙잭, 딜러도 블랙잭인 경우 DRAW를 반환한다")
    @Test
    void makeBlackJackDrawResult() {
        playerCards.addCard(new Card(CardNumber.KING, CardPattern.CLOVER_PATTERN));
        playerCards.addCard(new Card(CardNumber.ACE, CardPattern.SPADE_PATTERN));

        dealerCards.addCard(new Card(CardNumber.QUEEN, CardPattern.CLOVER_PATTERN));
        dealerCards.addCard(new Card(CardNumber.ACE, CardPattern.HEART_PATTERN));

        referee.judgeResult(playerCards, dealerCards);
        assertThat(referee.judgeResult(playerCards, dealerCards)).isEqualTo(PlayerResult.DRAW);
    }
}
