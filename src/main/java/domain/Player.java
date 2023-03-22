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

    public boolean isSelectToAddCard(boolean command) {
        return command && checkPlayerCanReceiveCard();
    }

    public boolean checkPlayerCanReceiveCard() {
        return PlayerState.LESS_THAN_MAXIMUM.equals(askPlayerState());
    }

    public boolean isPlayerCanAddCard() {
        return PlayerState.MORE_THAN_MAXIMUM.equals(askPlayerState()) || PlayerState.EQUAL_WITH_MAXIMUM.equals(askPlayerState());
    }

    public String getName() {
        return name.getName();
    }

    public Cards getCards() {
        return cards;
    }

}
