package domain.participant;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;

public class PlayerTest {

	@Test
	@DisplayName("플레이어들의 베스트 스코어 리스트를 반환_포비")
	void getBestScore_pobi() {
		Card card1 = new Card(Rank.RANK_A, Suit.CLOVER);
		Card card2 = new Card(Rank.RANK_9, Suit.CLOVER);
		List<Card> initCards = Arrays.asList(card1, card2);
		Name name = new Name("pobi");
		Player player = new Player(name, initCards);
		assertThat(player.getBestScore()).isEqualTo(20);
	}

	@Test
	@DisplayName("플레이어들의 베스트 스코어 리스트를 반환_제이슨")
	void getBestScores_jason() {
		Card card1 = new Card(Rank.RANK_K, Suit.CLOVER);
		Card card2 = new Card(Rank.RANK_6, Suit.CLOVER);
		List<Card> initCards = Arrays.asList(card1, card2);
		Name name = new Name("jason");
		Player player = new Player(name, initCards);
		assertThat(player.getBestScore()).isEqualTo(16);
	}
}
