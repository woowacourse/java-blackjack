package blackjack.domain;

import blackjack.fixture.ParticipantCardsFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {
    @Test
    @DisplayName("플레이어가 카드를 뽑는다.")
    void hit() {
        Card cardOne = new Card(CardShape.DIAMOND, CardNumber.THREE);
        Card cardTwo = new Card(CardShape.DIAMOND, CardNumber.TWO);
        Participant player = new Player(ParticipantCardsFixture.createParticipantsCards(cardOne, cardTwo, List.of()));
        int beforeHitPoint = player.getTotalPoint();
        player.hit(new Card(CardShape.SPADE, CardNumber.ACE));
        int afterHitPoint = player.getTotalPoint();

        assertThat(afterHitPoint).isGreaterThan(beforeHitPoint);
    }

    @Test
    @DisplayName("플레이어가 카드를 보여준다.")
    void open() {
        Card cardOne = new Card(CardShape.DIAMOND, CardNumber.THREE);
        Card cardTwo = new Card(CardShape.DIAMOND, CardNumber.TWO);
        Participant player = new Player(ParticipantCardsFixture.createParticipantsCards(cardOne, cardTwo, List.of()));

        assertThat(player.open(2)).containsAll(List.of(cardOne, cardTwo));
    }
}