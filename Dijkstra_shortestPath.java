package weidu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
/*
 * 
 * This solution is for undirected graph, in order to find the shortest path from vertex 1.
 * by Wei
 */
public class UndirectedGraph {
	final static int length = 200;
	public static void main(String[] args) throws Exception{
		Vertex[] v = new Vertex[length + 1];
		for(int i = 1 ; i < length +1 ; i ++){
			v[i] = new Vertex(""+i);
		}
		v[1].height = 0;
		String file = "C:\\Users\\adimv\\Desktop\\StanfordAlgorithm\\dijkstraData.txt";
		BufferedReader br = new BufferedReader(new FileReader(file));
		String data = "";
		int i = 1;
		while((data = br.readLine())!=null){
			String[] str = data.split("	");
			for(int j = 1; j < str.length; j++){
				String[] temp = str[j].split(",");
				v[i].path.put(v[Integer.parseInt(temp[0])], Integer.parseInt(temp[1]));
			}
			i++;
		}
		br.close();
		shortestPath(v[1]);
		for(int k = 1; k < length +1 ; k++){
			System.out.println("it takes "+v[k].height+" to get to vertex "+k);
		}
	}
	public static void shortestPath(Vertex v1){
		for(Vertex vertex : v1.path.keySet()){
			if(v1.height+v1.path.get(vertex)<vertex.height){
				vertex.height = v1.height+v1.path.get(vertex);
				if(!vertex.path.isEmpty()){
					shortestPath(vertex);
				}
			}
		}
		
		
	}
}

class Vertex{
	int height = 1000000;
	String name;
	HashMap<Vertex,Integer> path = new HashMap<>();
	Vertex(String name){
		this.name = name;
	}
	
	public void relation(String str1, String str2){
		this.path.put(new Vertex(str1), Integer.parseInt(str2));
	}
	
}
