package domain.player;

import domain.vo.Name;

public class Player {
    private Name name;
    private HandCards handCards = new HandCards();

    public Player(Name name) {
        this.name = name;
    }

    public String getName() {
        return name.getName();
    }

}
