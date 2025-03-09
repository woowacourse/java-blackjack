package controller;

import domain.card.Card;
import domain.card.CardPack;
import domain.participant.Dealer;
import dto.ParticipantResultResponse;
import domain.game.Gamblers;
import domain.game.GameResult;
import domain.participant.Player;
import dto.ParticipantsCardsResponse;
import domain.game.TakeMoreCardSelector;
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
        Dealer dealer = new Dealer();
        List<Player> players = inputView.getPlayers();
        Gamblers gamblers = new Gamblers(dealer, players);
        CardPack cardPack = new CardPack();
        gamblers.distributeSetUpCards(cardPack);
        ParticipantsCardsResponse participantsCardsResponse = createSetUpCardsDTO(dealer, players);
        outputView.printSetUpCardDeck(participantsCardsResponse);

        gamblers.distributeExtraCards(cardPack, new TakeMoreCardViewSelector());

        List<ParticipantResultResponse> participantResultRespons = createFinalResultDTOs(dealer, players);
        outputView.printFinalCardDeck(participantResultRespons);

        GameResult gameResult = gamblers.evaluateFinalScore();
        outputView.printGameResult(gameResult);
    }

    public ParticipantsCardsResponse createSetUpCardsDTO(Dealer dealer, List<Player> players) {
        Card dealerOpenCard = dealer.getOpenCard();

        Map<String, List<Card>> cards = new LinkedHashMap<>();
        players.forEach(p -> cards.put(p.getName(), p.getCards()));

        return new ParticipantsCardsResponse(dealerOpenCard, cards);
    }

    public List<ParticipantResultResponse> createFinalResultDTOs(Dealer dealer, List<Player> players) {
        List<ParticipantResultResponse> participantResultRespons = new ArrayList<>();
        ParticipantResultResponse participantResultResponse = new ParticipantResultResponse("딜러", dealer.getCards(), dealer.calculateScore());
        participantResultRespons.add(participantResultResponse);
        for(Player player : players) {
            ParticipantResultResponse participantResultResponse1 = new ParticipantResultResponse(player.getName(), player.getCards(), player.calculateScore());
            participantResultRespons.add(participantResultResponse1);
        }

        return participantResultRespons;
    }

    private class TakeMoreCardViewSelector implements TakeMoreCardSelector {

        @Override
        public boolean isYes(String name) {
            return inputView.getYesOrNo(name);
        }

        @Override
        public void takenResult(String name, List<Card> cards) {
            outputView.printTakenMoreCards(name, cards);
        }

        @Override
        public void dealerTakenResult() {
            outputView.printDealerTake();
        }
    }
}
