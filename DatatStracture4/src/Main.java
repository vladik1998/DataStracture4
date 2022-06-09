import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[]args){
        BacktrackingBTree<Integer> bTree=new BacktrackingBTree<>(3);
        for (Integer i: BacktrackingBTree.BTreeBacktrackingCounterExample()) {
            bTree.insert(i);
        }
        System.out.println(bTree);

        bTree.Backtrack();
        System.out.println(bTree);
        bTree.Backtrack();
        System.out.println(bTree);
        bTree.Backtrack();
        System.out.println(bTree);
        bTree.Backtrack();
        System.out.println(bTree);
        bTree.Backtrack();
        System.out.println(bTree);
        bTree.Backtrack();
        System.out.println(bTree);
        bTree.Backtrack();
        System.out.println(bTree);
//        Deque<Integer> de=new ArrayDeque<>();
////        de.add(1);
////        de.add(2);
////        de.add(3);
////        System.out.println(de.removeLast());
////        System.out.println(de);
//        System.out.println(de.peek());
    }
}
