package controller;

import domain.bet.Betting;
import domain.card.Deck;
import domain.card.DeckMaker;
import domain.hitStrategy.CasinoDealerHitStrategy;
import domain.participants.Dealer;
import domain.participants.Hand;
import domain.state.State;
import dto.DealerDrawDto;
import dto.NamesDto;
import dto.PlayerCardsDto;
import dto.PlayerCreateDto;
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
        State dealerState = Dealer.createDefaultStrategy().getStartState(Hand.createFromDeck(deck));
        List<State> playersState = readPlayerAndCreateState(deck);
        printCards(dealerState, playersState);
        playersState = drawPlayerHandAndPrint(playersState, deck);
        dealerState = drawDealerHandAndPrint(dealerState, deck);
        printAllStatus(dealerState, playersState);
    }

    private List<State> readPlayerAndCreateState(Deck deck) {
        return readPlayersInfo().stream()
                .map(PlayerCreateDto::toDefaultStrategyPlayer)
                .map(player -> player.getStartState(Hand.createFromDeck(deck)))
                .toList();
    }

    private List<PlayerCreateDto> readPlayersInfo() {
        List<String> playerNames = Parser.parse(inputView.readPlayerName());
        List<PlayerCreateDto> playerCreateDtos = new ArrayList<>();
        for (String playerName : playerNames) {
            Integer betting = Parser.toInteger(inputView.readBettingCost(playerName));
            playerCreateDtos.add(new PlayerCreateDto(playerName, new Betting(betting)));
        }

        return playerCreateDtos;
    }

    private List<State> drawPlayerHandAndPrint(List<State> playersState, Deck deck) {
        List<State> states = new ArrayList<>();
        for (State state : playersState) {
            while (!state.isFinished()) {
                state = state.drawCard(deck, inputView.readNeedToHit(state.getParticipantName()));
                outputView.showCards(PlayerCardsDto.fromState(state));
            }
            states.add(state);
        }
        return states;
    }

    private void printAllStatus(State dealerState, List<State> playersState) {
        outputView.showCardsAndScore(PlayerCardsDto.fromState(dealerState));
        playersState.forEach(player -> outputView.showCardsAndScore(PlayerCardsDto.fromState(player)));
        outputView.showResultStatistics(getStatisticsDtos(playersState, dealerState), dealerState.getParticipantName());
    }

    private void printCards(State dealerState, List<State> playersState) {
        outputView.drawCard(NamesDto.fromState(dealerState, playersState));
        outputView.showOnlyOneCard(PlayerCardsDto.fromState(dealerState));
        outputView.showPlayersCards(PlayersCardsDto.fromStates(playersState));
    }

    private List<StatisticsDto> getStatisticsDtos(List<State> playersState, State dealerState) {
        List<StatisticsDto> statisticsDtos = new ArrayList<>();
        for (State playerState : playersState) {
            String playerName = playerState.getParticipantName();
            StatisticsDto statisticsDto = new StatisticsDto(playerName, playerState.getProfit(dealerState));
            statisticsDtos.add(statisticsDto);
        }

        return statisticsDtos;
    }

    private State drawDealerHandAndPrint(State dealerState, Deck deck) {
        State state = dealerState;
        while (!state.isFinished()) {
            state = dealerState.drawCard(deck, true);
            outputView.drawDealer(
                    new DealerDrawDto(dealerState.getParticipantName(), CasinoDealerHitStrategy.BOUNDARY));
            outputView.showCards(PlayerCardsDto.fromState(dealerState));
        }
        return state;
    }
}
