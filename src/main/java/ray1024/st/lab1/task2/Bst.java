package ray1024.st.lab1.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Bst {

    private Node root = null;

    private static Node _insert(Node current, int value) {
        if (Objects.isNull(current)) return new Node(value);
        else {
            if (value <= current.value) current.l = _insert(current.l, value);
            else current.r = _insert(current.r, value);
            return current;
        }
    }

    public void insert(int value) {
        root = _insert(root, value);
    }

    private static Node _findMax(Node current) {
        return Objects.nonNull(current) && Objects.nonNull(current.r) ? _findMax(current.r) : current;
    }

    private static Node _delete(Node current, int value) {
        if (Objects.isNull(current)) return null;
        else {
            if (value == current.value) {
                if (Objects.nonNull(current.l) && Objects.nonNull(current.r)) {
                    current.value = _findMax(current.l).value;
                    current.l = _delete(current.l, current.value);
                } else if (Objects.nonNull(current.l)) {
                    current = current.l;
                } else if (Objects.nonNull(current.r)) {
                    current = current.r;
                } else {
                    current = null;
                }
            } else if (value < current.value) current.l = _delete(current.l, value);
            else current.r = _delete(current.r, value);
            return current;
        }
    }

    public void delete(int value) {
        root = _delete(root, value);
    }

    private static Node _find(Node current, int value) {
        if (Objects.isNull(current)) return null;
        else {
            if (value == current.value) return current;
            else if (value < current.value) return _find(current.l, value);
            else return _find(current.r, value);
        }
    }


    public boolean find(int value) {
        return Objects.nonNull(_find(root, value));
    }

    private static void _print(Node current, List<Node> nodes) {
        if (Objects.nonNull(current)) {
            _print(current.l, nodes);
            nodes.add(current);
            _print(current.r, nodes);
        }
    }

    public List<Node> getNodes() {
        List<Node> nodes = new ArrayList<>();
        _print(root, nodes);
        return nodes;
    }

    public static final class Node {
        int value;
        Node l;
        Node r;

        public Node(int value) {
            this.value = value;
            l = null;
            r = null;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }
}
