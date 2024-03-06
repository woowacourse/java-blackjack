package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantsTest {

    @Test
    @DisplayName("게임 참가자들의 수를 확인한다.")
    void sizeTest() {

        Player siso = new Player(new Name("시소"));
        Player tacan = new Player(new Name("타칸"));
        Player dealer = new Player(new Name("딜러"));
        List<Player> players = List.of(siso, tacan);

        Participants participants = new Participants(dealer, players);

        Assertions.assertThat(participants.count()).isEqualTo(3);

    }

    @Test
    @DisplayName("게임 참가자들이 초기 카드를 받는다")
    void initialDistributeTest() {

        Player siso = new Player(new Name("시소"));
        Player tacan = new Player(new Name("타칸"));
        Player dealer = new Player(new Name("딜러"));
        List<Player> players = List.of(siso, tacan);

        Participants participants = new Participants(dealer, players);
        Deck deck1 = new Deck();
        deck1.addCard(new Card(Shape.HEART, Rank.ACE));
        deck1.addCard(new Card(Shape.HEART, Rank.TWO));

        Deck deck2 = new Deck();
        deck2.addCard(new Card(Shape.SPADE, Rank.ACE));
        deck2.addCard(new Card(Shape.SPADE, Rank.TWO));

        Deck deck3 = new Deck();
        deck3.addCard(new Card(Shape.DIAMOND, Rank.ACE));
        deck3.addCard(new Card(Shape.DIAMOND, Rank.TWO));


        participants.receiveInitialCards(
                new ArrayList<>(List.of(deck1, deck2, deck3))
        );

        Assertions.assertThat(siso.getDeck().size()).isEqualTo(2);

    }

}
