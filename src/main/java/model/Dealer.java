package model;

public class Dealer extends User {
    private int win = 0;
    private int lose = 0;

    public Dealer(CardHand cardHand) {
        super(cardHand);
    }

    public Dealer(Strategy strategy) {
        super(strategy);
    }

    public String toStringCardHandFirst() {
        return cardHand.getCards().get(0).toString();
    }

    public Result compareWithOther(Player player){
        if (getScore() > player.getScore()) {
            return Result.WIN;
        }
        if(getScore()==player.getScore()){
            return Result.DEFAULT;
        }
        return Result.LOSE;
    }

    public void compare(Players players) {
        for (Player player : players.getPlayers()) {
            compareWithPlayer(player);
        }
    }

    public void compareWithPlayer(Player player) {
        Result result = compareWithOther(player);
        if (Result.WIN == result) {
            win++;
            player.setResult(Result.LOSE);
        }
        if (Result.LOSE == result) {
            lose++;
            player.setResult(Result.WIN);
        }
    }

    @Override
    public String toStringCardHand() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Card card : cardHand.getCards()) {
            stringBuilder.append(card.toString());
        }
        return stringBuilder.toString();
    }

    public int getWin() {
        return win;
    }
}
