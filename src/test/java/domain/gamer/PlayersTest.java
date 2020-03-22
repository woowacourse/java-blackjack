package domain.gamer;

import domain.card.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayersTest {

    @Test
    @DisplayName("players에 저장된 player를 eachPlayer메서드를 이용해서 꺼내올수 있다.")
    void eachPlayerTest() {
        PlayingCards playingCards = new PlayingCards(Arrays.asList(new Card(Symbol.ACE, Type.CLOVER),new Card(Symbol.TWO,Type.HEART)));
        Player player = new Player("lee", 10000,playingCards);
        Players players = new Players(Arrays.asList(player));
        assertThat(players.eachPlayer(0)).isEqualTo(player);
    }

    @Test
    @DisplayName("players에 저장된 player들의 숫자를 꺼내올수 있다.")
    void participantPlayerTest() {
        PlayingCards playingCards = new PlayingCards(Arrays.asList(new Card(Symbol.ACE, Type.CLOVER),new Card(Symbol.TWO,Type.HEART)));
        Player player = new Player("lee", 10000,playingCards);
        Players players = new Players(Arrays.asList(player));
        assertThat(players.participantNumber()).isEqualTo(1);
    }
}
