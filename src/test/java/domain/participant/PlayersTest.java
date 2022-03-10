package domain.participant;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;

public class PlayersTest {

	@Test
	@DisplayName("플레이어들의 베스트 스코어 리스트를 반환")
	void getBestScores() {
		Card card1 = new Card(Rank.RANK_A, Suit.CLOVER);
		Card card2 = new Card(Rank.RANK_K, Suit.CLOVER);
		Card card3 = new Card(Rank.RANK_6, Suit.CLOVER);
		Card card4 = new Card(Rank.RANK_9, Suit.CLOVER);
		List<Card> cards1 = new ArrayList<>(Arrays.asList(card1, card4));
		List<Card> cards2 = new ArrayList<>(Arrays.asList(card2, card3));
		List<List<Card>> initCards = new ArrayList<>(Arrays.asList(cards1, cards2));
		List<Name> names = Arrays.asList(new Name("pobi"), new Name("jason"));
		Players players = new Players(names, initCards);
		assertThat(players.getBestScores()).isEqualTo(List.of(20, 16));
	}
}
