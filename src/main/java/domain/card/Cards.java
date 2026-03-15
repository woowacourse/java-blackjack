package domain.card;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    private final List<Help> helps;

    public Cards(List<Help> helps) {
        this.helps = new ArrayList<>(helps);
    }

    public int getSize() {
        return helps.size();
    }

    public List<String> getCardsInfo() {
        return helps.stream()
                .map(Help::getCardInfo)
                .toList();
    }

    public Help removeFirst() {
        return helps.removeFirst();
    }

    public int sumScore() {
        int sum = 0;
        for (Help help : helps) {
            sum += help.getScore();
        }
        return sum;
    }

    public void add(Help help) {
        helps.add(help);
    }
}
