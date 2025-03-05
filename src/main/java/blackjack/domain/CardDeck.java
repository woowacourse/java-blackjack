package blackjack.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CardDeck {
    private final List<Card> cards = new ArrayList<>();

    public void add(Card card) {
        cards.add(card);
    }

    // TODO start: 카드 덱 - 가능한 점수 합산, 덱 사이즈, 카드 목록 제공

    public Set<Integer> calculatePossibleSum() {
        List<Integer> sums = new ArrayList<>(List.of(0, 0));

        for (Card card : cards) {
            Set<Integer> scoreSet = card.checkScore();

            List<Integer> score = new ArrayList<>(scoreSet);
            if (score.size() == 1) {
                //첫번째 경우, 반환되는 카드의 스코어가 1개뿐인 경우
                sums.set(0, sums.get(0) + score.get(0)); //suma.get(0) 누적합
                sums.set(1, sums.get(1) + score.get(0));
            }
            if (score.size() == 2) {
                //두번째 경우, 반환되는 카드의 스코어가 2개인 경우 ('A'의 경우)
                sums.set(0, sums.get(0) + score.get(0)); //1
                sums.set(1, sums.get(1) + score.get(1)); //11
            }
        }
        //sums 크기가 첫번째 경우에도 2가 되므로, 중복 제거
        return new HashSet<>(sums);
    }

    public int getDeckSize() {
        return cards.size();
    }

    public List<Card> getCards() {
        return cards;
    }
}
