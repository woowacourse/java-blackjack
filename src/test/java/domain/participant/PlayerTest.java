package domain.participant;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.Card;
import domain.card.Shape;
import domain.card.Symbol;

public class PlayerTest {
	public static final List<Card> blackjackCards = Arrays.asList(new Card(Symbol.ACE, Shape.CLOVER),
		new Card(Symbol.KING, Shape.CLOVER));
	public static final List<Card> bustCards = Arrays.asList(new Card(Symbol.QUEEN, Shape.CLOVER),
		new Card(Symbol.KING, Shape.CLOVER), new Card(Symbol.JACK, Shape.HEART));
	public static final List<Card> nonBustNonBlackjackLowerCards = Arrays.asList(new Card(Symbol.TWO, Shape.CLOVER),
		new Card(Symbol.THREE, Shape.CLOVER));
	public static final List<Card> nonBustNonBlackjackHigherCards = Arrays.asList(new Card(Symbol.FIVE, Shape.CLOVER),
		new Card(Symbol.SEVEN, Shape.CLOVER));

	private Player player;
	private Dealer dealer;

	@BeforeEach
	void setUp() {
		player = new Player(Name.create("플레이어"), Money.create("0"));
		dealer = new Dealer();
	}

	@Test
	@DisplayName("플레이어와 딜러 모두 버스트인 경우 플레이어가 패배하는지")
	void playerDealerBust() {
		for (Card bustCard : bustCards) {
			player.receive(bustCard);
			dealer.receive(bustCard);
		}
		assertThat(player.isLose(dealer)).isTrue();
		assertThat(player.isDraw(dealer)).isFalse();
		assertThat(player.isWin(dealer)).isFalse();
		assertThat(player.isWinByBlackJack(dealer)).isFalse();
	}

	@Test
	@DisplayName("플레이어만 버스트이고 딜러가 블랙잭일 경우 플레이어가 패배하는지")
	void playerBustDealerBlackJack() {
		for (Card bustCard : bustCards) {
			player.receive(bustCard);
		}
		for (Card blackjackCard : blackjackCards) {
			dealer.receive(blackjackCard);
		}
		assertThat(player.isLose(dealer)).isTrue();
		assertThat(player.isDraw(dealer)).isFalse();
		assertThat(player.isWin(dealer)).isFalse();
		assertThat(player.isWinByBlackJack(dealer)).isFalse();
	}

	@Test
	@DisplayName("플레이어만 버스트이고 딜러가 버스트도 블랙잭도 아닐 경우 플레이어가 패배하는지")
	void playerBustDealerNonBust() {
		for (Card bustCard : bustCards) {
			player.receive(bustCard);
		}
		for (Card nonBustNonBlackjackCard : nonBustNonBlackjackLowerCards) {
			dealer.receive(nonBustNonBlackjackCard);
		}
		assertThat(player.isLose(dealer)).isTrue();
		assertThat(player.isDraw(dealer)).isFalse();
		assertThat(player.isWin(dealer)).isFalse();
		assertThat(player.isWinByBlackJack(dealer)).isFalse();
	}

	@Test
	@DisplayName("플레이어와 딜러 모두 블랙잭일 때 무승부가 나오는지")
	void playerDealerBlackJack() {
		for (Card blackjackCard : blackjackCards) {
			player.receive(blackjackCard);
			dealer.receive(blackjackCard);
		}
		assertThat(player.isLose(dealer)).isFalse();
		assertThat(player.isDraw(dealer)).isTrue();
		assertThat(player.isWin(dealer)).isFalse();
		assertThat(player.isWinByBlackJack(dealer)).isFalse();
	}

	@Test
	@DisplayName("플레이어만 블랙잭이고 딜러가 버스트일 때 블랙잭 승리가 나오는지")
	void playerBlackJackDealerBust() {
		for (Card blackjackCard : blackjackCards) {
			player.receive(blackjackCard);
		}
		for (Card bustCard : bustCards) {
			dealer.receive(bustCard);
		}
		assertThat(player.isLose(dealer)).isFalse();
		assertThat(player.isDraw(dealer)).isFalse();
		assertThat(player.isWin(dealer)).isFalse();
		assertThat(player.isWinByBlackJack(dealer)).isTrue();
	}

