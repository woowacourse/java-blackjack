package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.Card;
import domain.card.Shape;
import domain.card.Symbol;
import domain.participant.Dealer;
import domain.participant.Money;
import domain.participant.Name;
import domain.participant.Player;

public class PlayerResultTest {
	private static final List<Name> names = Arrays.asList(Name.create("참여자1"), Name.create("참여자2"),
		Name.create("참여자3"));
	private static final List<Money> moneys = Arrays.asList(Money.create("100"), Money.create("200"),
		Money.create("300"));

	@Test
	@DisplayName("수익 계산이 제대로 되는지")
	void profitTest() {
		GameParticipant gameParticipant = new GameParticipant(new Players(names, moneys));
		Dealer dealer = gameParticipant.getDealer();
		Player player1 = gameParticipant.getPlayers().getPlayers().get(0);
		Player player2 = gameParticipant.getPlayers().getPlayers().get(1);
		Player player3 = gameParticipant.getPlayers().getPlayers().get(2);
		dealer.receive(new Card(Symbol.NINE, Shape.CLOVER));
		dealer.receive(new Card(Symbol.TEN, Shape.CLOVER));
		player1.receive(new Card(Symbol.ACE, Shape.HEART));
		player1.receive(new Card(Symbol.KING, Shape.HEART));
		player2.receive(new Card(Symbol.EIGHT, Shape.HEART));
		player2.receive(new Card(Symbol.QUEEN, Shape.HEART));
		player2.receive(new Card(Symbol.FOUR, Shape.HEART));
		player3.receive(new Card(Symbol.JACK, Shape.HEART));
		player3.receive(new Card(Symbol.NINE, Shape.HEART));
		Map<Player, Double> playerDoubleMap = new PlayerResult(gameParticipant).getResult();
		assertThat(playerDoubleMap.get(player1)).isEqualTo(150);
		assertThat(playerDoubleMap.get(player2)).isEqualTo(-200);
		assertThat(playerDoubleMap.get(player3)).isEqualTo(0);

	}
}
