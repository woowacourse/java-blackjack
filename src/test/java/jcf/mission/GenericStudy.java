package jcf.mission;

import org.junit.jupiter.api.Test;

/**
 * @author 우가
 * @version 1.0.0
 * @Created by 우가 on 2023/03/08
 */
public class GenericStudy {

    @Test
    void test1() {
        SimpleList<Integer> values = new SimpleArrayList<Integer>();
        values.add(1);
        values.add(2);

        Integer first = values.get(0);
        Integer second = values.get(1);
    }

    @Test
    void test2() {
//        final String[] arrays = {"first", "second"};
//
//        final SimpleList<String> values = SimpleList.<String>fromArrayToList(values);
    }
}
