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

    public int compare(Player player){
        if(this.getScore() < player.getScore()){
            return 1;
        }
        return 0;
    }
}
