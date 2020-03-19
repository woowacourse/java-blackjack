package blackjack.controller;

import blackjack.controller.dto.request.BettingDto;
import blackjack.controller.dto.request.NamesRequestDto;
import blackjack.controller.dto.response.GamersResultResponseDto;
import blackjack.controller.dto.response.HandResponseDtos;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardFactory;
import blackjack.domain.card.CardSymbol;
import blackjack.domain.card.CardType;
import blackjack.domain.deck.Deck;
import blackjack.domain.gamer.BettingMoney;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.domain.rule.BettingTable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class BlackjackControllerTest {

    private BlackjackController blackjackController;

    @BeforeEach
    void setUp() {
        blackjackController = new BlackjackController();
    }

    @Test
    @DisplayName("namesRequestDto를 받아서 players생성")
    void createPlayers() {
        List<String> names = Arrays.asList("pobi", "jay", "elly");
        NamesRequestDto namesRequestDto = new NamesRequestDto(names);
        Players players = blackjackController.createPlayers(namesRequestDto);
        assertThat(players.getNames()).isEqualTo(names);
    }

    @Test
    @DisplayName("getInitialHand는 딜러와 플레이어의 초기 카드를 반환")
    void getInitializeHand() {
        Deck deck = new Deck(CardFactory.generate());
        Players players = Players.from(Arrays.asList("pobi", "jay", "elly"));
        Dealer dealer = new Dealer();
        blackjackController.initializeHand(dealer, players, deck);

        HandResponseDtos handResponseDtos = blackjackController.getInitialHand(dealer, players);
        assertThat(handResponseDtos.getHandResponseDtos()).size().isEqualTo(4);
    }

    @Test
    @DisplayName("getFinalHand는 딜러와 플레이어의 전체 카드를 반환")
    void getFinalHand() {
        Dealer dealer = new Dealer();
        Players players = Players.from(Arrays.asList("pobi", "jay", "elly"));

        HandResponseDtos handResponseDtos = blackjackController.getFinalHand(dealer, players);
        assertThat(handResponseDtos.getHandResponseDtos()).size().isEqualTo(4);
    }

    @Test
    @DisplayName("bettingTableDto와 players를 받아서 betting table 생성")
    void createBettingTable() {
        List<String> names = Arrays.asList("pobi", "jay", "elly");
        Players players = Players.from(names);
        Map<String, String> betting = names.stream()
                .collect(Collectors.toMap(
                        name -> name,
                        name -> "1000"
                ));
        BettingDto bettingDto = new BettingDto(betting);

        assertThat(blackjackController.createBettingTable(players, bettingDto)).isNotNull()
                .isInstanceOf(BettingTable.class);
    }

    @Test
    void gamersResultResponse() {
        Dealer dealer = new Dealer();
        Map<Player, BettingMoney> bettingMoneyMap = new HashMap<>();
        Player pobi = new Player("pobi");
        BettingMoney tenThousand = BettingMoney.from(10000);
        bettingMoneyMap.put(pobi, tenThousand);
        Player jay = new Player("jay");
        BettingMoney twentyThousand = BettingMoney.from(20000);
        bettingMoneyMap.put(jay, twentyThousand);
        Player elly = new Player("elly");
        BettingMoney thirtyThousand = BettingMoney.from(30000);
        bettingMoneyMap.put(elly, thirtyThousand);
        Players players = new Players(Arrays.asList(pobi, jay, elly));
        BettingTable bettingTable = new BettingTable(bettingMoneyMap);

        dealer.draw(new Card(CardSymbol.SEVEN, CardType.SPADE));
        dealer.draw(new Card(CardSymbol.KING, CardType.SPADE));

        pobi.draw(new Card(CardSymbol.ACE, CardType.SPADE));
        pobi.draw(new Card(CardSymbol.JACK, CardType.SPADE));

        jay.draw(new Card(CardSymbol.QUEEN, CardType.SPADE));
        jay.draw(new Card(CardSymbol.KING, CardType.HEART));

        elly.draw(new Card(CardSymbol.SIX, CardType.DIAMOND));
        elly.draw(new Card(CardSymbol.KING, CardType.DIAMOND));

        GamersResultResponseDto gamersResultResponseDto = blackjackController.getGamersResultResponse(dealer, players, bettingTable);
        assertThat(gamersResultResponseDto.getNameMoneyTable())
                .containsEntry("pobi", 15000)
                .containsEntry("jay", 20000)
                .containsEntry("elly", -30000);
    }
}