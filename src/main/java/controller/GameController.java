package controller;

import domain.Card;
import domain.Dealer;
import domain.Deck;
import domain.Hand;
import domain.Name;
import domain.Player;
import domain.Players;
import dto.DealerResultDto;
import dto.PlayerDto;
import dto.PlayersDto;
import java.util.ArrayList;
import view.InputView;

import java.util.List;
import view.OutputView;

public class GameController {
    private static final int INITIAL_CARD_COUNT = 2;

    private final Deck deck;

    public GameController() {
        this.deck = Deck.createDeck();
    }

    public void start() {
        // 카드 초기화
        Dealer dealer = initDealer();
        Players players = initPlayers();

        // 카드 출력
        PlayersDto playersDto = PlayersDto.from(players);
        OutputView.printHandOutMessage(playersDto);
        OutputView.printCardStatus(playersDto, DealerResultDto.from(dealer));

        // 플레이어 카드 추가 수령
        addPlayersCard(players);

        // 딜러 게임 진행
        addDealerCards(dealer, players);

        // 카드 결과, 점수 출력
        printCardResults(DealerResultDto.from(dealer), PlayersDto.from(players));

        // 최종 승패 출력
    }

    // 플레이어들 초기화
    private Players initPlayers() {
        List<String> names = InputView.readParticipants();
        List<Player> players = new ArrayList<>();
        for (String name : names) {
            players.add(initPlayer(name));
        }

        return Players.from(players);
    }

    // 플레이어 초기 카드
    private Player initPlayer(String name) {
        return Player.of(Name.from(name), new Hand(initCards()));
    }

    private List<Card> initCards() {
        List<Card> cards = new ArrayList<>();
        for (int count = 0; count < INITIAL_CARD_COUNT; count++) {
            cards.add(deck.draw());
        }
        return cards;
    }


    private Dealer initDealer() {
        return Dealer.from(new Hand(initCards()));
    }

    // 플레이어 전체 카드 추가 TODO 카드 버스트 전까지 계속 받을지 확인
    private void addPlayersCard(Players players) {
        for (Player player : players.getPlayers()) {
            addPlayerCard(player);
        }
    }

    // 각 플레이어별 카드추가
    private void addPlayerCard(Player player) {
        if (InputView.checkAddCard(player.getName())) {
            player.addHandCard(deck.draw());
            OutputView.printPlayerCardStatus(PlayerDto.from(player));
        }
    }

    // 딜러 카드 추가하기 (반복)
    private void addDealerCards(Dealer dealer, Players players) {
        if (dealer.checkThreshold() && !players.isAllBust()) {
            OutputView.printAddDealerCardMessage();
            addDealerCard(dealer);
        }
    }

    // 딜러 카드 추가하기
    private void addDealerCard(Dealer dealer) {
        while (dealer.checkThreshold()) {
            dealer.addHandCard(deck.draw());
        }
    }

    private void printCardResults(DealerResultDto dealerResultDto, PlayersDto playersDto) {
        OutputView.printCardResult(dealerResultDto, playersDto);
    }
}
