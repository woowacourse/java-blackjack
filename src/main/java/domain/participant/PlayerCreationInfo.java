package domain.participant;

public class PlayerCreationInfo {
    private final Name name;
    private final BettingMoney bettingMoney;

    private PlayerCreationInfo(Name name, BettingMoney bettingMoney){
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public static PlayerCreationInfo of(Name name, BettingMoney bettingMoney){
        return new PlayerCreationInfo(name, bettingMoney);
    }

    public Name getName(){
        return name;
    }

    public BettingMoney getBettingMoney(){
        return bettingMoney;
    }

}
