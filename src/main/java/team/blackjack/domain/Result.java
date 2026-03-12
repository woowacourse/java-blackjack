package team.blackjack.domain;

public enum Result {
    BLACKJACK("승", 1.5),
    WIN("승", 1),
    DRAW("무", 0),
    LOSE("패", -1);

    private final String name;
    private final double odds;

    Result(String name, double odds) {
        this.name = name;
        this.odds = odds;
    }

    public String getName(){
        return this.name;
    }

    public double getOdds() {
        return this.odds;
    }
}
