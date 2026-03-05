import java.util.Collections;
import java.util.List;

public class Card {
    // 리스트 합 구하기
    public int sumNumbers(List<String> cards) {

        Collections.replaceAll(cards, "A", "1");
        return cards.stream().map(Integer::parseInt).mapToInt(Integer::intValue).sum();
    }

    // A에 따른 총합 조정하기
    public int filterAce(int total, boolean isContainAce) {
        if (isContainAce && total < 10) {
            return total + 10;
        }
        return total;
    }
}
