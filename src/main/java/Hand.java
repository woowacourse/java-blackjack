import java.util.ArrayList;
import java.util.List;

public class Hand {
    List<Integer> cards = new ArrayList<>();

    public Hand(List<Integer> cards) {
        this.cards = cards;

        for(int i = 0; i < cards.size(); i++){
            if (cards.get(i) < 1) {
                throw new IllegalArgumentException("[ERROR] 적절한 카드의 숫자가 아닙니다.");
            }
        }
    }

    public boolean isBurst() {
        return getTotal() > 21;
    }

    public int getTotal() {
        int sum = cards.stream()
                .mapToInt(Integer::intValue)
                .sum();

        if (cards.contains(1) && sum <= 11) {
            sum += 10;
        }

        return sum;
    }
}
