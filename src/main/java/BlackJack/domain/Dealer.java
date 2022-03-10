package BlackJack.domain;

public class Dealer extends User {

    private static final int DEALER_ADD_CARD_LIMIT = 16;

    public Dealer(Cards cards) {
        super("딜러", cards);
    }

    @Override
    public void addCard() {
        cards.add(CardFactory.drawOneCard());
    }

    public boolean checkScore(){
        return cards.calculateScore() <= DEALER_ADD_CARD_LIMIT;
    }

    public Result compare(Player player){
        if(this.getScore() < player.getScore() && player.getScore() <= 21){
            return Result.WIN;
        }
        if(this.getScore() > player.getScore() || player.getScore() > 21){
            return Result.LOSE;
        }
        return Result.DRAW;
    }
}
