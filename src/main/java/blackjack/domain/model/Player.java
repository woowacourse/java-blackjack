package blackjack.domain.model;

import blackjack.domain.vo.Name;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Player {
    private final Name name;
    private final Cards cards;
    public Player(Name name, Cards cards){
        this.name = name;
        this.cards = cards;
    }

    public void drawCard(Card card) {
        cards.add(card);
    }

    public int calculateTotal(){
        return this.cards.calculateTotalScore();
    }

    public String getName() {
        return name.get();
    }

    public List<String> getCards() {
        return cards.getCards().stream()
                .map(Card::getCardName)
                .collect(Collectors.toList());
    }

    public List<String> getOneCard(){
        return List.of(this.cards.getFirstCard().getCardName());
    }
}
