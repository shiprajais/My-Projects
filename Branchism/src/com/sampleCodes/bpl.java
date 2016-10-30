package com.sampleCodes;

import java.io.*;
import java.util.*;
class bpl
{/*Status taken
3:Strongly Taken
2:Weakly Taken
1:Weakly Not Taken
0:Strongly Not Taken*/
                static String btb[][];
public static void main1(String args[])throws IOException
{
                Scanner input=new Scanner(System.in);
                btb=new String[5][4];
                BufferedReader br=new BufferedReader(new FileReader("btb.txt"));
                String s;
                int index=0;
                while((s=br.readLine())!=null)
                {
                                String dummy[]=s.split(" ");
                                for(int i=0;i<3;i++)
                                {
                                                btb[index][i]=dummy[i];
                                }
                                btb[index][3]=(new Integer(put_value_status(dummy[3]))).toString();
                }
                                displaybtb();
                System.out.println("Enter instruction in following format: \n<source><instruction><destination>");
                String in=input.nextLine();
                boolean hit=false;
                String indummy[]=in.split(" ");
                for(int i=0;i<indummy.length;i++)
                {
                                System.out.println(indummy[i]+" ");
                }
                for(int i=0;i<4;i++)
{
                                if(indummy[0].equals(btb[i][0]))
                                {
                                                if(indummy[1].equals(btb[i][1]))
                                                {
                                                                if(indummy[2].equals(btb[i][2]))

{
hit=true;
System.out.println("*********** BTB HIT**********");
System.out.println("status change");
if(btb[i][3].equals("3"))
System.out.println("ST---->ST");
else{
int x=Integer.parseInt(btb[i][3]);
System.out.println("status change from"+get_value_status(btb[i][3]));
x++;
btb[i][3]=""+x;
System.out.println("to"+get_value_status(btb[i][3]));
displaybtb();
}}}}
else
{
hit=false;
btb=returnMiss(btb,in);
displaybtb();
}}
}static String[][] returnMiss(String a[][],String b)
{
		int len=a[0].length;
		String dummy[][]=new String[a.length+1][len];
		int count=0;
		for(int i=0;i<a.length;i++)
		{
				if(a[i][3].equals("0"))
				{
				continue;
				}
				for(int j=0;j<len-1;j++)
				{
				dummy[count][j]=a[i][j];
				}
				int x=Integer.parseInt(a[i][3]);
				x--;
				dummy[count][3]=""+x;
				count++;
		}
		String tp[]=b.split("");
		for(int i=0;i<3;i++)
		{
			dummy[count][i]=tp[i];
		}
		dummy[count][3]="3";
	return dummy;
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
static String get_value_status(String s)
{
                if(s.equals("0"))
                {
                                return "SNT";
                }
                if(s.equals("1"))
                {
                                return "WNT";
                }
                if(s.equals("2"))
                {
                                return "WT";
                }
                if(s.equals("3"))               
                {
                                return "ST";
                }
                return null;        
}
static void displaybtb()
{
                System.out.println("\n---------------------------BRANCH TEST BUFFER-------------------");
                System.out.println("Source Instruction Destinatiion Status");
                System.out.println("---------------------------------------");
                for(int i=0;i<5;i++)
                {
                                for(int j=0;j<3;j++)
                                {
                                                System.out.println(btb[i][j]+"\t");
                                }
                                System.out.println("\t"+get_value_status(btb[i][3]));
                System.out.println();
}
System.out.println("-----------------------------------------------------------------");
}
}

/*Output:
C:\Java\jdk\bin>javac bpl.java
C:\Java\jdk\bin>java  bpl

---------------------------BRANCH TEST BUFFER-------------------
Source Instruction Destinatiion Status
---------------------------------------
1000    JNC           2000       WT
2000     JMP         3000       WNT
3000     JNZ          1000       ST
4000      JZ             2000       SNT
5000     JNE          2000       ST

-----------------------------------------------------------------
ENTER INSTRUCTION IN FOLLOWING FORMAT:
<SOURCE><INSTRUCTION><DESTINATION>
1244  JNC  3000

---------------------------BRANCH TEST BUFFER-------------------
Source Instruction Destinatiion Status
---------------------------------------
1000    JNC           2000       WT
4000     JMP         3000       SNT
2000     JNZ          1000       ST
5000      JZ             2000       WNT
1244     JNC          3000       ST

-----------------------------------------------------------------

---------------------------BRANCH TEST BUFFER-------------------
Source Instruction Destinatiion Status
---------------------------------------
1000    JNC           2000       WT
2000     JMP         3000       WNT
3000     JNZ          1000       ST
4000      JZ             2000       SNT
5000     JNE          2000       ST

-----------------------------------------------------------------
ENTER INSTRUCTION IN FOLLOWING FORMAT:
<SOURCE><INSTRUCTION><DESTINATION>
1000  JNC  2000

*********** BTB HIT**********
status change from WT to ST

---------------------------BRANCH TEST BUFFER-------------------
Source Instruction Destinatiion Status
---------------------------------------
1000    JNC           2000       WT
2000     JNE          3000       WNT
3000     JNZ          1000       ST
4000      JZ             2000       SNT
5000     JZ              2000       ST

-----------------------------------------------------------------
ENTER INSTRUCTION IN FOLLOWING FORMAT:
<SOURCE><INSTRUCTION><DESTINATION>
1244  JNC  3000

*/