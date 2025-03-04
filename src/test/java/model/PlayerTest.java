package model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PlayerTest {

  @Test
  @DisplayName("플레이어가 생성 확인")
  void newPlayer() {
    //given
    Nickname expected = new Nickname("pobi");
    //when
    Player player = new Player(new Nickname("pobi"));
    //then
    Assertions.assertThat(player.getNickname()).isEqualTo(expected);
  }

  @Test
  @DisplayName("카드 추가 기능이 잘 작동하는 지")
  void addCardsSuccess() {

    // given
    final int amount = 2;
    final List<Card> cards = CardDeck.pickCard(2);

    // when
    Player player = new Player(new Nickname("pobi"));
    player.addCards(cards);

    // then
    Assertions.assertThat(player.getHands().size()).isEqualTo(amount);
  }
}
