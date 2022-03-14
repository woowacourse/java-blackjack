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
    public BlackjackGame initializeGame(List<String> playerNames) {
        return new BlackjackGame(new CardDeck(), playerNames);
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
            givePlayerCards(player, game);
        }
    }

    // TODO: 2 depth 수정하기
    private void givePlayerCards(Player player, BlackjackGame game) {
        while (player.canReceive()) {
            if (!InputView.requestMorePlayerCardInput(player.getName())) {
                return;
            }
            player.receiveCard(game.popCard());
            OutputView.printSingleHand(ParticipantDto.from(player));
        }

        OutputView.printPlayerBustInfo();
    }

    public void giveExtraCardToDealerIfPossible(BlackjackGame game) {
        if (game.giveCardToDealer()) {
            OutputView.printDealerExtraCardInfo();
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

