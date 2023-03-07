package domain;

import java.util.*;

public class Deck {

    private static final Queue<Card> DECK;

    static {
        DECK = createDeck();
    }

    private Deck() {
    }

    /*TODO: 중간 변수로 List가 있는 게 마음에 들지 않아요.
        하지만 Queue는 FIFO구조를 따르기에 순서를 섞을 수도 없고 어떻게 수정해야할지 모르겠습니다.
        넣을 때 부터 랜덤으로 넣을 수 있는 방법이 있나요?ㅜㅜ
     */
    private static Queue<Card> createDeck() {
        List<Card> deck = new LinkedList<>();
        addCardsInDeck(deck);
        Collections.shuffle(deck);

        return (Queue<Card>) deck;
    }

    private static void addCardsInDeck(List<Card> deck) {
        Arrays.stream(Denomination.values()).forEach(denomination -> {
            Arrays.stream(Suit.values()).map(suit -> new Card(suit, denomination)).forEach(deck::add);
        });
    }

    public static Card pickCard() {
        return DECK.poll();
    }

    public static Queue<Card> getDeck() {
        return new LinkedList<>(DECK);
    }
}
