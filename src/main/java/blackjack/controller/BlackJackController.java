package blackjack.controller;

import blackjack.domain.BlackJack;
import blackjack.domain.GameResult;
import blackjack.domain.Player;
import blackjack.domain.PlayerGroup;
import blackjack.dto.DealerResultDto;
import blackjack.dto.GamerCardsDto;
import blackjack.dto.PlayerResultDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackJackController {

    public void run() {
        PlayerGroup playerGroup = initializePlayerGroup();
        BlackJack blackJack = new BlackJack(playerGroup);
        blackJack.divideCards();

        OutputView.printGamersCards(GamerCardsDto.of(blackJack.getGamers()));

        List<Player> players = playerGroup.getPlayers();
        for (Player player : players) {
            requestCardAddition(blackJack, player);
        }

        OutputView.printDealerCardMessage(blackJack.addCardToDealer());

        blackJack.openDealerCards();
        OutputView.printGamersCardAndSum(GamerCardsDto.of(blackJack.getGamers()));

        GameResult gameResult = blackJack.getGameResult();
        OutputView.printGameResult(DealerResultDto.of(gameResult.getDealerResult()),
                PlayerResultDto.of(gameResult.getPlayerResult()));
    }

    private void requestCardAddition(BlackJack blackJack, Player player) {
        if (InputView.requestCardAddition(player.getName())) {
            blackJack.addCardTo(player);
            OutputView.printGamerCard(GamerCardsDto.of(player.getName(), player.getCardGroup()));
            requestCardAdditionToPlayer(blackJack, player);
            return;
        }
        OutputView.printGamerCard(GamerCardsDto.of(player.getName(), player.getCardGroup()));
    }

    private void requestCardAdditionToPlayer(BlackJack blackJack, Player player) {
        if (player.isAddable()) {
            requestCardAddition(blackJack, player);
        }
    }

    private PlayerGroup initializePlayerGroup() {
        try {
            return new PlayerGroup(Player.of(InputView.requestPlayerNames()));
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return initializePlayerGroup();
        }
    }
}
