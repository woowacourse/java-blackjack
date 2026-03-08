package blackjack.controller;

import blackjack.dto.ParticipantCardsDto;
import blackjack.dto.ParticipantInitialDealDtos;
import blackjack.dto.ParticipantScoreDto;
import blackjack.dto.ResultDto;
import blackjack.model.Answer;
import blackjack.model.BlackjackResult;
import blackjack.model.Dealer;
import blackjack.model.Deck;
import blackjack.model.Participant;
import blackjack.model.Participants;
import blackjack.model.Score;
import blackjack.service.BlackjackService;
import blackjack.view.BlackjackView;
import java.util.List;

public class BlackjackController {

    private final BlackjackView view;
    private final BlackjackService service;

    public BlackjackController(BlackjackView view, BlackjackService service) {
        this.view = view;
        this.service = service;
    }

    public void run() {
        Participants participants = Participants.from(view.readPlayers());
        Deck deck = service.createDeck();

        service.initialDeal(participants, deck);
        view.printInitialDeal(ParticipantInitialDealDtos.from(participants));

        hitPlayers(participants.getPlayers(), deck);
        hitDealer(participants.getDealer(), deck);

        printScore(participants);
        printResult(participants);
    }

    private void hitPlayers(List<Participant> players, Deck deck) {
        for (Participant player : players) {
            hitPlayer(player, deck);
        }
    }

    private void hitPlayer(Participant player, Deck deck) {
        while (service.canHit(player)) {
            Answer answer = Answer.from(view.askHit(player.getName()));
            service.hitPlayer(player, deck, answer);
            view.printPlayerCards(ParticipantCardsDto.fromAllCards(player));
        }
    }

    private void hitDealer(Dealer dealer, Deck deck) {
        if (service.shouldHit(dealer)) {
            service.hitDealer(dealer, deck);
            view.printDealerHit();
        }
    }

    private void printScore(Participants participants) {
        List<ParticipantScoreDto> participantScoreDtos = participants.stream()
            .map(participant -> {
                Score score = service.calculate(participant);
                return ParticipantScoreDto.from(participant, score);
            })
            .toList();
        view.printScore(participantScoreDtos);
    }

    private void printResult(Participants participants) {
        List<ResultDto> resultDtos = participants.getPlayers().stream()
            .map(player -> {
                    BlackjackResult result = service.judge(player, participants.getDealer());
                    return new ResultDto(player.getName(), result);
                }
            ).toList();
        view.printResult(resultDtos);
    }
}
