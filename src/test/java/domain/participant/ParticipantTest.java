package domain.participant;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardShape;
import domain.name.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vo.BettingMoney;

import static domain.card.CardShape.HEART;
import static org.assertj.core.api.Assertions.assertThat;

class ParticipantTest {

    @DisplayName("카드의 합계로 `Bust`를 판단한다.")
    @Test
    void isBust() {
        Card cardTwo = new Card(CardShape.SPADE, CardNumber.TWO);
        Card cardKing = new Card(HEART, CardNumber.KING);
        Card cardQueen = new Card(HEART, CardNumber.QUEEN);
        Player player = new Player(new Name("hotea"), new BettingMoney(5000));
        player.receive(cardTwo);
        player.receive(cardKing);
        player.receive(cardQueen);
        assertThat(player.isBust()).isTrue();
    }

    @DisplayName("카드의 합계로 `Blackjack`를 판단한다.")
    @Test
    void isBlackjack() {
        Card cardAce = new Card(CardShape.SPADE, CardNumber.ACE);
        Card cardKing = new Card(HEART, CardNumber.KING);
        Card cardQueen = new Card(HEART, CardNumber.QUEEN);
        Player player = new Player(new Name("hotea"), new BettingMoney(5000));
        player.receive(cardAce);
        player.receive(cardKing);
        player.receive(cardQueen);
        assertThat(player.isBlackjack()).isTrue();
    }
}
