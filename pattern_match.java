package pattern_match;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

public class Main {
	// 入力画像（背景）とパターン画像との精査
	private static Boolean isMatch (int[][] base, int[] pattern, int pattern_size, int y, int x) {
		int m = 0;
		for (int i=y;i<pattern_size+y;i++) {
        	for (int j=x;j<pattern_size+x;j++) {
        		if (base[i][j] != pattern[m]) {
        			return false;
        		}
        		m = m + 1;
        	}
		}
    	return true;
    }
	
	public static void main(String[] args) throws Exception {
		BufferedReader strStdIn;
		int bg_size;
		String bg_row[];
		int trim_size;
		String trim_row[];
		
		// 入力画像（背景）の標準入力
		strStdIn = new BufferedReader(new InputStreamReader(System.in));
	    bg_size = Integer.parseInt(strStdIn.readLine());
	    bg_row = new String[bg_size];
	    IntStream.range(0, bg_size).forEach(i -> {
	    	try{
	    		bg_row[i] = strStdIn.readLine();
	    	}catch(IOException e){
	    		e.printStackTrace();
	    		System.exit(1);
	    	}
	    });
	        
	    // パターン画像の標準入力
	    trim_size = Integer.parseInt(strStdIn.readLine());
	    trim_row = new String[trim_size];
	    IntStream.range(0, trim_size).forEach(i -> {
	    	try{
	    		trim_row[i] = strStdIn.readLine();
	    	}catch(IOException e){
	    		e.printStackTrace();
	    		System.exit(1);
	    	}
	    });
		
	    // 背景画像はint型で二次元配列に
	    String bg_temp = String.join(" ", bg_row);
	    String[] bg_array = bg_temp.split(" ");
	    int bg_2array[][];
	    bg_2array = new int[bg_size][bg_size];
	    //System.out.print("bg: ");
	    int k = 0;
	    for (int i=0;i<bg_size;i++) {
	    	for (int j=0;j<bg_size;j++) {
	    		bg_2array[i][j] = Integer.parseInt(bg_array[k]);
	    		k = k+1;
	    		//System.out.print(bg_2array[i][j] +" ");
	    	}
	        //System.out.print(System.getProperty("line.separator"));
	    }
	    // パターン画像はint型で一次元配列に
	    String trim_temp = String.join(" ", trim_row);
	    String[] trim_temp2 = trim_temp.split(" ");
	    int trim_array[];
	    trim_array = new int[trim_temp2.length];
	    //System.out.print("trim: ");
	    IntStream.range(0, trim_temp2.length).forEach(i -> {
	    	trim_array[i] = Integer.parseInt(trim_temp2[i]);
	    	//System.out.print(trim_array[i] +" ");
	    });
	    
	    //isMatch (int[][] base, int[] pattern, int pattern_size, int x, int y)
	    // 照合開始位置をstartareaに限定して検索 
	    int startarea = bg_size - trim_size + 1;
	    int answi =10000;
	    int answj =10000;
	    for (int i=0;i<startarea;i++) {
	    	for (int j=0;j<startarea;j++) {
	    		if (bg_2array[i][j] == trim_array[0]) {
	    			// パターン画像の一文字目と合致したら精査
	    			if (isMatch(bg_2array, trim_array, trim_size, i, j) == true) {
	    				//System.out.println("true");
	    				answi = i;
	    				answj = j;
	    			}
	    		}
	    	}
	    }
	    System.out.print(answi+" "+answj);
		//System.out.println("\nPROGRAM END");
    }
}
