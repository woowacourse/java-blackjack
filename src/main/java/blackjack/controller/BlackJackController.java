package blackjack.controller;

import blackjack.domain.BlackJack;
import blackjack.domain.Player;
import blackjack.domain.PlayerGroup;
import blackjack.dto.GamerCardsDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackJackController {

    public void run() {
        PlayerGroup playerGroup = initializePlayerGroup();
        BlackJack blackJack = new BlackJack(playerGroup);
        blackJack.divideCards();

        List<GamerCardsDto> gamersCardsDto = GamerCardsDto.of(blackJack.getGamersCards());
        OutputView.printGamersCards(gamersCardsDto);

        List<Player> players = playerGroup.getPlayers();
        for (Player player : players) {
            requestCardAddition(blackJack, player);
        }

        addDealerCards(blackJack);
    }

    private void addDealerCards(BlackJack blackJack) {
        while (blackJack.isDealerAddable()) {
            blackJack.addCardToDealer();
            OutputView.printDealCardMessage();
        }
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
