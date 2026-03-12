package blackjack.controller;

import blackjack.dto.DealerResultDto;
import blackjack.dto.GameResultDto;
import blackjack.dto.InitialDealDtos;
import blackjack.dto.ParticipantCardsDto;
import blackjack.dto.ParticipantScoreDto;
import blackjack.dto.PlayerResultDto;
import blackjack.model.Answer;
import blackjack.model.Dealer;
import blackjack.model.Deck;
import blackjack.model.Participants;
import blackjack.model.Player;
import blackjack.model.PlayerBlackjackResult;
import blackjack.model.Score;
import blackjack.service.BlackjackService;
import blackjack.view.BlackjackView;
import java.util.List;

public class BlackjackGame {

    private final BlackjackView view;
    private final BlackjackService service;

    public BlackjackGame(BlackjackView view, BlackjackService service) {
        this.view = view;
        this.service = service;
    }

    public void run() {
        Participants participants = Participants.from(view.readPlayers());
        Deck deck = service.createDeck();

        initialDeal(participants, deck);

        hitPlayers(participants.getPlayers(), deck);
        hitDealer(participants.getDealer(), deck);

        printScore(participants);
        printResult(participants);
    }

    private void initialDeal(Participants participants, Deck deck) {
        service.initialDeal(participants, deck);
        view.printInitialDeal(InitialDealDtos.from(participants));
    }

    private void hitPlayers(List<Player> players, Deck deck) {
        players.forEach(player -> hitPlayer(player, deck));
    }

    private void hitPlayer(Player player, Deck deck) {
        while (service.canHit(player) && view.askHit(player.getName()) == Answer.YES) {
            service.hitPlayer(player, deck);
            view.printPlayerCards(ParticipantCardsDto.fromAllCards(player));
        }
    }

    private void hitDealer(Dealer dealer, Deck deck) {
        if (service.shouldHit(dealer)) {
            service.hitDealer(dealer, deck);
            view.printDealerHit(dealer);
        }
    }

    private void printScore(Participants participants) {
        List<ParticipantScoreDto> participantScoreDtos = participants.stream()
            .map(participant -> {
                Score score = service.calculate(participant.getCards());
                return ParticipantScoreDto.from(participant, score);
            })
            .toList();
        view.printScore(participantScoreDtos);
    }

    private void printResult(Participants participants) {
        List<PlayerResultDto> playerResultDtos = participants.getPlayers().stream()
            .map(player -> {
                    PlayerBlackjackResult result = service.judge(player, participants.getDealer());
                    return PlayerResultDto.of(player, result);
                }
            ).toList();
        DealerResultDto dealerResultDto = DealerResultDto.from(playerResultDtos);
        view.printResult(new GameResultDto(dealerResultDto, playerResultDtos));
    }
}
