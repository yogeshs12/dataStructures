import java.util.ArrayList;
import java.util.List;

class Trie{
	TrieNode root;
	
	public Trie() {
		// TODO Auto-generated constructor stub
		root = new TrieNode();
	}
	
	void addItem(String key){
		char[] arr = key.toCharArray();
		TrieNode node = root;
		if(key.length()==0){
			node.isEnd = true;
		}else{
			for(int j = 0 ; j < arr.length;j++){
				int index = arr[j] - 'a';
				if(node.children[index] == null){
					node.children[index] = new TrieNode();
				}
				node = node.children[index];
			}
			node.isEnd = true;
		}
		System.out.println("Item added");
	}
	
	boolean isFound(String key){
		char[] arr = key.toCharArray();
		TrieNode node = root;
		
		for(int j = 0 ; j < arr.length;j++){
			int index = arr[j] - 'a';
			if(node.children[index] == null){
				return false;
			}
			node = node.children[index];
		}
		
		return true;
	}
	
	List<String> autocomplete(String key){
		char[] arr = key.toCharArray();
		TrieNode node = root;
		List<String> output = new ArrayList<String>();
		
		
		for(int j = 0 ; j < arr.length;j++){
			int index = arr[j] - 'a';
			if(node.children[index] == null){
				return output;
			}
			node = node.children[index];
		}
		
		printRest(output,node,key);
		return output;
		
	}

	private void printRest(List<String> output, TrieNode node, String key) {
		// TODO Auto-generated method stub
		if(node.isEnd){
			output.add(key);
		}
			for(int k = 0 ; k < 26;k++){
				//int index = arr[j] - 'a';
				if(node.children[k] != null){
					char x = (char) (k+97);
					String y = key + String.valueOf(x);
					printRest(output,node.children[k],y);
				}
				
			}
		//}
	}
}

class TrieNode{
	Character data;
	TrieNode[] children = new TrieNode[26];
	boolean isEnd ;
	public TrieNode() {
		// TODO Auto-generated constructor stub
		
		for(int i = 0 ; i < 26; i++){
			this.children[i] = null;
		}
		this.isEnd = false;
	}
}



public class TrieTest {
 
	public static void main(String args[]){
		Trie trie = new Trie();
		String[] s = { "there", "the","thousand", "and","tiger","andman"};
		
		for(String a : s){
			trie.addItem(a);
		}
		
		System.out.println("seach item : 'there' == "+ trie.isFound("their") );
		
		System.out.println("auto-seach item : 'thi' == "+ trie.autocomplete("thi").toString() );
	}
	
}
