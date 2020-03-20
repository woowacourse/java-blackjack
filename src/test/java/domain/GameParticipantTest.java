package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.Card;
import domain.card.Shape;
import domain.card.Symbol;
import domain.participant.Money;
import domain.participant.Name;

class GameParticipantTest {

    @Test
    @DisplayName("블랙잭인지 알맞게 체크해주는지")
    void checkBlackJack() {
        List<Name> names = new ArrayList<>();
        names.add(Name.create("a"));
        names.add(Name.create("b"));
        names.add(Name.create("c"));
        List<Money> moneys = new ArrayList<>();
        moneys.add(Money.create("0"));
        moneys.add(Money.create("0"));
        moneys.add(Money.create("0"));
        Players players = new Players(names, moneys);
        players.getPlayer(0).receive(new Card(Symbol.QUEEN, Shape.클로버));
        players.getPlayer(0).receive(new Card(Symbol.ACE, Shape.클로버));
        players.getPlayer(1).receive(new Card(Symbol.NINE, Shape.클로버));
        players.getPlayer(2).receive(new Card(Symbol.EIGHT, Shape.클로버));
        GameParticipant gameParticipant = new GameParticipant(players);
        gameParticipant.checkBlackJack();
        assertThat(players.getPlayer(0).isBlackJack()).isTrue();

    }
}
