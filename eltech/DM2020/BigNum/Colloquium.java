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
	private static ArrayList<BigPolinom> value = new ArrayList<BigPolinom>();
	private static ArrayList<BigPolinom> basis = new ArrayList<BigPolinom>();
	
	
	public static void start()
	{
		/*
		
		Полиномы для примера:
		x1x2-x3^2-x3
		x1^2-x1-x2x3
		1x3-x2^2-x2
		
		*/
		
		Scanner num = new Scanner(System.in);
		System.out.println("\nВведите кол-во незвестных");
		String buffS;
		int amount = num.nextInt();
		int i, j, flag;
		boolean f = true;
		
		/*basis.add( new BigPolinom(3, "x1x2-x3^2-x3"));
		basis.add( new BigPolinom(3, "x1^2-x1-x2x3"));
		basis.add( new BigPolinom(3, "x1x3-x2^2-x2"));*/
		
		/*basis.add( new BigPolinom(3, "x1x2-x3^2-x3"));
		basis.add( new BigPolinom(3, "x1^2+x1-x2x3"));
		basis.add( new BigPolinom(3, "x1x3-x2^2-x2"));*/
		
		//value.add( new BigPolinom(3, "0"));
		
		//basis.add( new BigPolinom(2, "x2^2-1"));
		//basis.add( new BigPolinom(2, "x1x2-1"));
		
		basis.add( new BigPolinom(3, "3x1^3x2x3-x1x2+x1-x3"));
		basis.add( new BigPolinom(3, "x1x2-x2^2+x3"));
		basis.add( new BigPolinom(3, "5x2^3-x3"));
		
		//basis.add( new BigPolinom(3, "1*x2^2*x3^4 + 17/75*x2^2*x3^3 + 4/15*x2^2*x3 - 5/3*x2*x3^7 - 13/15*x2*x3^6 - 4/375*x2*x3^5 + 26/45*x2*x3^4 - 26/1125*x2*x3^3 - 2/9*x2*x3^2 - 8/225*x2*x3 + 4/3*x3^7 + 13/75*x3^6 - 3122/5625*x3^5 - 49/225*x3^4 + 1/27*x3^2 - 1/675*x3"));
		//basis.add( new BigPolinom(3, "1*x2^2*x3^3 + 3000/5047*x2^2*x3^2 - 680/5047*x2^2*x3 - 18750/5047*x2*x3^8 - 5500/5047*x2*x3^7 - 7285/5047*x2*x3^6 + 40136/25235*x2*x3^5 - 5200/15141*x2*x3^4 - 44116/75705*x2*x3^3 + 500/15141*x2*x3^2 - 3478/15141*x2*x3 + 15000/5047*x3^8 - 1450/5047*x3^7 + 814/5047*x3^6 - 83227/378525*x3^5 - 7709/15141*x3^4 + 1250/15141*x3^3 - 1000/45423*x3^2 + 1909/45423*x3"));
		
		for(i = 0; i < basis.size(); i++)
			value.add(basis.get(i));
		//System.out.println(value.get(0).reduce(basis));
		
		for(i = 0; i < value.size(); i++)
			for(j = i+1; j < value.size(); j++)
			{
				//System.out.println(i + ", " + j);
				value.get(i).sPolynom( value.get(j) ).reduce(basis);
			}
			
		for(i = 0; i < basis.size(); i++)				//Упрощаем базисы
		{
			basis.set(i, basis.get(i).reduce2(basis));
			//System.out.println("TEST " + basis.get(i).isZero() + " i = " + i);
			if(basis.get(i).isZero())
			{
				basis.remove(i);
				i--;
			}
		}
		
		for(i = 0; i < basis.size()-1; i++)
		{
			for(j = 0; j < basis.size(); j++)
			{
				if(i != j)
					if(basis.get(i).equals2(basis.get(j)))
					{
						basis.remove(i);
						//i--;
					}
			}
		}
		
		//System.out.println("S polynom(reduce) от 1 и 2: " + value.get(0).sPolynom( value.get(1) ).reduce(basis));
		//System.out.println("S polynom(reduce) от 2 и 3: " + value.get(1).sPolynom( value.get(2) ).reduce(basis));
		
		System.out.println("Размер базиса: " + basis.size());
		
		for(i = 0; i < basis.size(); i++)
			System.out.println(basis.get(i) + "\n");
	}
}