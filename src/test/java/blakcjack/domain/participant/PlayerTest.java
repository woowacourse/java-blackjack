package blakcjack.domain.participant;

import blakcjack.domain.card.*;
import blakcjack.domain.money.Money;
import blakcjack.domain.name.Name;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {
	private Player player;

	@BeforeEach
	void setUp() {
		player = new Player(new Name("pobi"), new Money(10));
	}

	@DisplayName("플레이어 객체 생성 제대로 하는지")
	@Test
	void create() {
		final Player player = new Player(new Name("pobi"), new Money(10));
		assertThat(player).isEqualTo(new Player(new Name("pobi"), new Money(10)));
	}

	@DisplayName("카드 제대로 뽑는지")
	@Test
	void drawCard_deck_drawOneCardFromDeck() {
		final Deck customDeck = createCustomDeck(Card.of(CardSymbol.CLUB, CardNumber.ACE));
		player.drawOneCardFrom(customDeck);

		Cards cards = new Cards();
		cards.add(Card.of(CardSymbol.CLUB, CardNumber.ACE));
		assertThat(player.getCards()).isEqualTo(cards);
	}

	@DisplayName("패에 ACE가 여러장 있는 경우에 점수 계산을 제대로 하는지")
	@Test
	void score() {
		final Deck customDeck = createCustomDeck(
				Card.of(CardSymbol.CLUB, CardNumber.FIVE),
				Card.of(CardSymbol.CLUB, CardNumber.ACE),
				Card.of(CardSymbol.DIAMOND, CardNumber.ACE),
				Card.of(CardSymbol.HEART, CardNumber.ACE),
				Card.of(CardSymbol.SPADE, CardNumber.ACE)
		);
		player.drawOneCardFrom(customDeck);
		player.drawOneCardFrom(customDeck);
		player.drawOneCardFrom(customDeck);
		player.drawOneCardFrom(customDeck);
		player.drawOneCardFrom(customDeck);

		assertThat(player.getScore()).isEqualTo(19);
	}

	@DisplayName("점수가 21점 이하면 false 21점 초과일 시 true")
	@Test
	void isBust() {
		final Deck customDeck = createCustomDeck(
				Card.of(CardSymbol.CLUB, CardNumber.TWO),
				Card.of(CardSymbol.SPADE, CardNumber.QUEEN),
				Card.of(CardSymbol.CLUB, CardNumber.KING)
		);
		player.drawOneCardFrom(customDeck);
		player.drawOneCardFrom(customDeck);
		assertThat(player.isBust()).isFalse();

		player.drawOneCardFrom(customDeck);
		assertThat(player.isBust()).isTrue();
	}

	private Deck createCustomDeck(final Card... cards) {
		return new Deck(Arrays.asList(cards));
	}
}
