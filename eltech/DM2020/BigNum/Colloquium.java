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
		
		basis.add( new BigPolinom(2, "x2^2-1"));
		basis.add( new BigPolinom(2, "x1x2-1"));
		
		/*basis.add( new BigPolinom(3, "x1x2x3-x1x2+x1-x3"));
		basis.add( new BigPolinom(3, "x1x2-x2^2+x3"));
		basis.add( new BigPolinom(3, "x2^2-x3"));*/
		
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
			System.out.println(basis.get(i).reduce2(basis));
			basis.set(i, basis.get(i).reduce2(basis));
		}
		for(i = 0; i < basis.size()-1; i++)
		{
			if(basis.get(i).equals2(basis.get(i+1)))
			{
				basis.remove(i);
				i--;
			}
		}
		
		/*for(i = 0; i < basis.size(); i++)				//Упрощаем базисы2
		{
			for(j = i+1; j < basis.size(); j++)
			{
				f = basis.get(i).sPolynom(basis.get(j)).reduce(basis);
				//System.out.println(i + " " +  f);
				if(f)
				{
					i = 0;
					j = 0;
				}
			}
		}*/
		
		//System.out.println("S polynom(reduce) от 1 и 2: " + value.get(0).sPolynom( value.get(1) ).reduce(basis));
		//System.out.println("S polynom(reduce) от 2 и 3: " + value.get(1).sPolynom( value.get(2) ).reduce(basis));
		
		System.out.println("Размер базиса: " + basis.size());
		
		for(i = 0; i < basis.size(); i++)
			System.out.println(basis.get(i));
	}
}