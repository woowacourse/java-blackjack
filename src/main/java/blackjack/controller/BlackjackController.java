package blackjack.controller;

import blackjack.dto.ParticipantCardsDto;
import blackjack.dto.ParticipantInitialDealDtos;
import blackjack.dto.ParticipantScoreDto;
import blackjack.dto.ResultDto;
import blackjack.model.Answer;
import blackjack.model.BlackjackResult;
import blackjack.model.BustPolicy;
import blackjack.model.CardsGenerator;
import blackjack.model.Dealer;
import blackjack.model.DealerHitPolicy;
import blackjack.model.Deck;
import blackjack.model.Participant;
import blackjack.model.Participants;
import blackjack.model.ResultJudgement;
import blackjack.model.Score;
import blackjack.model.ScoreCalculator;
import blackjack.view.BlackjackView;
import java.util.List;

public class BlackjackController {

    private static final int INITIAL_DEAL_COUNT = 2;

    private final BlackjackView view;

    private final ScoreCalculator scoreCalculator;
    private final DealerHitPolicy dealerHitPolicy;
    private final BustPolicy bustPolicy;

    private final CardsGenerator cardsGenerator;
    private final ResultJudgement resultJudgement;

    public BlackjackController(
        BlackjackView view,
        ScoreCalculator scoreCalculator,
        DealerHitPolicy dealerHitPolicy,
        BustPolicy bustPolicy,
        CardsGenerator cardsGenerator,
        ResultJudgement resultJudgement
    ) {
        this.view = view;
        this.scoreCalculator = scoreCalculator;
        this.dealerHitPolicy = dealerHitPolicy;
        this.bustPolicy = bustPolicy;
        this.cardsGenerator = cardsGenerator;
        this.resultJudgement = resultJudgement;
    }

    public void run() {
        Participants participants = Participants.from(view.readPlayers());
        Deck deck = Deck.shuffled(cardsGenerator);

        initialDeal(participants, deck);
        hitPlayers(participants.getPlayers(), deck);
        hitDealer(participants.getDealer(), deck);

        printScore(participants);
        printResult(participants);
    }

    private void initialDeal(Participants participants, Deck deck) {
        for (int i = 0; i < INITIAL_DEAL_COUNT; i++) {
            deal(participants, deck);
        }
        view.printInitialDeal(ParticipantInitialDealDtos.from(participants));
    }

    private void deal(Participants participants, Deck deck) {
        for (Participant participant : participants) {
            participant.hit(deck.draw());
        }
    }

    private void hitPlayers(List<Participant> players, Deck deck) {
        for (Participant player : players) {
            hitPlayer(player, deck);
        }
    }

    private void hitPlayer(Participant player, Deck deck) {
        while (!bustPolicy.isBust(scoreCalculator.calculate(player.getCards()))
            && Answer.from(view.askHit(player.getName())) == Answer.YES) {
            player.hit(deck.draw());
            view.printPlayerCards(ParticipantCardsDto.fromAllCards(player));
        }
    }

    private void hitDealer(Dealer dealer, Deck deck) {
        if (dealer.shouldHit(dealerHitPolicy, scoreCalculator)) {
            dealer.hit(deck.draw());
            view.printDealerHit();
        }
    }

    private void printScore(Participants participants) {
        List<ParticipantScoreDto> participantScoreDtos = participants.stream()
            .map(participant -> {
                Score score = scoreCalculator.calculate(participant.getCards());
                return ParticipantScoreDto.from(participant, score);
            })
            .toList();
        view.printScore(participantScoreDtos);
    }

    private void printResult(Participants participants) {
        List<ResultDto> resultDtos = participants.getPlayers().stream()
            .map(player -> {
                    BlackjackResult result = resultJudgement.judge(
                        scoreCalculator.calculate(player.getCards()),
                        scoreCalculator.calculate(participants.getDealer().getCards()));
                    return new ResultDto(player.getName(), result);
                }
            ).toList();
        view.printResult(resultDtos);
    }
}
