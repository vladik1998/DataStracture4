import java.util.LinkedList;
import java.util.List;

public class BacktrackingBTree<T extends Comparable<T>> extends BTree<T> {
	// For clarity only, this is the default ctor created implicitly.
	public BacktrackingBTree() {
		super();
	}

	private T test;
	public BacktrackingBTree(int order) {
		super(order);
	}

	//You are to implement the function Backtrack.
	public void Backtrack(){
		if(!backtrackInserted.isEmpty()){
			Character type=backtrackType.removeLast();
			if(type.equals('s')) {
				T inserted=backtrackInserted.peekLast();
				while (backtrackInserted.peekLast().equals(inserted)) {
					Merge(backtrackNodes.removeLast(), backtrackMidIndex.removeLast(), backtrackInserted.removeLast());
					backtrackType.removeLast();
				}
			}else {
				Node<T> leaf=backtrackNodes.removeLast();
				T valueToRemove=backtrackInserted.removeLast();
				leaf.removeKey(valueToRemove); //delete the key from the leaf
			}
		}
	}
	
	//Change the list returned to a list of integers answering the requirements
	public static List<Integer> BTreeBacktrackingCounterExample(){
		List<Integer> inserts=new LinkedList<>();
		inserts.add(1);
		inserts.add(2);
		inserts.add(3);
		inserts.add(4);
		inserts.add(5);
		inserts.add(6);
//		inserts.add(7);
//		inserts.add(8);
//		inserts.add(9);
//		inserts.add(10);
//		inserts.add(11);
//		inserts.add(12);
		return inserts;
	}

	private void Merge(Node<T> parent,int indexOfMid, T inserted){

		Node<T> merged=parent.getChild(indexOfMid);
		boolean isLeaf=merged.isLeaf();
		int insertedInd=merged.indexOf(inserted);
		if(insertedInd!=-1){
			merged.removeKey(insertedInd);
		}
		merged.addKey(parent.getKey(indexOfMid));
		Node<T> right=parent.getChild(indexOfMid+1);
		insertedInd=right.indexOf(inserted);
		if(insertedInd!=-1){
			right.removeKey(insertedInd);
		}
		for(int i=0; i<right.getNumberOfKeys(); i++){
			merged.addKey(right.getKey(i));
			if(!isLeaf) {
				merged.addChild(right.getChild(i));
			}
		}
		//we have more children than keys
		if(!isLeaf) {
			merged.addChild(right.getChild(right.getNumberOfChildren() - 1));
		}
		if(parent.getNumberOfKeys()==1) {
			//parent=null;
			merged.parent = null;
			this.root=merged;
		}else {
			parent.removeKey(indexOfMid);
			parent.removeChild(indexOfMid+1);
		}
		//return copy;
	}
}
