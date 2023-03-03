package techcourse.jcf.mission;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SimpleArrayListTest {
    @DisplayName("SimpleArrayList 생성 테스트")
    @Test
    void SimpleArrayList_생성_테스트() {
        Assertions.assertDoesNotThrow(() -> {
            new SimpleArrayList();
        });
    }

    @DisplayName("SimpleArrayList 원소 추가 테스트")
    @Test
    void SimpleArrayList_원소_추가_테스트() {
        Assertions.assertEquals(true, new SimpleArrayList().add("hello"));
    }

    @DisplayName("SimpleArrayList 특정 인덱스에 원소 추가 테스트")
    @Test
    void SimpleArrayList_특정_인덱스에_원소_추가_테스트() {
        Assertions.assertDoesNotThrow(() -> {
            new SimpleArrayList().add(0, "hello");
        });
    }

    @DisplayName("SimpleArrayList 원소 추가 시 자동 크기 증가 테스트")
    @Test
    void SimpleArrayList_자동_크기_증가_테스트() {
        SimpleArrayList arrayList = new SimpleArrayList(1);
        arrayList.add("first");
        arrayList.add("second");

        Assertions.assertEquals(2, arrayList.size());
    }

    @DisplayName("SimpleArrayList 범위를 벗어나는 특정 인덱스에 원소 추가 실패 테스트")
    @Test
    void SimpleArrayList_잘못된_범위의_특정_인덱스에_원소_추가_실패_테스트() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    new SimpleArrayList().add(1, "hello");
                });
    }

    @DisplayName("SimpleArrayList 범위 내부의 원소 설정 테스트")
    @Test
    void SimpleArrayList_범위_내부의_원소_재설정_테스트() {
        SimpleArrayList arrayList = new SimpleArrayList();
        final String beforeValue = "before";
        final String setValue = "after";
        arrayList.add(beforeValue);

        Assertions.assertEquals(beforeValue, arrayList.set(0, setValue));
    }

    @DisplayName("SimpleArrayList 범위를 벗어나는 특정 인덱스의 원소 재설정 실패 테스트")
    @Test
    void SimpleArrayList_잘못된_범위_인덱스의_원소_설정_실패_테스트() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    new SimpleArrayList().set(1, "hello");
                });
    }

    @DisplayName("SimpleArrayList 범위 내부의 원소 반환 테스트")
    @Test
    void SimpleArrayList_범위_내부의_원소_반환_테스트() {
        SimpleArrayList arrayList = new SimpleArrayList();
        final String beforeValue = "before";
        arrayList.add(beforeValue);

        Assertions.assertEquals(beforeValue, arrayList.get(0));
    }

    @DisplayName("SimpleArrayList 범위를 벗어나는 특정 인덱스의 원소 반환 실패 테스트")
    @Test
    void SimpleArrayList_잘못된_범위_인덱스의_원소_반환_실패_테스트() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    new SimpleArrayList().get(1);
                });
    }

    @DisplayName("SimpleArrayList 범위 내부의 원소 포함 여부 테스트")
    @Test
    void SimpleArrayList_범위_내부의_원소_포함_여부_테스트() {
        SimpleArrayList arrayList = new SimpleArrayList();
        final String beforeValue = "before";
        arrayList.add(beforeValue);

        Assertions.assertEquals(true, arrayList.contains(beforeValue));
    }

    @DisplayName("SimpleArrayList 범위 내부의 원소 불포함 여부 테스트")
    @Test
    void SimpleArrayList_범위_내부의_원소_불포함_테스트() {
        SimpleArrayList arrayList = new SimpleArrayList();

        Assertions.assertEquals(false, arrayList.contains("not contain"));
    }

    @DisplayName("SimpleArrayList 내부 원소 위치 반환 테스트")
    @Test
    void SimpleArrayList_범위_내부의_원소_위치_반환_테스트() {
        SimpleArrayList arrayList = new SimpleArrayList();
        final String beforeValue = "before";
        arrayList.add(beforeValue);

        Assertions.assertEquals(0, arrayList.indexOf(beforeValue));
    }

    @DisplayName("SimpleArrayList 범위 내부에 찾는 원소가 없는 경우 -1 반환 테스트")
    @Test
    void SimpleArrayList_범위_내부에_원소_없는_경우_위치_테스트() {
        SimpleArrayList arrayList = new SimpleArrayList();

        Assertions.assertEquals(-1, arrayList.indexOf("not contain"));
    }

    @DisplayName("SimpleArrayList 내부 원소 개수 반환 테스트")
    @Test
    void SimpleArrayList_범위_내부의_원소_개수_반환_테스트() {
        SimpleArrayList arrayList = new SimpleArrayList();
        final String beforeValue = "before";
        arrayList.add(beforeValue);

        Assertions.assertEquals(1, arrayList.size());
    }

    @DisplayName("SimpleArrayList 범위 내부에 찾는 원소가 없는 경우 -1 반환 테스트")
    @Test
    void SimpleArrayList_범위_내부에_원소_없는_경우_개수_테스트() {
        SimpleArrayList arrayList = new SimpleArrayList();

        Assertions.assertEquals(0, arrayList.size());
    }

    @DisplayName("SimpleArrayList 내부 비어있는지 테스트")
    @Test
    void SimpleArrayList_비어있는지_테스트() {
        SimpleArrayList arrayList = new SimpleArrayList();

        Assertions.assertEquals(true, arrayList.isEmpty());
    }

    @DisplayName("SimpleArrayList 안 비어있는지 테스트")
    @Test
    void SimpleArrayList_내부가_비어있지_않은_경우_테스트() {
        SimpleArrayList arrayList = new SimpleArrayList();
        final String beforeValue = "before";
        arrayList.add(beforeValue);

        Assertions.assertEquals(false, arrayList.isEmpty());
    }

    @DisplayName("SimpleArrayList에서 특정 값으로 삭제 테스트")
    @Test
    void SimpleArrayList_특정_값_삭제_테스트() {
        SimpleArrayList arrayList = new SimpleArrayList();
        final String beforeValue = "before";
        arrayList.add(beforeValue);

        Assertions.assertEquals(true, arrayList.remove(beforeValue));
    }

    @DisplayName("SimpleArrayList 특정 값 삭제 실패 테스트")
    @Test
    void SimpleArrayList_특정_값_삭제_실패_테스트() {
        SimpleArrayList arrayList = new SimpleArrayList();
        final String beforeValue = "before";
        arrayList.add(beforeValue);

        Assertions.assertEquals(false, arrayList.remove("not contain"));
    }

    @DisplayName("SimpleArrayList에서 특정 인덱스로 삭제 테스트")
    @Test
    void SimpleArrayList_특정_인덱스로_삭제_테스트() {
        SimpleArrayList arrayList = new SimpleArrayList();
        final String beforeValue = "before";
        arrayList.add(beforeValue);

        Assertions.assertEquals(beforeValue, arrayList.remove(0));
    }

    @DisplayName("SimpleArrayList 특정 값 삭제 실패 테스트")
    @Test
    void SimpleArrayList_특정_인덱스로_삭제_실패_테스트() {
        SimpleArrayList arrayList = new SimpleArrayList();
        final String beforeValue = "before";
        arrayList.add(beforeValue);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            arrayList.remove(1);
        });
    }

    @DisplayName("SimpleArrayList 초기화 테스트")
    @Test
    void SimpleArrayList_초기화_테스트() {
        SimpleArrayList arrayList = new SimpleArrayList();
        final String beforeValue = "before";
        arrayList.add(beforeValue);

        arrayList.clear();

        Assertions.assertEquals(true, arrayList.isEmpty());
        Assertions.assertEquals(0, arrayList.size());
        Assertions.assertEquals(false, arrayList.contains(beforeValue));
    }
}
