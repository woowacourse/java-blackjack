package domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Player{
    private static final String Y_COMMAND = "y";
    private final Name name;
    private final Cards cards;

    public Player(String name) {
        this.name = new Name(name);
        this.cards = new Cards();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public Map<String, List<String>> getInfo() {
        Map<String, List<String>> info = new LinkedHashMap<>();
        info.put(name.getName(), getCards());
        return info;
    }

    public String getName() {
        return name.getName();
    }

    public Card getCard(int index) {
        return cards.getCard(index);
    }

    public List<String> getCards() {
        return cards.getCards();
    }

    public int getCardsSum() {
        return cards.getSum();
    }

    public boolean isOverPlayerBlackJack() {
        return getCardsSum() >= BlackjackGame.BLACK_JACK;
    }

    public boolean canSelectCard(String command) {
        if(!isOverPlayerBlackJack() && isCommandYes(command)){
            return true;
        }
        return false;
    }

    public boolean isCommandYes(String command){
        if(command.equals(Y_COMMAND)){
            return true;
        }
        return false;
    }

}
