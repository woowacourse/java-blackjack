package model;


import model.dto.PlayerResult;
import org.junit.jupiter.api.Assertions;
import static org.assertj.core.api.Assertions.*;

public class TestPlayer {

    public void 결과_출력_정상_작동() {
        //given
        //init
        Player player = new Player("jason");

        //then
        //결과가 나온다. (초기 점수는 0점)
        PlayerResult result = player.getResult();
        assertThat(result.name())
                .isEqualTo("jason");
        assertThat(result.deck().isEmpty())
                .isTrue();
        assertThat(result.score())
                .isEqualTo(0);
    }


    public void 카드_받기_정상_작동() {
        //given
        //init
        Card card = new Card(문양, 번호);
        Player player = new Player();
        Integer originalScore = player.getResult().score();

        //when
        //어떤 카드를 넣었을 때,
        player.addCard(card);

        List<Card> deck = player.getResult().deck();

        //then
        //리스트는 증가하고, 그 카드의 리스트가 같은지를 검증.
        assertThat(deck)
                .contains(card);
        assertThat(deck.size()).isEqualTo(1);

        //점수가 증가했는지 검증
        assertThat(player.getResult().score()).isEqualTo(originalScore + "숫자?");
    }

}
