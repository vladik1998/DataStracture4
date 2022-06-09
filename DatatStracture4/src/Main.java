import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[]args){
        BacktrackingBTree<Integer> bTree=new BacktrackingBTree<>(3);
        for (Integer i: BacktrackingBTree.BTreeBacktrackingCounterExample()) {
            bTree.insert2(i);
        }
        System.out.println(bTree);

        BacktrackingBTree<Integer> bTree2=new BacktrackingBTree<>(2);
        for (int i = 1; i < 23; i++) {
            bTree2.insert2(i);
        }
        System.out.println(bTree2);
        bTree2.Backtrack2();
        System.out.println(bTree2);


//        for (int i=1; i<13; i++){
//            System.out.println("BackTrack "+i);
//            bTree.Backtrack2();
//            System.out.println(bTree);
//        }


//        bTree.Backtrack();
//        System.out.println(bTree);
//        bTree.Backtrack();
//        System.out.println(bTree);
//        bTree.Backtrack();
//        System.out.println(bTree);
//        bTree.Backtrack();
//        System.out.println(bTree);
//        bTree.Backtrack();
//        System.out.println(bTree);
//        Deque<Integer> de=new ArrayDeque<>();
////        de.add(1);
////        de.add(2);
////        de.add(3);
////        System.out.println(de.removeLast());
////        System.out.println(de);
//        System.out.println(de.peek());
    }
}
