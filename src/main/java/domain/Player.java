package domain;

public class Player extends Participant{

    private static final int MAXIMUM_SUM_OF_CARD = 21;
    private final Name name;
    private Betting betting;

    public Player(Cards cards, Name name, Betting betting) {
        super(cards);
        this.name = name;
        this.betting = betting;
    }

    public void giveBonusIfInitialCardsAreBlackJack() {
        if (isBlackJack()) {
            this.betting = betting.giveBonus();
        }
    }

    public void calculateFinalBettingResult(Result result){
        this.betting = betting.calculateProfitByResult(result);
    }

    public int calculateFinalProfit(){
        return betting.calculateFinalProfit();
    }

    public String getName() {
        return name.getName();
    }

    public Cards getCards() {
        return cards;
    }

}
