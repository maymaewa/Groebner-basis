package eltech.DM2020.BigNum;

import java.util.*;
import java.math.*;
import java.lang.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;

/**
* Класс, который содержит интерфейс
* @version 0.01
* @author Семенов Алексей, Сычев Александр
* Главный SQA - Семенов Алексей
*/
public class Colloquium
{
	private static Basis base = new Basis();
	
	public static void start()
	{
		/*
		
		1*x1^6*x3 + 1*x1 - 1/3*x2^2 - 2/3*x3

		1*x1*x2 - 1/3*x2^2 + 1/3*x3

		1*x2^3 - 1*x3
		
		
		x1^2-x1-x2x3
		x1x3-x2^2-x2
		*/
		/*BigPolinom test = new BigPolinom(3, "0");
		BigPolinom test2 = new BigPolinom(3, "3511808/6128487*x2^15-1617280/2042829x2^14-6545120/6428487x2^13+306102280/55156383x2^12-175994720/55156383x2^11-1182017594/165469149x2^10+21269039420/1489222341x2^9");
		System.out.println(test.add(test2));*/
		
		
		System.out.println("\nВведите кол-во незвестных");
		String buffS;
		int amount,i;
		
		do
		{
			Scanner num = new Scanner(System.in);
			amount = num.nextInt();
			if(amount > 0)
				base.setMaxPower(amount);
		} while(amount < 1);
		
		do
		{
			Scanner in = new Scanner(System.in);
			System.out.print("\nВведите полином: ");
			buffS = in.nextLine();
			if(!buffS.equals(""))
			{
				if(amount < 4)
				{
					buffS = buffS.replace("x", "x1");
					buffS = buffS.replace("y", "x2");
					buffS = buffS.replace("z", "x3");
				}
				base.addBasis(buffS);
			}
		} while(!buffS.equals(""));
		
		base.doActions();
		base.decision();
		System.out.println("Решение:");
		System.out.print(base.decision);
	}
}