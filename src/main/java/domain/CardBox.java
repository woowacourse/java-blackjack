package domain;

import java.util.ArrayList;
import java.util.List;

public class CardBox {

    public List<Card> cardBox = new ArrayList<>();

    public CardBox() {
        initHeart();
        initClover();
        initDiamond();
        initSpade();
    }

    private void initSpade() {
        cardBox.add(new Card("A스페이드", 11));
        cardBox.add(new Card("2스페이드", 2));
        cardBox.add(new Card("3스페이드", 3));
        cardBox.add(new Card("4스페이드", 4));
        cardBox.add(new Card("5스페이드", 5));
        cardBox.add(new Card("6스페이드", 6));
        cardBox.add(new Card("7스페이드", 7));
        cardBox.add(new Card("8스페이드", 8));
        cardBox.add(new Card("9스페이드", 9));
        cardBox.add(new Card("10스페이드", 10));
        cardBox.add(new Card("J스페이드", 10));
        cardBox.add(new Card("Q스페이드", 10));
        cardBox.add(new Card("K스페이드", 10));
    }

    private void initDiamond() {
        cardBox.add(new Card("A다이아몬드", 11));
        cardBox.add(new Card("2다이아몬드", 2));
        cardBox.add(new Card("3다이아몬드", 3));
        cardBox.add(new Card("4다이아몬드", 4));
        cardBox.add(new Card("5다이아몬드", 5));
        cardBox.add(new Card("6다이아몬드", 6));
        cardBox.add(new Card("7다이아몬드", 7));
        cardBox.add(new Card("8다이아몬드", 8));
        cardBox.add(new Card("9다이아몬드", 9));
        cardBox.add(new Card("10다이아몬드", 10));
        cardBox.add(new Card("J다이아몬드", 10));
        cardBox.add(new Card("Q다이아몬드", 10));
        cardBox.add(new Card("K다이아몬드", 10));
    }

    private void initClover() {
        cardBox.add(new Card("A클로버", 11));
        cardBox.add(new Card("2클로버", 2));
        cardBox.add(new Card("3클로버", 3));
        cardBox.add(new Card("4클로버", 4));
        cardBox.add(new Card("5클로버", 5));
        cardBox.add(new Card("6클로버", 6));
        cardBox.add(new Card("7클로버", 7));
        cardBox.add(new Card("8클로버", 8));
        cardBox.add(new Card("9클로버", 9));
        cardBox.add(new Card("10클로버", 10));
        cardBox.add(new Card("J클로버", 10));
        cardBox.add(new Card("Q클로버", 10));
        cardBox.add(new Card("K클로버", 10));
    }

    private void initHeart() {
        cardBox.add(new Card("A하트", 11));
        cardBox.add(new Card("2하트", 2));
        cardBox.add(new Card("3하트", 3));
        cardBox.add(new Card("4하트", 4));
        cardBox.add(new Card("5하트", 5));
        cardBox.add(new Card("6하트", 6));
        cardBox.add(new Card("7하트", 7));
        cardBox.add(new Card("8하트", 8));
        cardBox.add(new Card("9하트", 9));
        cardBox.add(new Card("10하트", 10));
        cardBox.add(new Card("J하트", 10));
        cardBox.add(new Card("Q하트", 10));
        cardBox.add(new Card("K하트", 10));
    }


    public Card get(final int index) {
        Card card = cardBox.get(index);
        cardBox.remove(card);
        return card;
    }
}
