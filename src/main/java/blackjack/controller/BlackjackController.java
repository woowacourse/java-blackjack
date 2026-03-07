package blackjack.controller;

import blackjack.dto.CardDto;
import blackjack.dto.ParticipantInitialDealDto;
import blackjack.dto.ParticipantScoreDto;
import blackjack.dto.ResultDto;
import blackjack.model.Answer;
import blackjack.model.BlackjackResult;
import blackjack.model.BustPolicy;
import blackjack.model.Card;
import blackjack.model.CardsGenerator;
import blackjack.model.Dealer;
import blackjack.model.DealerHitPolicy;
import blackjack.model.Deck;
import blackjack.model.Participant;
import blackjack.model.Participants;
import blackjack.model.ResultJudgement;
import blackjack.model.ScoreCalculator;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackjackController {

    private static final int INITIAL_DEAL_COUNT = 2;

    private final InputView inputView;
    private final OutputView outputView;

    private final ScoreCalculator scoreCalculator;
    private final DealerHitPolicy dealerHitPolicy;
    private final BustPolicy bustPolicy;

    private final CardsGenerator cardsGenerator;
    private final ResultJudgement resultJudgement;

    public BlackjackController(
        InputView inputView,
        OutputView outputView,
        ScoreCalculator scoreCalculator,
        DealerHitPolicy dealerHitPolicy,
        BustPolicy bustPolicy,
        CardsGenerator cardsGenerator,
        ResultJudgement resultJudgement
    ) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.scoreCalculator = scoreCalculator;
        this.dealerHitPolicy = dealerHitPolicy;
        this.bustPolicy = bustPolicy;
        this.cardsGenerator = cardsGenerator;
        this.resultJudgement = resultJudgement;
    }

    public void run() {
        Participants participants = readPlayers();
        Deck deck = Deck.shuffled(cardsGenerator);

        initialDeal(participants, deck);
        hitPlayers(participants.getPlayers(), deck);
        hitDealer(participants.getDealer(), deck);

        printScore(participants, scoreCalculator);
        printResult(participants);
    }

    private Participants readPlayers() {
        String rawPlayerNames = inputView.readPlayerNames();
        return Participants.from(rawPlayerNames);
    }

    private void initialDeal(Participants participants, Deck deck) {
        for (int i = 0; i < INITIAL_DEAL_COUNT; i++) {
            deal(participants, deck);
        }
        outputView.printInitialDeal(participantsToDots(participants));
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
            && askHit(player.getName()) == Answer.YES) {
            player.hit(deck.draw());
            outputView.printPlayerCards(player.getName(), cardsToDtos(player.getCards()));
        }
    }

    private Answer askHit(String playerName) {
        String answer = inputView.askHit(playerName);
        return Answer.from(answer);
    }

    private void hitDealer(Dealer dealer, Deck deck) {
        if (dealer.shouldHit(dealerHitPolicy, scoreCalculator)) {
            dealer.hit(deck.draw());
            outputView.printDealerHit();
        }
    }

    private void printScore(Participants participants, ScoreCalculator scoreCalculator) {
        List<ParticipantScoreDto> participantScoreDtos = participants.stream()
            .map(participant -> ParticipantScoreDto.from(participant, scoreCalculator))
            .toList();
        outputView.printScore(participantScoreDtos);
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
        outputView.printResult(resultDtos);
    }

    private List<CardDto> cardsToDtos(List<Card> cards) {
        return cards.stream().map(CardDto::from).toList();
    }

    private List<ParticipantInitialDealDto> participantsToDots(Participants participants) {
        return participants.stream().map(ParticipantInitialDealDto::from).toList();
    }
}
