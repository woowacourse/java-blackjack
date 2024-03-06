package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.strategy.SettedDecksGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    //TODO : 생성 부분 리팩터링
    @DisplayName("플레이어가 카드를 뽑아서 저장한다.")
    @Test
    void drawTest() {
        // given
        Name name = new Name("lini");
        Player player = new Player(name);

        Card card1 = new Card(Symbol.HEART, Rank.NINE);
        Card card2 = new Card(Symbol.SPADE, Rank.QUEEN);

        SettedDecksGenerator settedDecksGenerator = new SettedDecksGenerator(card1, card2);
        Decks decks = Decks.createByStrategy(settedDecksGenerator);

        // when
        player.hit(decks);

        // then
        assertThat(player.getHand()).hasSize(1);
    }

    @DisplayName("플레이어가 가진 카드의 점수를 알 수 있다.")
    @Test
    void calculateTotalScoreTest() {
        // given
        Name name = new Name("lini");
        Player player = new Player(name);

        Card card1 = new Card(Symbol.HEART, Rank.NINE);
        Card card2 = new Card(Symbol.SPADE, Rank.QUEEN);

        SettedDecksGenerator settedDecksGenerator = new SettedDecksGenerator(card1, card2);
        Decks decks = Decks.createByStrategy(settedDecksGenerator);

        player.hit(decks);
        player.hit(decks);

        int expectedScore = 19;

        // when
        int result = player.calculateTotalScore();

        // then
        assertThat(result).isEqualTo(expectedScore);
    }

    @DisplayName("플레이어가 버스트인지 확인한다.")
    @Test
    void isBust(){
        // given
        Name name = new Name("lini");
        Player player = new Player(name);

        Card card1 = new Card(Symbol.HEART, Rank.NINE);
        Card card2 = new Card(Symbol.SPADE, Rank.QUEEN);
        Card card3 = new Card(Symbol.SPADE, Rank.THREE);

        SettedDecksGenerator settedDecksGenerator = new SettedDecksGenerator(card1, card2, card3);
        Decks decks = Decks.createByStrategy(settedDecksGenerator);
        player.hit(decks);
        player.hit(decks);
        player.hit(decks);

        // when
        boolean bust = player.isBust();

        // then
        assertThat(bust).isTrue();
    }
}
