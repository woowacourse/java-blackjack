package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ResultTest {

    @Test
    @DisplayName("")
    void calculateResult() {
        Dealer dealer = Dealer.create();
        Player pobi = Player.from("fobi");
        Player jason = Player.from("jason");

        dealer.receiveCard(new Card(Shape.HEART, Number.ACE));
        dealer.receiveCard(new Card(Shape.HEART, Number.JACK));
        dealer.receiveCard(new Card(Shape.HEART, Number.QUEEN));

        pobi.receiveCard(new Card(Shape.HEART, Number.ACE));
        pobi.receiveCard(new Card(Shape.HEART, Number.JACK));
        pobi.receiveCard(new Card(Shape.HEART, Number.QUEEN));

        jason.receiveCard(new Card(Shape.HEART, Number.ACE));
        jason.receiveCard(new Card(Shape.HEART, Number.JACK));
        jason.receiveCard(new Card(Shape.HEART, Number.QUEEN));

        Map<Participant, Integer> participantScores = new HashMap<>();
    }
}