package model.player;

import model.BlackJackState;
import model.GameMoney;
import model.Outcome;

import java.util.Objects;

public class ParticipantProfile {
    private final Name name;
    private final GameMoney gameMoney;

    public ParticipantProfile(Name name, GameMoney gameMoney) {
        this.name = name;
        this.gameMoney = gameMoney;
    }

    public int calculateRevenue(Outcome outcome, BlackJackState blackJackState) {
        return gameMoney.calculateRevenue(outcome, blackJackState);
    }

    public Name getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParticipantProfile that = (ParticipantProfile) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
