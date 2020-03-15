package blackjack.domain.card;

import java.util.List;

public interface Drawable {
	Card draw();

	List<Card> draw(int num);
}
