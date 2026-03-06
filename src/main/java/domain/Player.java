package domain;

public class Player {
    private final String name;
    private final Cards cards;

    public Player(String name) {
        this.name = name;
        this.cards = new Cards();
    }

    public void add(Card card){
        cards.add(card);
    }

    public boolean isPlayerWin(int target){
        int sum = cards.getTotalSum();
        if(cards.decideBurst(sum)){
            return false;
        }
        return target < sum;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return cards.toString();
    }
}
