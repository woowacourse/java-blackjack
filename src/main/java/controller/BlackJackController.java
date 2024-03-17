package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import model.BlackJack;
import model.card.CardDeck;
import model.card.CardSize;
import model.card.Cards;
import model.player.Dealer;
import model.player.Name;
import model.player.Participant;
import model.player.Participants;
import view.InputView;
import view.OutputView;
import view.dto.ParticipantNamesDto;
import view.dto.UserCardDto;
import view.dto.UserProfitDto;
import view.dto.UserResultDto;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void startGame() {
        BlackJack blackJack = createBlackJack(inputView.askParticipantNames());
        outputView.printPlayerNames(new ParticipantNamesDto(blackJack.findParticipantsName()));
        outputView.printPlayerCards(mapToUserCardDtos(blackJack));
        offerMoreCards(blackJack);
        outputView.printBlackJackScores(mapToUserResultDtos(blackJack));
        outputView.printResults(mapToUserProfitDtos(blackJack));
    }

    private List<UserCardDto> mapToUserCardDtos(BlackJack blackJack) {
        return Stream
                .concat(mapToUserCardDto(blackJack.mapToDealerNameAndCards()).stream(),
                        mapToUserCardDto(blackJack.mapToUsersNameAndCards()).stream())
                .toList();
    }

    private List<UserCardDto> mapToUserCardDto(Map<Name, Cards> userCardDto) {
        return userCardDto.entrySet().stream()
                .map(userResult -> new UserCardDto(userResult.getKey(), userResult.getValue()))
                .toList();
    }

    private List<UserProfitDto> mapToUserProfitDtos(BlackJack blackJack) {
        List<UserProfitDto> userProfitDtos = mapToUserProfitDto(blackJack.getParticipantProfits());
        userProfitDtos.add(0, new UserProfitDto("딜러", blackJack.getDealerProfit()));
        return userProfitDtos;
    }

    private List<UserProfitDto> mapToUserProfitDto(Map<Participant, Double> participantProfits) {
        return new ArrayList<>(participantProfits.entrySet().stream()
                .map(participantProfit -> new UserProfitDto(participantProfit.getKey(), participantProfit.getValue()))
                .toList());
    }

    private List<UserResultDto> mapToUserResultDtos(BlackJack blackJack) {
        return Stream
                .concat(mapToUserDto(blackJack.mapToDealerNameAndCards()).stream(),
                        mapToUserDto(blackJack.mapToUsersNameAndCards()).stream())
                .toList();
    }

    private List<UserResultDto> mapToUserDto(Map<Name, Cards> userResults) {
        return userResults.entrySet().stream()
                .map(userResult -> new UserResultDto(userResult.getKey(), userResult.getValue()))
                .toList();
    }

    private BlackJack createBlackJack(List<String> names) {
        CardDeck cardDeck = new CardDeck(CardDeck.createCards());
        Participants participants = createParticipants(names, cardDeck);
        Dealer dealer = new Dealer(cardDeck, () -> cardDeck.selectRandomCards(CardSize.TWO));
        return new BlackJack(participants, dealer);
    }

    private Participants createParticipants(List<String> names, CardDeck cardDeck) {
        return new Participants(new ArrayList<>(names.stream()
                .map(name -> new Participant(new Name(name), cardDeck.selectRandomCards(CardSize.TWO),
                        inputView.askBettingAmount(name))).toList()));
    }

    private void offerMoreCards(BlackJack blackJack) {
        blackJack.decideParticipantsPlay(inputView::isOneMoreCard, outputView::printPlayerCardMessage);
        blackJack.decideDealerPlay(outputView::printDealerAddCard);
    }
}
