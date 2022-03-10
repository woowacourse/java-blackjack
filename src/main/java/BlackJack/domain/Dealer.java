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
        //TODO: 테스트 코드 작성
        //TODO: INPUTVIEW 유효성 검사
        if(this.getScore() < player.getScore() && player.getScore() <= 21){
            return Result.WIN;
        }
        if(this.getScore() > player.getScore() || player.getScore() > 21){
            return Result.LOSE;
        }
        return Result.DRAW;
    }
}
