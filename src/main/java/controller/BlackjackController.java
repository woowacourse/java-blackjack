package controller;

import domain.card.Card;
import domain.card.CardHand;
import domain.card.CardPack;
import domain.game.Gamblers;
import domain.game.Winning;
import domain.game.WinningCounts;
import domain.participant.Dealer;
import domain.participant.Player;
import dto.ParticipantResultResponse;
import dto.ParticipantsCardsResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void play() {
        Dealer dealer = new Dealer(new CardHand());
        List<Player> players = inputView.getPlayerNames()
            .stream()
            .map(name -> new Player(name, new CardHand()))
            .toList();
        Gamblers gamblers = new Gamblers(dealer, players);
        CardPack cardPack = new CardPack(Card.allCards());
        cardPack.shuffle();
        gamblers.distributeSetUpCards(cardPack);
        ParticipantsCardsResponse participantsCardsResponse = createSetUpCardsDTO(dealer, players);
        outputView.printSetUpCardDeck(participantsCardsResponse);

        gamblers.distributeExtraCards(cardPack, new ViewExtraCardsInteract(inputView, outputView));

        List<ParticipantResultResponse> participantResultResponse = createFinalResultDTOs(dealer,
            players);
        outputView.printFinalCardDeck(participantResultResponse);

        WinningCounts winningCounts = gamblers.evaluateDealerWinnings();
        Map<Player, Winning> playerWinnings = gamblers.evaluatePlayerWinnings();
        outputView.printGameResult(winningCounts, playerWinnings);
    }

    public ParticipantsCardsResponse createSetUpCardsDTO(Dealer dealer, List<Player> players) {
        Card dealerOpenCard = dealer.getOpenCard();

        Map<String, List<Card>> cards = new LinkedHashMap<>();
        players.forEach(p -> cards.put(p.getName(), p.getCards()));

        return new ParticipantsCardsResponse(dealerOpenCard, cards);
    }

    public List<ParticipantResultResponse> createFinalResultDTOs(Dealer dealer,
        List<Player> players) {
        List<ParticipantResultResponse> participantResultRespons = new ArrayList<>();
        ParticipantResultResponse participantResultResponse = new ParticipantResultResponse("딜러",
            dealer.getCards(), dealer.calculateScore());
        participantResultRespons.add(participantResultResponse);
        for (Player player : players) {
            ParticipantResultResponse participantResultResponse1 = new ParticipantResultResponse(
                player.getName(), player.getCards(), player.calculateScore());
            participantResultRespons.add(participantResultResponse1);
        }

        return participantResultRespons;
    }
}
