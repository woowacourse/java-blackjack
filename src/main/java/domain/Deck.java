package domain;

import domain.card.Card;
import domain.card.CardRank;
import domain.card.CardSuit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> cards; //유지해야하는 52장의 카드
    private int index; //카드가 가리키는 리스트 좌표

    public Deck(){
        this.cards = new ArrayList<>();
        this.index = 0;
        init();
    }

    private void init(){
       for(CardSuit cardSuit : CardSuit.values()){
            Arrays.stream(CardRank.values()).forEach(c -> cards.add(new Card(c, cardSuit)));
       }

       Collections.shuffle(cards);
    }

    public void shuffle(){
        Collections.shuffle(cards);
        index = 0;
    }

    //TODO : INDEX가 52를 넘는다면?
    public Card deal(){
        return cards.get(index++);
    }
}
