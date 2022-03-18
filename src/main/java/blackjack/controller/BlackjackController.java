package blackjack.controller;


import blackjack.domain.card.CardDeck;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.game.DealerProfitResult;
import blackjack.domain.game.PlayerBetResult;
import blackjack.domain.money.BetAndProfit;
import blackjack.domain.money.Money;
import blackjack.domain.participant.Player;
import blackjack.dto.ParticipantDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackController {
    public void run() {
        BlackjackGame blackjackGame = initializeGame();

        Map<Player, BetAndProfit> playerBets = requestBetToAllPlayer(blackjackGame);
        printInitialHand(blackjackGame);
        giveExtraCardsToAllPlayer(blackjackGame);
        giveExtraCardsToDealer(blackjackGame);
        printFinalHandAndScore(blackjackGame);
        printBetResult(blackjackGame, playerBets);
    }

    public BlackjackGame initializeGame() {
        try {
            List<String> playerNames = InputView.inputPlayerNames();
            return new BlackjackGame(new CardDeck(), playerNames);
        } catch (IllegalArgumentException exception) {
            OutputView.printException(exception);
            return initializeGame();
        }
    }

    public Map<Player, BetAndProfit> requestBetToAllPlayer(BlackjackGame game) {
        List<Player> players = game.getPlayers();
        Map<Player, BetAndProfit> playerBetResults = new LinkedHashMap<>();
        for (Player player : players) {
            Money betMoney = requestBetToSinglePlayer(player);
            playerBetResults.put(player, BetAndProfit.from(betMoney));
        }

        return playerBetResults;
    }

    private Money requestBetToSinglePlayer(Player player) {
        try {
            return Money.from(InputView.inputBetMoney(player.getName()));
        } catch (IllegalArgumentException exception) {
            OutputView.printException(exception);
            return requestBetToSinglePlayer(player);
        }
    }

    // TODO: 추후 participants 에 dealer 가 포함되는 구조로 개선되면 수정 필요
    public void printInitialHand(BlackjackGame game) {
        ParticipantDto dealerDto = ParticipantDto.from(game.getDealer());
        List<ParticipantDto> playerDtos = game.getPlayers()
                .stream()
                .map(ParticipantDto::from)
                .collect(Collectors.toList());

        printInitialInfo(playerDtos, dealerDto);
    }

    private void printInitialInfo(List<ParticipantDto> playerDtos, ParticipantDto dealerDto) {
        OutputView.printInitialDistributionInfo(playerDtos);
        OutputView.printInitialDealerHand(dealerDto);
        OutputView.printInitialPlayersHand(playerDtos);
    }

    public void giveExtraCardsToAllPlayer(BlackjackGame game) {
        List<Player> players = game.getPlayers();
        for (Player player : players) {
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
            OutputView.printParticipantHand(ParticipantDto.from(player));
            giveCardsToPlayer(player, game);
            return;
        }

        printHandAndBustMessage(player);
    }

    private void printHandAndBustMessage(Player player) {
        OutputView.printParticipantHand(ParticipantDto.from(player));
        OutputView.printPlayerBustInfo();
    }

    public void giveExtraCardsToDealer(BlackjackGame game) {
        int extraCardCount = game.giveExtraCardsToDealer();

        if (extraCardCount > 0) {
            OutputView.printDealerExtraCardInfo(extraCardCount);
        }
    }

    // TODO: 추후 participants 에 dealer 가 포함되는 구조로 개선되면 수정 필요
    public void printFinalHandAndScore(BlackjackGame game) {
        List<ParticipantDto> participants = new ArrayList<>(List.of(ParticipantDto.from(game.getDealer())));
        participants.addAll(game.getPlayers()
                .stream()
                .map(ParticipantDto::from)
                .collect(Collectors.toList()));

        OutputView.printHandAndScore(participants);
    }

    public void printBetResult(BlackjackGame game, Map<Player, BetAndProfit> playerBets) {
        DealerProfitResult dealerProfitResult = DealerProfitResult.of(game.getDealer(), playerBets);
        PlayerBetResult playerBetResult = PlayerBetResult.of(playerBets, game.getDealer());

        OutputView.printProfitResultInfo();
        OutputView.printDealerProfitResult(dealerProfitResult);
        OutputView.printPlayerBetResult(playerBetResult);
    }

}
