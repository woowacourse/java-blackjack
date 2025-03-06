package controller;

import domain.CardGiver;
import domain.CardRandomGenerator;
import domain.Cards;
import domain.GivenCards;
import domain.Participant;
import domain.ParticipantRepository;
import java.util.List;
import view.InputView;
import view.OutputView;

public class BlackjackApplication {

    private final InputView inputView;
    private final OutputView outputView;
    private final ParticipantRepository participantRepository;

    public BlackjackApplication(InputView inputView, OutputView outputView, ParticipantRepository participantRepository) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.participantRepository = participantRepository;
    }

    public void execute() {
        initializeGame();
    }

    private void initializeGame() {
        List<String> playerNames = inputView.requestPlayerNames();

        CardGiver cardGiver = new CardGiver(new CardRandomGenerator(), GivenCards.createEmpty());

        List<Participant> players = playerNames.stream()
                .map(playerName -> Participant.createPlayer(playerName, cardGiver.giveDefault()))
                .toList();
        participantRepository.addAll(players);

        Participant dealer = participantRepository.getDealer();
        dealer.addCards(cardGiver.giveDefault());

        outputView.printInitialCards(dealer, participantRepository.getAllPlayer());
    }
}
