import java.util.LinkedList;

public class Palindrome{

	public Deque<Character> wordToDeque(String word){
		LinkedListDeque<Character> deque = new LinkedListDeque<Character>();
		for (int i = 0; i < word.length(); i++){
			deque.addLast(word.charAt(i));
		}
		return deque;
	}

	public boolean isPalindrome(String word){
		Deque<Character> deque = wordToDeque(word.toLowerCase());
		int dequeSize = deque.size();
		if (deque.isEmpty() || deque.size() == 1){
			return true;
		} else {
			StringBuilder reverse = new StringBuilder();
			for (int i = 0; i < dequeSize; i++){
				reverse.append(deque.removeLast());
			}
			return reverse.toString().equals(word.toLowerCase());

			// a more complicated method using get() from Deque
//			int numHalf = (int) deque.size()/2;
//			for (int i = 0; i < numHalf; i++){
//				if(!deque.get(i).equals(deque.get(dequeSize - 1 - i))){
//					return false;
//				}
//			}
//			return true;
		}
	}

	public boolean isPalindrome(String word, CharacterComparator cc){
		// fixed LinkedListDeque bugs
		Deque<Character> deque = wordToDeque(word);
		while (deque.size() > 1) {
			Character first = deque.removeFirst();
			Character last = deque.removeLast();
			if (!cc.equalChars(first, last)){
				return false;
			}
		}
		return true;
	}

}