package blackjack.controller;


import blackjack.domain.card.CardDeck;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.dto.DealerMatchDto;
import blackjack.dto.ParticipantDto;
import blackjack.dto.PlayerMatchDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BlackjackController {
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
        List<ParticipantDto> playerDtos = game.getParticipants()
                .stream()
                .map(ParticipantDto::from)
                .collect(Collectors.toList());

        OutputView.printInitialDistributionInfo(playerDtos);
        OutputView.printInitialDealerHand(dealerDto);
        OutputView.printInitialPlayersHand(playerDtos);
    }

    public void giveCardsToAllPlayer(BlackjackGame game) {
        List<Player> players = game.getParticipants();
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

    public void giveExtraCardToDealer(BlackjackGame game) {
        int extraCardCount = game.giveExtraCardsToDealer();

        if (extraCardCount > 0) {
            OutputView.printDealerExtraCardInfo(extraCardCount);
        }
    }

    // TODO: 추후 participants 에 dealer 가 포함되는 구조로 개선되면 수정 필요
    public void printFinalHandAndScore(BlackjackGame game) {
        List<ParticipantDto> participants = new ArrayList<>(List.of(ParticipantDto.from(game.getDealer())));
        participants.addAll(game.getParticipants()
                .stream()
                .map(ParticipantDto::from)
                .collect(Collectors.toList()));

        OutputView.printHandAndScore(participants);
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

