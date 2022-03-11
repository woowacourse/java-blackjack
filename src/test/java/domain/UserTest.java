package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.FACE;
import domain.card.SUIT;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class UserTest {
    @ParameterizedTest
    @DisplayName("플레이어가 카드를 더 받을수 있는지 테스트")
    @CsvSource(value = {"SPADE,ACE,HEART,NINE,true", "DIAMOND,ACE,CLUB,JACK,false"})
    void CanReceiveCard(SUIT firstSuit, FACE firstFace, SUIT secondSuit, FACE secondFace, boolean expected) {
        //given
        User player = new Player();
        player.receiveCard(new Card(firstSuit, firstFace));
        player.receiveCard(new Card(secondSuit, secondFace));

        assertThat(player.canReceiveCard()).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("딜러가 카드를 더 받을수 있는지 테스트")
    @CsvSource(value = {"SPADE,ACE,HEART,FIVE,true", "DIAMOND,ACE,CLUB,SIX,false"})
    void CanReceiveCard2(SUIT firstSuit, FACE firstFace, SUIT secondSuit, FACE secondFace, boolean expected) {
        //given
        User player = new Dealer();
        player.receiveCard(new Card(firstSuit, firstFace));
        player.receiveCard(new Card(secondSuit, secondFace));

        assertThat(player.canReceiveCard()).isEqualTo(expected);
    }
}
