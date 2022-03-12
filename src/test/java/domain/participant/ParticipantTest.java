package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Face;
import domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ParticipantTest {

    @ParameterizedTest
    @DisplayName("플레이어가 카드를 더 받을수 있는지 테스트")
    @CsvSource(value = {"SPADE,ACE,HEART,NINE,true", "DIAMOND,ACE,CLUB,JACK,false"})
    void CanReceiveCard(Suit firstSuit, Face firstFace, Suit secondSuit, Face secondFace, boolean expected) {
        Participant player = new Player("testName");
        player.receiveCard(new Card(firstSuit, firstFace));
        player.receiveCard(new Card(secondSuit, secondFace));

        assertThat(player.canReceiveCard()).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("딜러가 카드를 더 받을수 있는지 테스트")
    @CsvSource(value = {"SPADE,ACE,HEART,FIVE,true", "DIAMOND,ACE,CLUB,SIX,false"})
    void CanReceiveCard2(Suit firstSuit, Face firstFace, Suit secondSuit, Face secondFace, boolean expected) {
        Participant dealer = new Dealer();
        dealer.receiveCard(new Card(firstSuit, firstFace));
        dealer.receiveCard(new Card(secondSuit, secondFace));

        assertThat(dealer.canReceiveCard()).isEqualTo(expected);
    }
}
