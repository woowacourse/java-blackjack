package BlackJack;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

public class DealTest {

	@Test
	void testPickCards() {
		
		Player player = new Player("pobi");
        List<Player> players = new ArrayList<>();
        players.add(player);
        Dealer dealer = new Dealer();
        Deal deal = new Deal(players, dealer);
		
        Random fixedRandom = new Random(0);
		String result = deal.PickCards(player);
		
		assertTrue(result.equals("하트A"));
	}
}
