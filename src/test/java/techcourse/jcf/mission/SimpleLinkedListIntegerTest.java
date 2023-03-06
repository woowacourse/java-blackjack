package techcourse.jcf.mission;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SimpleLinkedListIntegerTest {
    @DisplayName("SimpleLinkedList 생성 테스트")
    @Test
    void SimpleLinkedList_생성_테스트() {
        Assertions.assertDoesNotThrow(() -> {
            new SimpleLinkedList<Integer>();
        });
    }

    @DisplayName("SimpleLinkedList 원소 추가 테스트")
    @Test
    void SimpleLinkedList_원소_추가_테스트() {
        Assertions.assertEquals(true, new SimpleLinkedList<Integer>().add(10));
    }

    @DisplayName("SimpleLinkedList 특정 인덱스에 원소 추가 테스트")
    @Test
    void SimpleLinkedList_특정_인덱스에_원소_추가_테스트() {
        SimpleLinkedList<Integer> linkedList = new SimpleLinkedList<Integer>();
        Assertions.assertDoesNotThrow(() -> {
            for (int i = 0; i < 10; i++) {
                linkedList.add(i, 10);
            }
        });
    }

    @DisplayName("SimpleLinkedList 범위 내부의 원소 설정 테스트")
    @Test
    void SimpleLinkedList_범위_내부의_원소_재설정_테스트() {
        SimpleLinkedList<Integer> linkedList = new SimpleLinkedList<Integer>();
        final Integer beforeValue = 10;
        final Integer setValue = 100;
        linkedList.add(beforeValue);

        Assertions.assertEquals(beforeValue, linkedList.set(0, setValue));
    }

    @DisplayName("SimpleLinkedList 범위를 벗어나는 특정 인덱스의 원소 재설정 실패 테스트")
    @Test
    void SimpleLinkedList_잘못된_범위_인덱스의_원소_설정_실패_테스트() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    new SimpleLinkedList<Integer>().set(1, 10);
                });
    }

    @DisplayName("SimpleLinkedList 범위 내부의 원소 반환 테스트")
    @Test
    void SimpleLinkedList_범위_내부의_원소_반환_테스트() {
        SimpleLinkedList<Integer> linkedList = new SimpleLinkedList<Integer>();
        final Integer beforeValue = 10;
        linkedList.add(beforeValue);

        Assertions.assertEquals(beforeValue, linkedList.get(0));
    }

    @DisplayName("SimpleLinkedList 범위를 벗어나는 특정 인덱스의 원소 반환 실패 테스트")
    @Test
    void SimpleLinkedList_잘못된_범위_인덱스의_원소_반환_실패_테스트() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    new SimpleLinkedList<Integer>().get(1);
                });
    }

    @DisplayName("SimpleLinkedList 범위 내부의 원소 포함 여부 테스트")
    @Test
    void SimpleLinkedList_범위_내부의_원소_포함_여부_테스트() {
        SimpleLinkedList<Integer> linkedList = new SimpleLinkedList<Integer>();
        final Integer beforeValue = 10;
        linkedList.add(beforeValue);

        Assertions.assertEquals(true, linkedList.contains(beforeValue));
    }

    @DisplayName("SimpleLinkedList 범위 내부의 원소 불포함 여부 테스트")
    @Test
    void SimpleLinkedList_범위_내부의_원소_불포함_테스트() {
        SimpleLinkedList<Integer> linkedList = new SimpleLinkedList<Integer>();

        Assertions.assertEquals(false,
                linkedList.contains(10));
    }

    @DisplayName("SimpleLinkedList 내부 원소 위치 반환 테스트")
    @Test
    void SimpleLinkedList_범위_내부의_원소_위치_반환_테스트() {
        SimpleLinkedList<Integer> linkedList = new SimpleLinkedList<Integer>();
        final Integer beforeValue = 10;
        linkedList.add(beforeValue);

        Assertions.assertEquals(0, linkedList.indexOf(beforeValue));
    }

    @DisplayName("SimpleLinkedList 범위 내부에 찾는 원소가 없는 경우 -1 반환 테스트")
    @Test
    void SimpleLinkedList_범위_내부에_원소_없는_경우_위치_테스트() {
        SimpleLinkedList<Integer> linkedList = new SimpleLinkedList<Integer>();

        Assertions.assertEquals(-1, linkedList.indexOf(10));
    }

    @DisplayName("SimpleLinkedList 내부 원소 개수 반환 테스트")
    @Test
    void SimpleLinkedList_범위_내부의_원소_개수_반환_테스트() {
        SimpleLinkedList<Integer> linkedList = new SimpleLinkedList<Integer>();
        final Integer beforeValue = 10;
        linkedList.add(beforeValue);

        Assertions.assertEquals(1, linkedList.size());
    }

    @DisplayName("SimpleLinkedList 범위 내부에 찾는 원소가 없는 경우 -1 반환 테스트")
    @Test
    void SimpleLinkedList_범위_내부에_원소_없는_경우_개수_테스트() {
        SimpleLinkedList<Integer> linkedList = new SimpleLinkedList<Integer>();

        Assertions.assertEquals(0, linkedList.size());
    }

    @DisplayName("SimpleLinkedList 내부 비어있는지 테스트")
    @Test
    void SimpleLinkedList_비어있는지_테스트() {
        SimpleLinkedList<Integer> linkedList = new SimpleLinkedList<Integer>();

        Assertions.assertEquals(true, linkedList.isEmpty());
    }

    @DisplayName("SimpleLinkedList 안 비어있는지 테스트")
    @Test
    void SimpleLinkedList_내부가_비어있지_않은_경우_테스트() {
        SimpleLinkedList<Integer> linkedList = new SimpleLinkedList<Integer>();
        final Integer beforeValue = 10;
        linkedList.add(beforeValue);

        Assertions.assertEquals(false, linkedList.isEmpty());
    }

    @DisplayName("SimpleLinkedList 특정 값으로 삭제 테스트")
    @Test
    void SimpleLinkedList_특정_값_삭제_테스트() {
        SimpleLinkedList<Integer> linkedList = new SimpleLinkedList<Integer>();
        final Integer beforeValue = 10;
        linkedList.add(beforeValue);

        Assertions.assertEquals(true, linkedList.remove(beforeValue));
    }

    @DisplayName("SimpleLinkedList 특정 값 삭제 실패 테스트")
    @Test
    void SimpleLinkedList_특정_값_삭제_실패_테스트() {
        SimpleLinkedList<Integer> linkedList = new SimpleLinkedList<Integer>();
        final Integer beforeValue = 10;
        linkedList.add(beforeValue);

        Assertions.assertEquals(false, linkedList.remove((Integer) 100));
    }

    @DisplayName("SimpleLinkedList 특정 인덱스로 삭제 테스트")
    @Test
    void SimpleLinkedList_특정_인덱스로_삭제_테스트() {
        SimpleLinkedList<Integer> linkedList = new SimpleLinkedList<Integer>();
        final Integer beforeValue = 10;
        linkedList.add(beforeValue);

        Assertions.assertEquals(beforeValue, linkedList.remove(0));
    }

    @DisplayName("SimpleLinkedList 특정 인덱스로 삭제 실패 테스트")
    @Test
    void SimpleLinkedList_특정_인덱스로_삭제_실패_테스트() {
        SimpleLinkedList<Integer> linkedList = new SimpleLinkedList<Integer>();
        final Integer beforeValue = 10;
        linkedList.add(beforeValue);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            linkedList.remove(1);
        });
    }

    @DisplayName("SimpleLinkedList 초기화 테스트")
    @Test
    void SimpleLinkedList_초기화_테스트() {
        SimpleLinkedList<Integer> linkedList = new SimpleLinkedList<Integer>();
        final Integer beforeValue = 10;
        linkedList.add(beforeValue);

        linkedList.clear();

        Assertions.assertEquals(true, linkedList.isEmpty());
        Assertions.assertEquals(0, linkedList.size());
        Assertions.assertEquals(false, linkedList.contains(beforeValue));
    }
}
