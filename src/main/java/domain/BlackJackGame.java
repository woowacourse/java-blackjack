package domain;

import controller.dto.HandStatus;
import controller.dto.JudgeResult;
import domain.constants.CardCommand;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class BlackJackGame {
    // TODO: 딜러와 플레이어가 알 수 있도록 접근제어자를 public으로 변경하였다. 옳은가?
    public static final int BLACKJACK_SCORE = 21;
    private static final int CARD_INITIAL_SIZE = 2;
    private static final int CARD_PICK_SIZE = 1;

    private final Participants participants;
    private final Deck deck;

    public BlackJackGame(final Participants participants, final Deck deck) {
        this.participants = participants;
        this.deck = deck;
    }

    public static BlackJackGame from(final List<String> playerNames) {
        return new BlackJackGame(Participants.from(playerNames), new Deck());
    }

    public List<HandStatus> initialize() {
        List<HandStatus> status = new ArrayList<>();
        for (Participant participant : participants.getParticipants()) {
            participant.pickCard(deck, CARD_INITIAL_SIZE);
            status.add(participant.createHandStatus());
        }
        return status;
    }

    // TODO: 함수형 인터페이스를 Wrapping하기
    public void giveCard(
            final Participant participant,
            final Consumer<HandStatus> action, // 출력
            final Supplier<CardCommand> supplier // 딜러는 아무런 입력도 받지 않고 무조건 HIT
    ) {
        while (participant.canPickCard(supplier.get())) {
            participant.pickCard(deck, CARD_PICK_SIZE);
            HandStatus currentStatus = participant.createHandStatus();
            action.accept(currentStatus);
        }
    }

    public List<HandStatus> createHandStatuses() {
        return participants.getParticipants()
                .stream()
                .map(participant -> participant.createHandStatus())
                .toList();
    }

    public JudgeResult judge() {
        Referee referee = new Referee(participants);
        return referee.judge();
    }

    /*
    public void finish(final OutputView outputView) {
        List<Integer> scores = new ArrayList<>();
        scores.add(participants.dealer().calculateResultScore());
        for (Player player : participants.players()) {
            scores.add(player.calculateResultScore());
        }
        outputView.printResult(getCurrentHandStatus(), scores);
        outputView.printGameResult(getGameResult());
    }*/

    /*
    public void giveCardToPlayer(final String name, final OutputView outputView, final InputView inputView) {
        Player player = getPlayer(name);
        HandStatus currentHand = new HandStatus(player.getName(), player.getHand());
        CardCommand command = inputCommand(name, inputView);
        while (HIT.equals(command)) {
            currentHand = createHandStatus(player);
            outputView.printCardStatus(currentHand);
            if (player.cannotDraw()) {
                break;
            }
            command = inputCommand(name, inputView);
        }
        if (STAND.equals(command)) {
            outputView.printCardStatus(currentHand);
        }
    }*/

    /*
    public void giveCardsToDealer(final OutputView outputView) {
        Dealer dealer = participants.dealer();

        int currentScore = dealer.calculateResultScore();
        while (currentScore <= BLACKJACK_SCORE) {
            dealer.pickOneCard(deck);
            outputView.printDealerPickMessage();
            currentScore = dealer.calculateResultScore();
        }
    }*/

    public List<Participant> getParticipants() {
        return participants.getParticipants();
    }
}
