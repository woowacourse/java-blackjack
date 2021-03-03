package blackjack;

import java.util.List;

public class Dealer implements Playable {
    private final Cards cards;

    public Dealer(List<Card> cards) {
        this.cards = new Cards(cards);
    }

    @Override
    public String getName() {
        return "딜러";
    }

    @Override
    public List<Card> getCards() {
        return cards.getUnmodifiableList();
    }

    public boolean isAvailableToTake(){
        return sumCards() <= 17;
    }

    @Override
    public int result(int playerSum) {
        int dealerSum = sumCardsForResult();

        // 이기는 경우 (1)
        // 1 플레이어만 합계가 21을 넘는 경우
        // 2 딜러가 21을 넘지 않으면서 플러이어의 합계보다 높은 경우
        //
        if (playerSum > 21 && dealerSum <= 21) {
            return 1;
        }
        if (dealerSum <= 21 && playerSum < dealerSum) {
            return 1;
        }

        // 지는 경우 (-1)
        // 딜러만 합계가 21을 넘는 경우
        // 플레이어가가 21을 넘지 않으면서 딜러어의 합계보다 높은 경우
        if (playerSum <= 21 && dealerSum > 21) {
            return -1;
        }
        if (playerSum <= 21 && playerSum > dealerSum) {
            return -1;
        }

        // 무승부일 경우 (0)
        // 둘다 21을 넘지 않으면서 합게가 같은 경우
        // 둘다 21을 초과하는 경우
        return 0;
    }

    @Override
    public void takeCard(Card card) {
        cards.add(card);
    }

    @Override
    public int sumCards() {
        List<Card> cardValues = cards.getUnmodifiableList();
        return cardValues.stream().mapToInt(Card::getScore).sum();
    }

    @Override
    public int sumCardsForResult() {
        List<Card> cardValues = cards.getUnmodifiableList();
        int sum = cardValues.stream().mapToInt(Card::getScore).sum();
        int aceCount = (int) cardValues.stream().filter(Card::isAce).count();
        sum += aceCount * 10;
        for (int i = 0; i < aceCount; i++) {
            if (sum > 21) {
                sum -= 10;
                continue;
            }
            break;
        }
        return sum;
    }
}
