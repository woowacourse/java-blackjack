package blackjack.domain.status;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardSymbol;

public class ReadyTest {

    @Test
    @DisplayName("draw으로 카드 한 장을 뽑아 저장한다.")
    void draw() {
        //given
        Status status = new Ready();

        //when
        Status newStatus = status.draw(new Card(CardSymbol.HEART, CardNumber.JACK));

        //then
        assertThat(newStatus.getCards().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("2 장을 뽑으면 Hit 상태로 바뀐다.")
    void toHit() {
        //given
        Status status = new Ready();

        //when
        Status newStatus = status.draw(new Card(CardSymbol.HEART, CardNumber.JACK));
        Status hitStatus = newStatus.draw(new Card(CardSymbol.CLUB, CardNumber.JACK));

        //then
        assertThat(hitStatus).isInstanceOf(Hit.class);
    }

    @Test
    @DisplayName("처음 2장의 카드의 합이 21이면 Blackjack 상태로 바뀐다.")
    void toBlackjack() {
        //given
        Status status = new Ready();

        //when
        Status newStatus = status.draw(new Card(CardSymbol.HEART, CardNumber.JACK));
        Status blackjackStatus = newStatus.draw(new Card(CardSymbol.HEART, CardNumber.ACE));

        //then
        assertThat(blackjackStatus).isInstanceOf(Blackjack.class);
    }
}
