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
		/*BigMonom one = new BigMonom(2, "4x1^2x2");
		BigMonom two = new BigMonom(2, "x1");
		System.out.println(two.multiply(two.getMultiplier(one)));*/
		
		Scanner num = new Scanner(System.in);
		System.out.println("\nВведите кол-во незвестных");
		String buffS;
		int amount = num.nextInt();
		int i, j, flag;
		boolean f = true;
		basis.add( new BigPolinom(3, "x1x2-x3^2-x3"));
		basis.add( new BigPolinom(3, "x1^2-x1-x2x3"));
		basis.add( new BigPolinom(3, "1x3-x2^2-x2"));
		value.add( new BigPolinom(3, "x1x2-x3^2-x3"));
		value.add( new BigPolinom(3, "x1^2-x1-x2x3"));
		value.add( new BigPolinom(3, "1x3-x2^2-x2"));
		
		for(i = 0; i < 3; i++)
			System.out.println(value.get(i));
		
		/*for(i = 0; i < 3; i++)
		{
			Scanner in = new Scanner(System.in);
			System.out.println("\nВведите полином");
			buffS = in.nextLine();
			value.add( new BigPolinom(amount, buffS.trim()) );
			basis.add( new BigPolinom(amount, buffS.trim()) );
			System.out.println("Введенный полином *-1: " + value.get(i).multiplyByMinusOne());
			System.out.println("Полином = 0? " + value.get(i).isZero() + "\n\n");
		}*/
		
		/*System.out.println("Сумма: " + value.get(0).add( value.get(1) ));
		System.out.println("Разница: " + value.get(0).subtract( value.get(1) ));
		System.out.println("Произведение: " + value.get(0).multiply( value.get(1) ));
		System.out.println("Частное: " + value.get(0).divide( value.get(1) ));
		System.out.println("Остаток: " + value.get(0).mod( value.get(1) ));*/
		System.out.println("S polynom(reduce) от 1 и 2: " + value.get(0).sPolynom( value.get(1) ).reduce(basis));
		for(i = 0; i < 3; i++)
			System.out.println(value.get(i));
		
		//System.out.println("S polynom(reduce) от 2 и 3: " + value.get(1).sPolynom( value.get(2) ).reduce(basis));
		//for(i = 0; i < 2; i++)
		/*{
			System.out.println(value.get(0).sPolynom( value.get(1) ).getHighMonom().isDivided(value.get(i).getHighMonom()));
		}*/
		//System.out.println("НОД: " + value.get(0).gcd( value.get(1) ));
		//buffThis.getHighMonom().getMultiplier(buffOther.getHighMonom());
		//System.out.println("Остаток: " + value.get(0).mod( value.get(1) ));
	}
}