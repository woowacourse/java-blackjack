package blackjack.domain.status;

import static blackjack.domain.card.CardNumber.*;
import static blackjack.domain.card.CardSymbol.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Cards;

public class ReadyTest {

    @Test
    @DisplayName("draw으로 카드 한 장을 뽑은 경우 Ready 상태이다.")
    void draw() {
        //given
        Status status = new Ready();

        //when
        Status newStatus = status.draw(new Card(HEART, JACK));

        //then
        assertThat(newStatus).isInstanceOf(Ready.class);
    }

    @Test
    @DisplayName("2 장을 뽑으면 Hit 상태로 바뀐다.")
    void toHit() {
        //given
        Status status = new Ready();

        //when
        Status newStatus = status.draw(new Card(HEART, JACK));
        Status hitStatus = newStatus.draw(new Card(CLUB, JACK));

        //then
        assertThat(hitStatus).isInstanceOf(Hit.class);
    }

    @Test
    @DisplayName("처음 2장의 카드의 합이 21이면 Blackjack 상태로 바뀐다.")
    void toBlackjack() {
        //given
        Status status = new Ready();

        //when
        Status newStatus = status.draw(new Card(HEART, JACK));
        Status blackjackStatus = newStatus.draw(new Card(HEART, ACE));

        //then
        assertThat(blackjackStatus).isInstanceOf(Blackjack.class);
    }

    @Test
    @DisplayName("Hit 상태에서 draw 로 21이 초과하면 Bust 상태로 바뀐다.")
    void toBust() {
        //given
        Status hitStatus = new Hit(new Cards(Set.of(new Card(HEART, JACK), new Card(CLUB, JACK))));

        //when
        Status bustStatus = hitStatus.draw(new Card(HEART, TWO));

        //then
        assertThat(bustStatus).isInstanceOf(Bust.class);
    }
}
