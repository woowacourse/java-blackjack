package blackjack.controller;


import blackjack.domain.card.CardDeck;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.game.DealerMatchResult;
import blackjack.domain.game.PlayerMatchResult;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.dto.ParticipantDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BlackjackController {
    public void run() {
        BlackjackGame blackjackGame = initializeGame();

        printInitialHand(blackjackGame);
        giveExtraCardsToAllPlayer(blackjackGame);
        giveExtraCardsToDealer(blackjackGame);
        printFinalHandAndScore(blackjackGame);
        printDealerMatchResult(blackjackGame);
        printPlayersMatchResult(blackjackGame);
    }

    public BlackjackGame initializeGame() {
        try {
            List<String> playerNames = InputView.requestPlayerNamesInput();
            return new BlackjackGame(new CardDeck(), playerNames);
        } catch (IllegalArgumentException exception) {
            OutputView.printException(exception);
            return initializeGame();
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
        if (InputView.requestMorePlayerCardInput(player.getName())) {
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

    public void printDealerMatchResult(BlackjackGame game) {
        DealerMatchResult dealerMatchResult = DealerMatchResult.of(game.getDealer(), game.getPlayers());
        OutputView.printDealerMatchResult(dealerMatchResult);
    }

    public void printPlayersMatchResult(BlackjackGame game) {
        Dealer dealer = game.getDealer();

        List<PlayerMatchResult> playerMatchResults = game.getPlayers()
                .stream()
                .map(player -> PlayerMatchResult.of(player, dealer))
                .collect(Collectors.toList());

        OutputView.printPlayerMatchResults(playerMatchResults);
    }
}

