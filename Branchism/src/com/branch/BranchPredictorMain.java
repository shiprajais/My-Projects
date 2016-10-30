package com.branch;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.jws.Oneway;

public class BranchPredictorMain {

	static List<String> bht = new ArrayList<String>();
	static ArrayList<ArrayList<String>> bhts = new ArrayList<ArrayList<String>>();
	static int hit = 0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedReader br = null;
		try {
			br=new BufferedReader(new FileReader("gcc-10K.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String s;
       
        
        int bitPredictor =1;int counter = 0;
        int m=4;
        int len=6;
        
        
        char[] array = new char[m];
        Arrays.fill(array, '0');
        String gbh = new String(array);
        
        if(m!=0){
       // ArrayList<ArrayList<String>> bhts = new ArrayList<ArrayList<String>>();
        for(int i=0;i<(int) Math.pow(2, m);i++){
	        ArrayList<String> bht = new ArrayList<String>();
	        bhts.add(bht);
        }}
        
        
        try {
			while((s=br.readLine())!=null)
			{
				counter++;
			                      String dummy[]=s.split(" ");
			                      String output = new BigInteger(dummy[0], 16).toString(2);
			                      
			                      String reverse = new StringBuffer(output).reverse().toString();
			                      
			                      String revSubstring = reverse.substring(0,len);
			                      String address = new StringBuffer(revSubstring).reverse().toString(); 
			                      
			                      String decision = getdecision(dummy[1]); 
			                   Instruction inst = new Instruction(address, decision);  
			        if(m==0 && bitPredictor == 1){
			        	oneBitPredictor(inst);	
			        }
			        else if(bitPredictor == 2 && m == 0){
			        	twoBitPredictor(inst);
			        }
			        else if(bitPredictor == 1 && m != 0){
			        	oneBitMPredictor(inst,gbh);
			        }
			}
			System.out.println(bitPredictor+" bit predictor hit is "+hit);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void oneBitPredictor(Instruction inst){
		 Boolean isPresent = false;
		 
			for(int i=0;i<bht.size();i++){
				String[] bhtRow = ((String) bht.get(i)).split(" ");
				int tempBht = Integer.parseInt(bhtRow[1]);
     		    int tempInst = Integer.parseInt(inst.decision);
            	   if(bhtRow[0] != null && bhtRow[0].equals(inst.address))
            	   {
            		   isPresent = true;
            		   
            		   if(tempBht == tempInst)
            		   {
            			   hit++;
            		   }
            		   //tempBht=3 and tempInst=0
            		   else
            		   {
            			   tempBht = tempInst;
            			bht.set(i, inst.address+" "+tempBht);
            		   }
            	   
                    	  
            		   break;
               }
			}
			if(isPresent == false)
			{
				if(inst.decision == "0"){
					hit++;
				}
				addinbht(inst);
			}
			
			
		
	}

	public static void twoBitPredictor(Instruction inst){
		Boolean isPresent = false;
		for(int i=0;i<bht.size();i++){
			String[] bhtRow = ((String) bht.get(i)).split(" ");
			int tempBht = Integer.parseInt(bhtRow[1]);
 		    int tempInst = Integer.parseInt(inst.decision);
        	   if(bhtRow[0] != null && bhtRow[0].equals(inst.address))
        	   {
        		   isPresent = true;
        		   
        		   switch (tempBht){
           		   case 3 :
           			   		if(tempInst==0){
           			   		  tempBht = 2;
           			   		}
           			   		else if (tempInst == 3){
           			   			tempBht = 3;
           			   			hit++;
           			   		}
           			   	    bht.set(i, inst.address+" "+tempBht);
           			   		break;
           		   case 2 :
                   			if(tempInst==0){
             			   		  tempBht = 0;
             			   		}
             			   		else if (tempInst == 3){
             			   			tempBht = 3;
             			   			hit++;
             			   		}
             			   	    bht.set(i, inst.address+" "+tempBht);
           			   		
           			        break;
           		   case 1 :
           			   
                   			if(tempInst==0){
           			   		  tempBht = 0;
           			   		  hit++;
           			   		}
           			   		else if (tempInst == 3){
           			   			tempBht = 3;
           			   			
           			   		}
           			   	    bht.set(i, inst.address+" "+tempBht);
           			   		break;
           		   case 0 :
                   			if(tempInst==0){
               			   		  tempBht = 0;
               			   		  hit++;
               			   		}
               			   		else if (tempInst == 3){
               			   			tempBht = 1;
               			   			
               			   		}
               			   	    bht.set(i, inst.address+" "+tempBht);
   			   		        break;
           		   }
           }
		}
		if(isPresent == false)
			{
				if(inst.decision == "0"){
				hit++;
				addinbht(inst);
			}
				else if (inst.decision == "3"){
					inst.decision="1";
					addinbht(inst);
				}
			}
		
	}

	public static void oneBitMPredictor(Instruction inst, String gbh){
		
	}
	private static String getdecision(String string) {
		// TODO Auto-generated method stub
		if(string.equals("T"))
		{
			return "3";
		}
		if(string.equals("N"))
		{
			return "0";
		}
		return null;
		
	}

	private static void addinbht(Instruction inst) {
		// TODO Auto-generated method stub
		bht.add(inst.address+" "+inst.decision);
		
			
		
	}

	static int put_value_status(String s)
	{
	                if(s.equals("ST"))
	                {
	                                return 3;
	                }
	                if(s.equals("WT"))
	                {
	                                return 2;
	                }
	                if(s.equals("WNT"))
	                {
	                                return 1;
	                }
	                if(s.equals("SNT"))         
	                {
	                                return 0;
	                }
	                return -1;
	}
	
	static String put_value_0_1(String s)
	{
	                if(s.equals("3"))
	                {
	                                return "1";
	                }
	                if(s.equals("0"))
	                {
	                                return "0";
	                }
	                return "";
	}
}
