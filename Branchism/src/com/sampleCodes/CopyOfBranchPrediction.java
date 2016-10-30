package com.sampleCodes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;



public class CopyOfBranchPrediction {
	
	
	static List bht = new ArrayList<String>();
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
       
        BufferedReader br=new BufferedReader(new FileReader("test1.txt"));
        String s;
        int index=0;
        int hit=0;
        while((s=br.readLine())!=null)
        {
                        String dummy[]=s.split(" ");
                        
                              /*System.out.print(dummy[0]+"  "); 
                              System.out.println(dummy[1]+"  ");*/
                              String output = new BigInteger(dummy[0], 16).toString(2);
                              
                              String address = output.substring(0,7);
                              String decision = getdecision(dummy[1]); 
                           Instruction inst = new Instruction(address, decision);   
                        
                           Boolean isPresent = false;
						for(int i=0;i<bht.size();i++){
								/*if(bht[0][0] == null){
									addinbht(inst);
									break;
								}*/
							String[] bhtRow = ((String) bht.get(i)).split(" ");
                        	   if(bhtRow[0] != null && bhtRow[0].equals(inst.address))
                        	   {
                        		   isPresent = true;
                        		   if(bhtRow[1].equals(inst.decision))
                        		   {
                        			   
                        			   if(bhtRow[1].equals("3"))
                        			   {
                        				   hit++;
                        				   break;
                        			   }
                        			   else if(bhtRow[1].equals("2"))
                        			   {
                        				   int x=Integer.parseInt(bhtRow[1]);
                        				   x++;
                        				   bhtRow[1] = ""+x;
                        				   break;
                        			   }
                        			   else if(bhtRow[1].equals("0"))
                        			   {
                        				   hit++;
                        				   System.out.println("NT->NT");
                        				   break;
                        			   }
                        			   else if(bhtRow[1].equals("1"))
                        			   {
                        				   int x=Integer.parseInt(bhtRow[1]);
                        				   x++;
                        				   bhtRow[1] = ""+x;
                        				   break;
                        			   }
                        		   }
                        	   } 
                        	   else
                        	   {
                        		   addinbht(inst);
                        		   break;
                        	   }
                        	   if(bhtRow[0].equals(inst.address) && !(bhtRow[1].equals(inst.decision)))
                        	   {
                        		   bhtRow[1] = inst.decision;
                        		   bht.set(i, inst.address+" "+inst.decision);
                        		   break;
                        	   }
                           }
						if(isPresent == false)
						{
							addinbht(inst);
						}
		
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
