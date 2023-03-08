package domain;

import domain.Card.Card;
import domain.Card.CardNumber;
import domain.Card.CardShape;
import domain.user.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {
    
    
    @Test
    @DisplayName("처음에 카드를 지급받지 않은 경우 카드 조회시 오류를 던진다.")
    void getReadyCardsTestFailed() {
        Player participant = new Player("echo");
        Assertions.assertThatThrownBy(participant::getReadyCards)
                .isExactlyInstanceOf(IllegalStateException.class);
    }
    
    @Test
    @DisplayName("처음에 지급받은 카드를 반환한다.")
    void getReadyCardsTestSuccess() {
        Player participant = new Player("echo");
        participant.addCard(new Card(CardNumber.ACE, CardShape.SPADE));
        participant.addCard(new Card(CardNumber.THREE, CardShape.HEART));
        Assertions.assertThat(participant.getReadyCards())
                .containsExactly(new Card(CardNumber.ACE, CardShape.SPADE),
                        new Card(CardNumber.THREE, CardShape.HEART));
    }
    
    @Test
    @DisplayName("현재의 점수를 반환한다.")
    void calculateScore() {
        Player participant = new Player("echo");
        participant.addCard(new Card(CardNumber.ACE, CardShape.SPADE));
        participant.addCard(new Card(CardNumber.THREE, CardShape.HEART));
        Assertions.assertThat(participant.getScore()).isEqualTo(14);
    }
}
