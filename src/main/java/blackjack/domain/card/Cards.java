package blackjack.domain.card;

public class Cards {

    Cards(){

    }

    public void addCard(Card card){
        checkCardNull(card);
    }

    private void checkCardNull(Card card) {
        if (card == null){
            throw new IllegalArgumentException("[ERROR] 올바른 카드를 입력해주세요.");
        }
    }
}
