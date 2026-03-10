package controller;

import domain.card.Deck;
import domain.card.DeckMaker;
import domain.card.Hand;
import domain.hitStrategy.CasinoDealerHitStrategy;
import domain.participants.Dealer;
import domain.participants.Player;
import domain.score.Result;
import domain.state.State;
import dto.DealerDrawDto;
import dto.NamesDto;
import dto.PlayerCardsDto;
import dto.PlayersCardsDto;
import dto.StatisticsDto;
import java.util.ArrayList;
import java.util.List;
import util.Parser;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start(final DeckMaker deckMaker) {
        Deck deck = Deck.createFromDeckMaker(deckMaker);
        List<String> playerNames = Parser.parse(inputView.readPlayerName());

        Dealer dealer = Dealer.createDefaultStrategy(Hand.createFromDeck(deck));
        List<Player> players = playerNames.stream()
                .map(playerName -> Player.createDefaultStrategy(playerName, Hand.createFromDeck(deck)))
                .toList();
        State dealerState = dealer.getStartState();
        List<State> playersState = players.stream().map(Player::getStartState).toList();
        printCards(dealerState, playersState);

        playersState = drawPlayerHandAndPrint(playersState, deck);
        dealerState = drawDealerHandAndPrint(dealerState, deck, playersState);
        printAllStatus(dealerState, playersState);
    }

    private List<State> drawPlayerHandAndPrint(List<State> playersState, Deck deck) {
        List<State> states = new ArrayList<>();
        for (State state : playersState) {
            while (!state.isFinished()) {
                state = state.drawCard(deck, inputView.readNeedToHit(state.getParticipantName()));
                outputView.showCards(PlayerCardsDto.fromEntity(state));
            }
            states.add(state);
        }
        return states;
    }

    private void printAllStatus(State dealerState, List<State> playersState) {
        outputView.showCardsAndScore(PlayerCardsDto.fromEntity(dealerState));
        playersState.forEach(player -> outputView.showCardsAndScore(PlayerCardsDto.fromEntity(player)));
        outputView.showResultStatistics(getStatisticsDtos(playersState, dealerState), dealerState.getParticipantName());
    }

    private void printCards(State dealerState, List<State> playersState) {
        outputView.drawCard(NamesDto.fromEntity(dealerState, playersState));
        outputView.showOnlyOneCard(PlayerCardsDto.fromEntity(dealerState));
        outputView.showPlayersCards(PlayersCardsDto.fromEntities(playersState));
    }

    private List<StatisticsDto> getStatisticsDtos(List<State> playersState, State dealerState) {
        List<StatisticsDto> statisticsDtos = new ArrayList<>();
        for (State playerState : playersState) {
            String playerName = playerState.getParticipantName();
            Result result = Result.getResult(dealerState, playerState);
            StatisticsDto statisticsDto = new StatisticsDto(playerName, result.getDisplayName());
            statisticsDtos.add(statisticsDto);
        }

        return statisticsDtos;
    }

    private State drawDealerHandAndPrint(State dealerState, Deck deck, List<State> playersState) {
        State state = dealerState;
        while (!state.isFinished()) {
            state = dealerState.drawCard(deck, true);
            outputView.drawDealer(
                    new DealerDrawDto(dealerState.getParticipantName(), CasinoDealerHitStrategy.BOUNDARY));
            outputView.showCards(PlayerCardsDto.fromEntity(dealerState));
        }
        return state;
    }
}
