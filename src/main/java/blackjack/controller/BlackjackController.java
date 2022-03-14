package blackjack.controller;


import blackjack.domain.card.CardDeck;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.game.dto.DealerMatchDto;
import blackjack.domain.game.dto.PlayerMatchDto;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.stream.Collectors;

public class BlackjackController {
    public BlackjackGame initializeGame(List<String> playerNames) {
        return new BlackjackGame(new CardDeck(), playerNames);
    }

    public void givePlayerCards(Player player, BlackjackGame game) {
        while (player.canReceive()) {
            if (!InputView.requestMorePlayerCardInput(player.getName())) {
                return;
            }
            player.receiveCard(game.popCard());
            OutputView.printPlayerCardsInfo(player);
        }

        OutputView.printPlayerBustInfo();
    }

    public void printDealerMatchDto(BlackjackGame game) {
        DealerMatchDto dealerMatchDto = DealerMatchDto.of(game.getDealer(), game.getParticipants());
        OutputView.printDealerMatchResult(dealerMatchDto);
    }

    public void printPlayersMatchDto(BlackjackGame game) {
        Dealer dealer = game.getDealer();

        List<PlayerMatchDto> playerMatchDtos = game.getParticipants()
                .stream()
                .map(player -> PlayerMatchDto.of(player, dealer))
                .collect(Collectors.toList());

        OutputView.printPlayerMatchResults(playerMatchDtos);
    }
}

