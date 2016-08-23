package weidu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;
/* Firstly, reverse the direction of every edge.
 * 
 * We use stack to store every vertices by finishing time.
 * Then, DFS to push every vertices into Stack.
 * Thirdly, reverse back to normal direction of every edge.
 * Then, pop vertex from stack to DFS and count the size of SCC at the same time.
 */
public class SCC {
	static int count;
	static final int length = 875714;
	static Stack<Vertex> stack = new Stack<>();
	public static void main(String[] args) throws Exception {
		String filename = "C:\\Users\\adimv\\Desktop\\StanfordAlgorithm\\SCC.txt";
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String data = "";
		ArrayList<String> array = new ArrayList<>();
		while((data = br.readLine())!=null){
			String[] temp = data.split(" ");
			for(String s: temp){
				array.add(s);
			}
		}
		br.close();
		Vertex[] vertex = new Vertex[length+1];
		for(int j = 1 ; j <length+1;j++){
			vertex[j] = new Vertex(""+j);
		}
		for(int i = 1 ; i < array.size(); i= i+2){
			edge(vertex,array.get(i),array.get(i-1));
		}
		int[] layer = new int[1];
		layer[0] = 1;
		HashMap<Integer,Vertex> map = new HashMap<Integer,Vertex>();
		ArrayList<Integer> list = new ArrayList<>();
		stack = finishingTimes(vertex);
		for(int k = 1 ; k < length+1;k++){
			vertex[k].reverse();
			vertex[k].flag = 0;
		}
		while(!stack.isEmpty()){
			Vertex v = stack.pop();
			if(v.flag==0){
				count=1;
				depthfirstSearch(v);
				list.add(count);
			}
		}
		Collections.sort(list);
		System.out.println(list.toString());
	}
	public static void edge(Vertex[] vertex, String s1, String s2){
		vertex[Integer.parseInt(s1)].outgoing.add(vertex[Integer.parseInt(s2)]);
		vertex[Integer.parseInt(s2)].incoming.add(vertex[Integer.parseInt(s1)]);
	}
	
	public static Stack<Vertex> finishingTimes(Vertex[] vertex){
		for(int k = 1; k<length+1;k++){
			if(vertex[k].flag==0){
				depthfirstSearch(vertex[k]);
			}
		}
		return stack;
	}
	public static void depthfirstSearch(Vertex v){
		v.flag=1;
		for(Vertex vertex:v.outgoing){
			if(vertex.flag==0){
				count++;
				depthfirstSearch(vertex);
			}
		}
		stack.push(v);
	}

static class Vertex {
		String name;
		ArrayList<Vertex> incoming = new ArrayList<>();
		ArrayList<Vertex> outgoing = new ArrayList<>();
		int flag;
		int finishTime;
		
		Vertex(String s1){
			this.name = s1;
			this.flag = 0;
		}
		
		public void reverse(){
			ArrayList<Vertex> temp = new ArrayList<Vertex>();
			for(Vertex e:this.incoming){
				temp.add(e);
			}
			incoming.clear();
			for(Vertex e:this.outgoing){
				incoming.add(e);
			}
			outgoing.clear();
			for(Vertex e:temp){
				outgoing.add(e);
			}
		}
}	

}

