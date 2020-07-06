package _03_链表.sigle单向链表;

public class TestSingleLingkMain {
    public static void main(String[] args) {
        linkListTest();
    }


    public static void linkListTest() {
        SingleLinkList<Integer> list = new SingleLinkList<>();
        list.add(0, 11);
        list.add(1, 22);
        list.add(2, 33);
        list.add(3, 44);

        list.set(0, 55);   // [55, 22, 33, 44]



        list.remove(0); //  [ 22, 33, 44]
        list.remove(2); // [ 22, 33]
        list.remove(list.size() - 1); // 22

        System.out.println(list);
    }
}
