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
	public void BacktrackOld() {
		if (!backtrackInserted.isEmpty()) {
			Character type = backtrackType.removeLast();
			if (type.equals('s')) {
				T inserted = backtrackInserted.peekLast();
				while (backtrackInserted.peekLast().equals(inserted)) {
					MergeOld(backtrackNodes.removeLast(), backtrackMidIndex.removeLast(), backtrackInserted.removeLast());
					backtrackType.removeLast();
				}
			} else {
				Node<T> leaf = backtrackNodes.removeLast();
				T valueToRemove = backtrackInserted.removeLast();
				leaf.removeKey(valueToRemove); //delete the key from the leaf
			}
		}
	}

	public void Backtrack() {
		if (!backtrackInserted.isEmpty()) {
			Character type = backtrackType.peekLast();
			if (type.equals('s')) {
				T inserted = backtrackInserted.peekLast();
				boolean removedAlready=false;
				while (backtrackInserted.peekLast().equals(inserted)) {
					Merge(backtrackParent.removeLast(), backtrackNodes.removeLast(), backtrackMidIndex.removeLast(), backtrackInserted.removeLast(), removedAlready);
					removedAlready=true;
					backtrackType.removeLast();
				}
			} else {
				Node<T> leaf = backtrackNodes.removeLast();
				T valueToRemove = backtrackInserted.removeLast();
				leaf.removeKey(valueToRemove); //delete the key from the leaf
				backtrackType.removeLast();
				if(leaf.equals(root)&&leaf.getNumberOfKeys()==0){
					root=null;
				}
			}
		}
	}

	//Change the list returned to a list of integers answering the requirements
	public static List<Integer> BTreeBacktrackingCounterExample() {
		List<Integer> inserts = new LinkedList<>();
		inserts.add(1);
		inserts.add(2);
		inserts.add(3);
		inserts.add(4);
		inserts.add(5);
		inserts.add(6);
		inserts.add(7);
		inserts.add(8);
		inserts.add(9);
		inserts.add(10);
		inserts.add(11);
		inserts.add(12);
		return inserts;
	}

	private void MergeOld(Node<T> parent, int indexOfMid, T inserted) {

		Node<T> merged = parent.getChild(indexOfMid);
		//System.out.println(merged.hashCode());
		boolean isLeaf = merged.isLeaf();
		int insertedInd = merged.indexOf(inserted);
		if (insertedInd != -1) {
			merged.removeKey(insertedInd);
		}
		merged.addKey(parent.getKey(indexOfMid));
		Node<T> right = parent.getChild(indexOfMid + 1);
		insertedInd = right.indexOf(inserted);
		if (insertedInd != -1) {
			right.removeKey(insertedInd);
		}
		for (int i = 0; i < right.getNumberOfKeys(); i++) {
			merged.addKey(right.getKey(i));
			if (!isLeaf) {
				merged.addChild(right.getChild(i));
			}
		}
		//we have more children than keys
		if (!isLeaf) {
			merged.addChild(right.getChild(right.getNumberOfChildren() - 1));
		}
		if (parent.getNumberOfKeys() == 1) {
			//parent=null;
			merged.parent = null;
			this.root = merged;
		} else {
			parent.removeKey(indexOfMid);
			parent.removeChild(indexOfMid + 1);
		}
		System.out.println(merged.hashCode());
		//return copy;
	}

	private void Merge(Node<T> parent, Node<T> previous, int indexOfMid, T inserted, boolean removedAlready) {

		Node<T> left = parent.getChild(indexOfMid);
		Node<T> right = parent.getChild(indexOfMid + 1);
		if(!removedAlready) {
			Node<T> toRemove = getNode(inserted); //never null
			toRemove.removeKey(inserted);
		}
		if (!previous.isLeaf()) {
			int previousChilds=previous.getNumberOfChildren();
			for (int i = previousChilds-1; i >=0; i--) {
				previous.removeChild(i);
			}
		}
		for (int i = 0; i < left.getNumberOfChildren(); i++) {
			previous.addChild(left.getChild(i));
		}
		for (int i = 0; i < right.getNumberOfChildren(); i++) {
			previous.addChild(right.getChild(i));
		}

		if (parent.parent == null && parent.getNumberOfKeys()==1) {
			this.root = previous;
			previous.parent = parent.parent;
		}else {
			parent.removeKey(indexOfMid);
			parent.removeChild(left);
			parent.removeChild(right);
			parent.addChild(previous);
		}

	}

	private void RemoveKeyFromLeaf(Node <T> node, T value){

	}
}
