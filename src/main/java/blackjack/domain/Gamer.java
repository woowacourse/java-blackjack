package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Gamer {

    private final List<Card> cards;

    public Gamer() {
        cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }

    public boolean isOverThan(int number) {
        return getCardsNumberSum() > number;
    }

    public int getCardsNumberSum() {
        int sum = getSumExceptAce();
        List<Card> aces = getAces();

        sum = getSumNotToBurst(sum, aces);
        return sum;
    }

	private int getSumExceptAce() {
		return cards.stream()
				.filter(card -> !card.isAce())
				.mapToInt(Card::getNumber)
				.sum();
	}

	private List<Card> getAces() {
		return cards.stream()
				.filter(Card::isAce)
				.collect(Collectors.toList());
	}

    private int getSumNotToBurst(int sum, List<Card> aces) {
        for (Card ace : aces) {
            sum += selectAceValue(sum, ace);
        }
        return sum;
    }

    private int selectAceValue(int sum, Card ace) {
        validateAceCard(ace);
        if (sum + ace.getNumber() > 21) {
            return CardNumber.LOWER_ACE_VALUE;
        }
        return ace.getNumber();
    }

    private void validateAceCard(Card ace) {
        if (!ace.isAce()) {
            throw new IllegalArgumentException("입력받은 값이 에이스 카드가 아닙니다.");
        }
    }
}
