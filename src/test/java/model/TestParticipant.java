package model;


import java.util.List;
import model.dto.Card;
import model.dto.PlayerResult;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class TestParticipant {

    @Test
    public void 결과_출력_정상_작동() {
        //given
        //init
        Participant participant = new Participant(new PlayerName("jason"));

        //then
        //결과가 나온다. (초기 점수는 0점)
        PlayerResult result = participant.getResult();
        assertThat(result.name().get())
                .isEqualTo("jason");
        assertThat(result.deck().isEmpty())
                .isTrue();
        assertThat(result.score())
                .isEqualTo(0);
    }

    @Test
    public void 카드_받기_정상_작동() {
        //given
        //init
        Card card = new Card(Shape.CLOVER, CardNumber.EIGHT);
        Participant participant = new Participant(new PlayerName("jason"));
        Integer originalScore = participant.getResult().score();

        //when
        //어떤 카드를 넣었을 때,
        participant.addCard(card);

        List<Card> deck = participant.getResult().deck();

        //then
        //리스트는 증가하고, 그 카드의 리스트가 같은지를 검증.
        assertThat(deck)
                .contains(card);
        assertThat(deck.size()).isEqualTo(1);

        //점수가 증가했는지 검증
        assertThat(participant.getResult().score()).isEqualTo(originalScore + "숫자?");
    }

}
