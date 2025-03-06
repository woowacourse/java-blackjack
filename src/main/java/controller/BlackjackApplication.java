package controller;

import domain.BlackjackReferee;
import domain.CardGiver;
import domain.CardRandomGenerator;
import domain.GameResult;
import domain.GameResultStatus;
import domain.GivenCards;
import domain.Participant;
import domain.ParticipantRepository;
import java.util.List;
import java.util.Map;
import view.AnswerType;
import view.InputView;
import view.OutputView;

public class BlackjackApplication {

    private final InputView inputView;
    private final OutputView outputView;
    private final ParticipantRepository participantRepository;
    private final CardGiver cardGiver = new CardGiver(new CardRandomGenerator(), GivenCards.createEmpty());

    public BlackjackApplication(InputView inputView, OutputView outputView,
                                ParticipantRepository participantRepository) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.participantRepository = participantRepository;
    }

    public void execute() {
        initializeGame();

        askForAdditionalCard();

        decideAdditionalCardForDealer();

        calculateResult();
    }

    private void askForAdditionalCard() {
        List<Participant> players = participantRepository.getAllPlayer();
        players.forEach(player -> {
            while (true) {
                AnswerType answerType = inputView.requestAdditionalCard(player);
                if(answerType.isNo()) {
                    outputView.printCurrentCard(player);
                    break;
                }
                player.cards().add(cardGiver.giveOne());
                outputView.printCurrentCard(player);
                boolean isOver = player.cards().isEqualAndMoreMaxSum();
                if(isOver) {
                    System.out.println("21 이상, 너 더 못받음");
                    break;
                }
            }
        });
    }

    private void calculateResult() {
        Participant dealer = participantRepository.getDealer();
        List<Participant> participants = participantRepository.getAllPlayer();

        outputView.printCardsResult(dealer, participants);
        BlackjackReferee blackjackReferee = new BlackjackReferee();
        GameResult gameResult = blackjackReferee.judge(dealer, participants);
        outputView.printGameResults(gameResult);
    }

    private void decideAdditionalCardForDealer() {
        Participant dealer = participantRepository.getDealer();
        if(dealer.cards().isUnderDrawLimit()) {
            dealer.cards().add(cardGiver.giveOne());
            outputView.printDealerDraw(dealer.name());
            return;
        }
        outputView.printDealerNoDraw(dealer.name());
    }

    private void initializeGame() {
        List<String> playerNames = inputView.requestPlayerNames();

        List<Participant> players = playerNames.stream()
                .map(playerName -> Participant.createPlayer(playerName, cardGiver.giveDefault()))
                .toList();
        participantRepository.addAll(players);

        Participant dealer = participantRepository.getDealer();
        dealer.addCards(cardGiver.giveDefault());

        outputView.printInitialCards(dealer, participantRepository.getAllPlayer());
    }
}
