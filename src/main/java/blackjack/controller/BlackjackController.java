package blackjack.controller;


import blackjack.domain.card.CardDeck;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.participant.Player;
import blackjack.dto.DealerDto;
import blackjack.dto.ParticipantDto;
import blackjack.dto.PlayerDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public class BlackjackController {
    public void run() {
        BlackjackGame blackjackGame = initializeGame();
        printInitialHand(blackjackGame);
        giveExtraCardsToAllPlayer(blackjackGame);
        giveExtraCardsToDealer(blackjackGame);
        printFinalHandAndScore(blackjackGame);
        printBetResult(blackjackGame);
    }

    private BlackjackGame initializeGame() {
        try {
            BlackjackGame blackjackGame = new BlackjackGame(new CardDeck());

            requestPlayerNames(blackjackGame);

            return blackjackGame;
        } catch (IllegalArgumentException exception) {
            OutputView.printException(exception);
            return initializeGame();
        }
    }

    private void requestPlayerNames(BlackjackGame blackjackGame) {
        List<String> playerNames = InputView.inputPlayerNames();
        for (String playerName : playerNames) {
            addPlayerIntoBlackjackGameByBetMoneyInput(blackjackGame, playerName);
        }
    }

    private void addPlayerIntoBlackjackGameByBetMoneyInput(BlackjackGame blackjackGame, String playerName) {
        try {
            int rawBetMoney = InputView.inputBetMoney(playerName);
            blackjackGame.addPlayer(playerName, rawBetMoney);
        } catch (IllegalArgumentException exception) {
            OutputView.printException(exception);
            addPlayerIntoBlackjackGameByBetMoneyInput(blackjackGame, playerName);
        }
    }

    private void printInitialHand(BlackjackGame game) {
        DealerDto dealerDto = DealerDto.from(game.getDealer());
        List<PlayerDto> playerDtos = PlayerDto.from(game.getPlayers());

        printInitialInfo(playerDtos, dealerDto);
    }

    private void printInitialInfo(List<PlayerDto> playerDtos, DealerDto dealerDto) {
        OutputView.printInitialDistributionInfo(playerDtos);
        OutputView.printInitialDealerHand(dealerDto);
        OutputView.printInitialPlayersHand(playerDtos);
    }

    private void giveExtraCardsToAllPlayer(BlackjackGame game) {
        for (Player player : game.getPlayers().getValue()) {
            giveCardsToPlayer(player, game);
        }
    }

    private void giveCardsToPlayer(Player player, BlackjackGame game) {
        try {
            giveSingleCardToPlayerOnMoreCardInput(player, game);
        } catch (IllegalArgumentException exception) {
            OutputView.printException(exception);
            giveCardsToPlayer(player, game);
        }
    }

    private void giveSingleCardToPlayerOnMoreCardInput(Player player, BlackjackGame game) {
        if (InputView.inputMorePlayerCard(player.getName())) {
            giveSingleCardToPlayer(player, game);
        }
    }

    private void giveSingleCardToPlayer(Player player, BlackjackGame game) {
        if (game.giveExtraCardToPlayer(player)) {
            OutputView.printHandOfPlayer(PlayerDto.from(player));
            giveCardsToPlayer(player, game);
            return;
        }

        printHandAndBustMessage(player);
    }

    private void printHandAndBustMessage(Player player) {
        OutputView.printHandOfPlayer(PlayerDto.from(player));
        OutputView.printPlayerBustInfo();
    }

    private void giveExtraCardsToDealer(BlackjackGame game) {
        int extraCardCount = game.giveExtraCardsToDealer();

        if (extraCardCount > 0) {
            OutputView.printDealerExtraCardInfo(extraCardCount);
        }
    }

    private void printFinalHandAndScore(BlackjackGame game) {
        List<ParticipantDto> participantDtos = new ArrayList<>();
        participantDtos.add(DealerDto.from(game.getDealer()));
        participantDtos.addAll(PlayerDto.from(game.getParticipantGroup().getPlayers()));

        OutputView.printHandAndScore(participantDtos);
    }

    private void printBetResult(BlackjackGame game) {
        OutputView.printProfitResultInfo();
        OutputView.printProfitOfParticipantGroup(game.getParticipantGroup());
    }
}