	@Test
	@DisplayName("플레이어만 블랙잭이고 딜러가 버스트가 아닐 때 블랙잭 승리가 나오는지")
	void playerBlackJackDealerNonBust() {
		for (Card blackjackCard : blackjackCards) {
			player.receive(blackjackCard);
		}
		for (Card nonBustNonBlackjackCard : nonBustNonBlackjackLowerCards) {
			dealer.receive(nonBustNonBlackjackCard);
		}
		assertThat(player.isLose(dealer)).isFalse();
		assertThat(player.isDraw(dealer)).isFalse();
		assertThat(player.isWin(dealer)).isFalse();
		assertThat(player.isWinByBlackJack(dealer)).isTrue();
	}

	@Test
	@DisplayName("플레이어가 버스트도 블랙잭도 아니고 딜러가 버스트일 경우 승리가 나오는지")
	void playerNonDealerBust() {
		for (Card nonBustNonBlackjackLowerCard : nonBustNonBlackjackLowerCards) {
			player.receive(nonBustNonBlackjackLowerCard);
		}
		for (Card bustCard : bustCards) {
			dealer.receive(bustCard);
		}
		assertThat(player.isLose(dealer)).isFalse();
		assertThat(player.isDraw(dealer)).isFalse();
		assertThat(player.isWin(dealer)).isTrue();
		assertThat(player.isWinByBlackJack(dealer)).isFalse();
	}

	@Test
	@DisplayName("플레이어가 버스트도 블랙잭도 아니고 딜러가 블랙잭일 경우 패배가 나오는지")
	void playerNonDealerBlackjack() {
		for (Card nonBustNonBlackjackLowerCard : nonBustNonBlackjackLowerCards) {
			player.receive(nonBustNonBlackjackLowerCard);
		}
		for (Card blackjackCard : blackjackCards) {
			dealer.receive(blackjackCard);
		}
		assertThat(player.isLose(dealer)).isTrue();
		assertThat(player.isDraw(dealer)).isFalse();
		assertThat(player.isWin(dealer)).isFalse();
		assertThat(player.isWinByBlackJack(dealer)).isFalse();
	}

	@Test
	@DisplayName("플레이어와 딜러 모두 버스트나 블랙잭이 아니고, 딜러 점수가 더 높을 경우 패배가 나오는지")
	void playerLowerThanDealer() {
		for (Card nonBustNonBlackjackLowerCard : nonBustNonBlackjackLowerCards) {
			player.receive(nonBustNonBlackjackLowerCard);
		}
		for (Card nonBustNonBlackjackHigherCard : nonBustNonBlackjackHigherCards) {
			dealer.receive(nonBustNonBlackjackHigherCard);
		}
		assertThat(player.isLose(dealer)).isTrue();
		assertThat(player.isDraw(dealer)).isFalse();
		assertThat(player.isWin(dealer)).isFalse();
		assertThat(player.isWinByBlackJack(dealer)).isFalse();
	}

	@Test
	@DisplayName("플레이어와 딜러 모두 버스트나 블랙잭이 아니고, 플레이어 점수가 더 높을 경우 승리가 나오는지")
	void playerHigherThanDealer() {
		for (Card nonBustNonBlackjackHigherCard : nonBustNonBlackjackHigherCards) {
			player.receive(nonBustNonBlackjackHigherCard);
		}
		for (Card nonBustNonBlackjackLowerCard : nonBustNonBlackjackLowerCards) {
			dealer.receive(nonBustNonBlackjackLowerCard);
		}
		assertThat(player.isLose(dealer)).isFalse();
		assertThat(player.isDraw(dealer)).isFalse();
		assertThat(player.isWin(dealer)).isTrue();
		assertThat(player.isWinByBlackJack(dealer)).isFalse();
	}

}
