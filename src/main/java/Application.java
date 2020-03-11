import controller.BlackjackController;
import domain.card.Card;
import domain.card.CardRepository;
import domain.card.Cards;
import domain.card.Deck;
import domain.user.Dealer;
import domain.user.Players;

/**
 * 클래스 이름 : .java
 *
 * @author
 * @version 1.0
 * <p>
 * 날짜 : 2020/03/11
 */
public class Application {
	public static void main(String[] args) {
		Deck deck = new Deck(CardRepository.toList());
		Dealer dealer = new Dealer();
		BlackjackController.run(deck, dealer, Players.of("kouz,toney"));
	}
}
