package domain.deck;

import domain.card.Card;
import domain.card.CardRank;
import domain.card.CardSuit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck implements CardDeck{
    private final List<Card> cards;
    private int index;

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

    //TODO : INDEX가 52를 넘는다면? --> 나중에 생각
    @Override
    public Card deal(){
        return cards.get(index++);
    }
}
