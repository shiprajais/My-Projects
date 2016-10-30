package com.sampleCodes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;



public class BranchPredictionRunning {
	
	
	static List<String> bht = new ArrayList<String>();
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
       
        BufferedReader br=new BufferedReader(new FileReader("gcc-8M.txt"));
        String s;
        int hit=0;
        int bitPredictor =1;int counter = 0;
       if(bitPredictor == 2)
        {
        while((s=br.readLine())!=null)
        {
        	counter++;
                              String dummy[]=s.split(" ");
                              String output = new BigInteger(dummy[0], 16).toString(2);
                             // System.out.println("output is"+output);
                              String reverse = new StringBuffer(output).reverse().toString();
                             
                              String revSubstring = reverse.substring(0,6);
                              String address = new StringBuffer(revSubstring).reverse().toString();
                              //System.out.println("address is "+address);
                              String decision = getdecision(dummy[1]); 
                           Instruction inst = new Instruction(address, decision);   
                        
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
                    				   break;
                    			   }
                        		   else if(tempBht == 2 && tempInst>tempBht)
                        		   {
                        			   
                        				   tempBht++;
                        				   hit++;
                        				   bhtRow[1] = ""+tempBht;
                        				   bht.set(i, inst.address+" "+bhtRow[1]);
                        				   break;
                        			}
                        			else if(tempBht == 1 && tempInst<tempBht)
                        			{
                        				   tempBht--;
                        				   hit++;
                        				   bhtRow[1] = ""+tempBht;
                        				   bht.set(i, inst.address+" "+bhtRow[1]);
                        				   break;
                        			 }
                        	   
		                        	   else
		                        	   {
		                        		   switch (tempInst){
		                        		     case 3 :
		                        		    	 		if(tempBht == 0){
		                        		    	 			tempBht = 1;
		                        		    	 		}
		                        		    	 		else if(tempBht == 1){
		                        		    	 			tempBht = 3;
		                        		    	 		}
		                        		    	 		bht.set(i, inst.address+" "+tempBht);
		                        		    	 		break;
		                        		     case 0 :
		                 		    	 		if(tempBht == 2){
		                 		    	 			tempBht = 0;
		                 		    	 		}
		                 		    	 		else if(tempBht == 3){
		                 		    	 			tempBht = 2;
		                 		    	 		}
		                 		    	 		bht.set(i, inst.address+" "+tempBht);
		                 		    	 		break;
		                        		   }
		                        		   
		                        	   }
                        		   break;
                           }
						}
						if(isPresent == false)
						{
							addinbht(inst);
						}
						
		
	    }
        System.out.println("hit for 2 bit "+hit+" counter is "+counter);
       }
       else if(bitPredictor == 1) {
    	   hit=0;
    	   while((s=br.readLine())!=null)
           {
                                 String dummy[]=s.split(" ");
                                 String output = new BigInteger(dummy[0], 16).toString(2);
                                 
                                 String address = output.substring(0,7);
                                 String decision = getdecision(dummy[1]); 
                              Instruction inst = new Instruction(address, decision);   
                           
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
   							addinbht(inst);
   						}
   	    }
    	   System.out.println("1 bit predictor hit is "+hit);
       }
        displaybht();

    

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
	
	private static void displaybht() {
		// TODO Auto-generated method stub
		System.out.println("displaying bht");
		for(int i=0;i<bht.size();i++)
		{
			
				System.out.println(bht.get(i));
			
		}
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
}
