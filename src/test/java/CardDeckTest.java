import domain.Card;
import domain.CardDeck;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CardDeckTest {

    @Test
    void 생성한_카드덱의_갯수가_중복되지_않는_52개이다() {
        //given: 생성된 카드덱을 가져오기만?
        CardDeck cardDeck = new CardDeck();
        //when: 카드덱 개수 파악
        Integer size = cardDeck.getDeckSize();
        //then: 개수가 52개인지 확인
        assertThat(size).isEqualTo(52);
    }

    @Test
    void 뽑은_카드가_올바른_카드이다(){
        List<String> rank=List.of("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K");
        List<String> pattern=List.of("스페이드", "다이아몬드", "하트", "클로버");
        //given: 카드덱 준비
        CardDeck cardDeck=new CardDeck();

        //when: 카드 뽑기
        Card popCard=cardDeck.drawCard();

        //then: 뽑은 카드의 Rank와 Pattern이 각각 Rank 클래스와 Pattern 클래스에 포함되는지 확인 (목업 사용)
        assertThat(rank).contains(popCard.getCardCode());
        assertThat(pattern).contains(popCard.getCardShape());

        System.out.println(popCard.getCardCode());
        System.out.println(popCard.getCardShape());
    }
}
