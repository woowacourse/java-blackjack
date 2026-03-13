package service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.Deck;
import domain.Game;
import domain.Players;

class BlackjackServiceTest {
    private BlackjackService blackjackService;
    private Deck deck;

    @BeforeEach
    void setUp() {
        BlackjackService blackjackService = new BlackjackService();
        this.blackjackService = blackjackService;
        this.deck = blackjackService.generateCards();
    }

    @DisplayName("처음에 카드 2장씩 베부된다.")
    @Test
    void 초기_카드_2장_배부_테스트() {
        Players players = blackjackService.createPlayers(List.of("요크", "아티"), deck);
        Game game = blackjackService.createGame(deck,players);

        assertThat(game.getPlayers().getPlayer(0).getCards().size()).isEqualTo(2);
    }

//
//    @DisplayName("처음에 카드 2장씩 베부된다.")
//    @Test
//    void 초기_카드_2장_배_테스트() {
//        List<CardContentDto> cardContentDtos = blackjackService.getCardContentDtos(game);
//        assertThat(players.getPlayer(0).getCards().size()).isEqualTo(2);
//    }
//
//    @DisplayName("처음에 카드 2장씩 베부된다.")
//    @Test
//    void 초기_카드_2장_배_테d스트() {
//        List<FinalCardDto> finalCardDtos = blackjackService.getFinalCardDtos(game);
//        assertThat(players.getPlayer(0).getCards().size()).isEqualTo(2);
//    }
//
//    @DisplayName("처음에 카드 2장씩 베부된다.")
//    @Test
//    void 초기_카드_2장_배_테d스트() {
//        MatchResultDto matchResultDto = blackjackService.getPlayerResultDto(game);
//        assertThat(players.getPlayer(0).getCards().size()).isEqualTo(2);
//    }
//
//    @DisplayName("처음에 카드 2장씩 베부된다.")
//    @Test
//    void 초기_카드_2장_배_테d스트() {
//        BettingResultDto bettingResultDto = blackjackService.getBettingScoreDto(game);
//        assertThat(players.getPlayer(0).getCards().size()).isEqualTo(2);
//    }
//
//    @DisplayName("딜러가 받은 카드의 점수 합이 16이하이면 추가로 받는다.")
//    @Test
//    void 딜러_카드_추가_배부_필요_정상_테스트() {
//        Player dealer = blackjackService.createDealer(deck);
//
//        assertThat(dealer.needAdditionalCard()).isEqualTo(true);
//    }
//
//    @DisplayName("딜러가 받은 카드의 점수 합이 17 이상이면 추가로 받지 않는다.")
//    @Test
//    void 딜러_카드_추가_배부_불필요_정상_테스트() {
//        Dealer dealer = createDealerFromCards(List.of(
//                new Card(CardShape.HEART, CardRank.TEN),
//                new Card(CardShape.HEART, CardRank.SEVEN)
//        ));
//
//        assertThat(dealer.needAdditionalCard()).isEqualTo(false);
//    }
//
//    private Dealer createDealerFromCards(List<Card> cards) {
//        Dealer dealer = new Dealer();
//        for (Card card : cards) {
//            dealer.add(card);
//        }
//        return dealer;
//    }


}