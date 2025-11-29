package deque;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

public class MaxArrayDequeTest {

    private static class IntComparator implements Comparator<Integer>{
        @Override
        public int compare(Integer a,Integer b){
            return a-b;
        }
    }

    private static class StringLengthComparator implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            return s1.length() - s2.length();  // 按长度比较
        }
    }

    private static class ReverseIntComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer a, Integer b) {
            return b - a;  // 逆序
        }
    }

    @Test
    public void testMaxWithIntegers() {
        Comparator<Integer> comp = new IntComparator();
        MaxArrayDeque<Integer> deque = new MaxArrayDeque<>(comp);

        deque.addLast(5);
        deque.addLast(10);
        deque.addLast(3);
        deque.addLast(8);

        assertEquals(Integer.valueOf(10),deque.max());
    }

    @Test
    public void testMaxWithStringsByLength() {
        Comparator<String> comp = new StringLengthComparator();
        MaxArrayDeque<String> deque = new MaxArrayDeque<>(comp);

        deque.addLast("a");
        deque.addLast("apple");
        deque.addLast("banana");
        deque.addLast("hi");

        assertEquals("最长的字符串应该是'banana'", "banana", deque.max());
    }

    @Test
    public void testMaxWithCustomComparator() {
        Comparator<Integer> natural = new IntComparator();
        MaxArrayDeque<Integer> deque = new MaxArrayDeque<>(natural);

        deque.addLast(5);
        deque.addLast(10);
        deque.addLast(3);

        // 使用不同的比较器
        Comparator<Integer> reverse = new ReverseIntComparator();
        assertEquals("使用逆序比较器的最小值应该是3", Integer.valueOf(3), deque.max(reverse));
    }

    @Test
    public void testMaxEmptyDeque() {
        Comparator<Integer> comp = new IntComparator();
        MaxArrayDeque<Integer> deque = new MaxArrayDeque<>(comp);

        assertNull("空队列的max应该返回null", deque.max());
    }

    @Test
    public void testMaxSingleElement() {
        Comparator<String> comp = new StringLengthComparator();
        MaxArrayDeque<String> deque = new MaxArrayDeque<>(comp);

        deque.addLast("single");
        assertEquals("单个元素的max应该是它自己", "single", deque.max());
    }

    @Test
    public void testInheritedMethods() {
        // 测试MaxArrayDeque继承了ArrayDeque的所有方法
        Comparator<Integer> comp = new IntComparator();
        MaxArrayDeque<Integer> deque = new MaxArrayDeque<>(comp);

        deque.addFirst(1);
        deque.addLast(2);

        assertEquals("应该能正常使用addFirst", Integer.valueOf(1), deque.removeFirst());
        assertEquals("应该能正常使用addLast", Integer.valueOf(2), deque.removeLast());
        assertTrue("应该能正常使用isEmpty", deque.isEmpty());
    }
}
