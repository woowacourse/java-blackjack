package domain.participant;

import static domain.card.Cards.*;
import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.Card;
import domain.participant.info.Betting;
import domain.participant.info.Hand;
import domain.participant.info.Name;

public class PlayersTest {
	private List<Card> cards_21;
	private List<Card> cards_BURST;
	private Player pobi;
	private Player jason;

	@BeforeEach
	void setUp() {
		cards_21 = new ArrayList<>(Arrays.asList(ACE_CLOVER, QUEEN_CLOVER));
		cards_BURST = new ArrayList<>(Arrays.asList(KING_CLOVER, QUEEN_CLOVER, TWO_CLOVER));
		pobi = new Player(new Name("pobi"), new Hand(cards_21), new Betting(0));
		jason = new Player(new Name("jason"), new Hand(cards_BURST), new Betting(0));
	}

	@Test
	@DisplayName("플레이어가 Bust 인지 판별")
	void checkBust() {
		Players players = new Players(Arrays.asList(pobi, jason));
		int jasonIdx = 1;
		assertThat(players.checkBust(jasonIdx)).isTrue();
	}

	@Test
	@DisplayName("플레이어 draw가 정상적으로 되는지 확인")
	void addCard() {
		Players players = new Players(Arrays.asList(pobi, jason));
		int pobiIdx = 0;
		players.addCard(0, SIX_CLOVER);
		assertThat(pobi.showHand()).isEqualTo(List.of("A클로버", "Q클로버", "6클로버"));
	}
}
