package domain.player;

import domain.card.Hand;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Gambler extends Player {
    public Gambler(Hand hand, Name name, Bet bet) {
        super(hand, name, bet);
    }

    public static List<Gambler> from(Map<Name, Bet> nameAndBet) {
        List<Gambler> gamblers = new ArrayList<>();
        nameAndBet.forEach((name, bet) -> gamblers.add(new Gambler(Hand.withEmptyHolder(), name, bet)));
        return gamblers;
    }

    @Override
    public boolean isNameEqualTo(String playerName) {
        return getName().equals(playerName);
    }
}
