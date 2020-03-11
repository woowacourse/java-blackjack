package domain.user;

import domain.card.Card;
import domain.card.Cards;

import java.util.List;

/**
 * 클래스 이름 : .java
 *
 * @author
 * @version 1.0
 * <p>
 * 날짜 : 2020/03/11
 */
public class Dealer extends User {
	public Card openCard() {
		List<Card> dealerCards = cards.toList();
		return dealerCards.get(0);
	}
}
