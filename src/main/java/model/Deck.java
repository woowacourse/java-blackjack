package model;

import exception.EmptyDeckException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck implements Strategy {
    private List<Card> cards = new ArrayList<>();

    public Deck(List<Card> cards) {
        this.cards.addAll(cards);
        shuffle();
    }

    private void shuffle() {
        Collections.shuffle(cards);
    }

    @Override
    public Card draw(){
        validateSize();
        int lastIndex = cards.size()-1;
        Card drawCard = cards.get(lastIndex);
        cards.remove(lastIndex);
        return drawCard;
    }

    private void validateSize() {
        if(cards.size()<=0){
            throw new EmptyDeckException("더 이상 draw 할 카드가 존재하지 않습니다.");
        }
    }

}
