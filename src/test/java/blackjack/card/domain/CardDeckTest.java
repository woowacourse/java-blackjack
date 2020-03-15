package blackjack.card.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Collections;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardDeckTest {

	@DisplayName("CardDeck 에 더 이상 없을때 뽑으려하면 Exception 발생")
	@Test
	void drawCard() {
		CardDeck cardDeck = CardDeck.getInstance(Collections.EMPTY_LIST);

		assertThatThrownBy(cardDeck::draw)
			.isInstanceOf(NoSuchElementException.class);
	}

}