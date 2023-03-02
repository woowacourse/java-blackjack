package blackjack.domain.model;

import blackjack.domain.vo.Name;

public class Player {
    private final Name name;
    private final Cards cards;
    public Player(Name name, Cards cards){
        this.name = name;
        this.cards = cards;
    }
}
