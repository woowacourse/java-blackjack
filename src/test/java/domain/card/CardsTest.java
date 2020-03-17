package domain.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardsTest {

	private Cards cards;
	private Card card;

	@BeforeEach
	void setUp() {
		cards = new Cards(new ArrayList<>());
		card = new Card(Symbol.ACE, Type.CLOVER);
		cards.add(card);
	}

	@DisplayName("Cards 생성자 정상 동작 테스트")
	@Test
	void create_Cards() {
		assertThat(new Cards(new ArrayList<>())).isInstanceOf(Cards.class);
	}

	@DisplayName("Cards에 카드를 한 장 추가하는 메서드 확인")
	@Test
	void add_Card() {
		Cards expected = new Cards(Collections.singletonList(card));

		assertThat(cards).isEqualTo(expected);
	}

	@DisplayName("Cards의 toList()메서드가 List<Card>를 반환하는지 확인")
	@Test
	void toList_Getter_For_View() {
		List<Card> expected = Collections.singletonList(card);

		assertEquals(expected, cards.toList());
	}

	@DisplayName("Ace한 장일 경우 11점 반환 확인")
	@Test
	void getPoint_When_One_Ace_Card_Return_11() {
		assertThat(cards.calculateScore()).isEqualTo(11);
	}

	@DisplayName("2장의 카드를 가지고 있을 떄 hasInitialSize()가 true반환")
	@Test
	void hasInitialSize_When_Two_Cards_Return_True() {
		cards.add(card);
		assertThat(cards.hasInitialSize()).isTrue();
	}

	@DisplayName("3장의 카드를 가지고 있을 떄 hasInitialSize()가 false반환")
	@Test
	void hasInitialSize_When_Three_Cards_Return_False() {
		cards.add(card);
		cards.add(card);
		assertThat(cards.hasInitialSize()).isFalse();
	}
}
