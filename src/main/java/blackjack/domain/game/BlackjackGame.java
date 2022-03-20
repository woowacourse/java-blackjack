package blackjack.domain.game;

import static blackjack.domain.role.Dealer.CAN_DRAW_STANDARD;
import static blackjack.domain.role.Dealer.CAN_NOT_DRAW_STANDARD;
import static blackjack.domain.state.Ready.READY_CARD_NUMBER;

import blackjack.domain.role.PlayerDrawChoice;
import blackjack.domain.role.PlayerTurns;
import blackjack.domain.role.Role;
import blackjack.domain.role.Roles;
import blackjack.dto.DealerTurnDto;
import blackjack.dto.FinalResultDto;
import blackjack.dto.PlayerStatusDto;
import blackjack.dto.PlayerTurnsDto;
import blackjack.dto.TableDto;
import java.util.List;
import java.util.stream.Collectors;

public class BlackjackGame {

	private final Deck deck;
	private final Roles roles;

	public BlackjackGame(final Deck deck, final Roles roles) {
		this.deck = deck;
		this.roles = roles;
	}

	public List<TableDto> ready() {
		List<Role> readyRoles = roles.ready(deck);
		return readyRoles.stream()
				.map(TableDto::from)
				.collect(Collectors.toList());
	}

	public PlayerTurnsDto getPlayerDrawTurn() {
		if (roles.isDealerBlackjack()) {
			return PlayerTurnsDto.from(PlayerTurns.createEmpty());
		}
		return PlayerTurnsDto.from(roles.getPlayerTurn());
	}

	public PlayerStatusDto drawPlayer(final PlayerDrawChoice answer, final String name) {
		Role afterDrawPlayer = roles.drawPlayer(answer, name, deck);
		return PlayerStatusDto.from(afterDrawPlayer);
	}

	public DealerTurnDto drawDealer() {
		Role dealer = roles.drawDealer(deck);
		if (dealer.getCards().getSize() == READY_CARD_NUMBER) {
			return DealerTurnDto.from(dealer, false, CAN_NOT_DRAW_STANDARD);
		}
		return DealerTurnDto.from(dealer, true, CAN_DRAW_STANDARD);
	}

	public FinalResultDto calculateFinalResult() {
		final Revenue revenue = new Revenue(roles.getPlayersProfit());
		return FinalResultDto.from(roles, revenue);
	}
}
