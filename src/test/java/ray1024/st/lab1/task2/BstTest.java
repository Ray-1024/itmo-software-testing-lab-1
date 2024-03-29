package ray1024.st.lab1.task2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BstTest {

    @Test
    void emptyBst() {
        Bst bst = new Bst();
        assertEquals(bst.getNodes(), new ArrayList<Bst.Node>());
    }

    private static Stream<List<Integer>> nums() {
        return Stream.of(
                List.of(5, 3, 5, 7, 2, 1, 4, 5, 6),
                List.of(2, 6, 3, 32, 4, 2, 32, 34, 45, 5564),
                List.of(435, 234, 34, 34, 56, 3, 212, 1, 3431, 2),
                List.of(45, 21, 21, 342, 656, 878, 21, 21),
                List.of(9),
                List.of(0, 0, 0, 0, 0, 0),
                List.of(-1, -2, -3, -4, -5)
        );
    }

    @ParameterizedTest
    @MethodSource("nums")
    void insert(List<Integer> numbers) {
        Bst bst = new Bst();
        for (int i = 0; i < numbers.size(); ++i) {
            bst.insert(numbers.get(i));
            assertEquals(numbers.stream().limit(i + 1).sorted().toList(),
                    bst.getNodes().stream().mapToInt(node -> node.value).boxed().toList());
        }
    }

    @ParameterizedTest
    @MethodSource("nums")
    void findWithoutDelete(List<Integer> numbers) {
        Bst bst = new Bst();
        numbers.forEach(i -> {
            bst.insert(i);
            assertTrue(bst.find(i));
        });
    }

    @ParameterizedTest
    @MethodSource("nums")
    void findWithDeleteAfter(List<Integer> numbers) {
        Bst bst = new Bst();
        numbers.forEach(bst::insert);
        numbers.forEach(i -> {
            int oldSize = bst.getNodes().size();
            assertTrue(bst.find(i));
            bst.delete(i);
            int newSize = bst.getNodes().size();
            assertEquals(oldSize - 1, newSize);
        });
    }

    @ParameterizedTest
    @MethodSource("nums")
    void delete(List<Integer> numbers) {
        Bst bst = new Bst();
        numbers.forEach(bst::insert);
        numbers.forEach(i -> {
            int oldSize = bst.getNodes().size();
            bst.delete(i);
            int newSize = bst.getNodes().size();
            assertEquals(oldSize - 1, newSize);
        });
    }

    @ParameterizedTest
    @MethodSource("nums")
    void dots(List<Integer> numbers) {
        Bst bst = new Bst();
        numbers.forEach(bst::insert);
        assertEquals(bst.getNodes().stream().map(node -> node.value).toList(), numbers.stream().sorted().toList());
    }
}

