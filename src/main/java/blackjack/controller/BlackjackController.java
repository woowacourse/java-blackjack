package blackjack.controller;

import blackjack.dto.ParticipantCardsDto;
import blackjack.dto.ParticipantInitialDealDtos;
import blackjack.dto.ParticipantScoreDto;
import blackjack.dto.PlayerResultDto;
import blackjack.model.Answer;
import blackjack.model.PlayerBlackjackResult;
import blackjack.model.Dealer;
import blackjack.model.Deck;
import blackjack.model.Participants;
import blackjack.model.Player;
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

        initialDeal(participants, deck);

        hitPlayers(participants.getPlayers(), deck);
        hitDealer(participants.getDealer(), deck);

        printScore(participants);
        printResult(participants);
    }

    private void initialDeal(Participants participants, Deck deck) {
        service.initialDeal(participants, deck);
        view.printInitialDeal(ParticipantInitialDealDtos.from(participants));
    }

    private void hitPlayers(List<Player> players, Deck deck) {
        for (Player player : players) {
            hitPlayer(player, deck);
        }
    }

    private void hitPlayer(Player player, Deck deck) {
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
                    return new PlayerResultDto(player.getName(), result);
                }
            ).toList();
        view.printResult(playerResultDtos);
    }
}
