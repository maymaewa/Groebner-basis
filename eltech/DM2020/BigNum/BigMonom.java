package eltech.DM2020.BigNum;

import java.util.*;

/**
* Класс, который позволяет манипулировать с мономами с рациональными коэффициентами
* 
* @version 0.01
*/
public class BigMonom
{
	private BigQ coef;												//коэффициент
	private ArrayList<Integer> powers = new ArrayList<Integer>();	//степени x_i
	
	private BigMonom() {}
	
	/**
	* Конструктор, с помощью которого можно инициализировать моном
	* Если строка src пустая или null, то бросит исключение
	*
	* -27/7 | x | 1^3 | x | 2
	*
	* -27/7x1^3x2
	*
	* -57/7
	*
	* @param String src - представление полинома в виде строки
	*
	* @version 1
	* @author 
	*/
	public BigMonom(int amount, String src)
	{
		int i, power, index;
		String[] str;
		for(i = 0; i < amount; i++)
			powers.add(null);
		if(src.indexOf("x") == -1)
			coef = new BigQ(src);
		else
		{
			str = src.split("x");
			coef = new BigQ(str[0]);
			for(i = 1; i < str.length; i++)
			{
				if(str[i].indexOf("^") != -1)
				{
					index = Integer.parseInt( str[i].substring(0,str[i].indexOf("^")) );
					power = Integer.parseInt( str[i].substring(str[i].indexOf("^")+1, str[i].length()) );
					powers.set(index-1, power);
				}
				else
				{
					index = Integer.parseInt( str[i] );
					powers.set(index-1, 1);
				}
			}
		}
		
		for(i = 0; i < powers.size(); i++)
				if(powers.get(i) == null)
					powers.set(i, 0);
				
				
		/*System.out.println("coef = " + coef);
		for(i = 0; i < powers.size(); i++ )
			System.out.println("xi = " + powers.get(i));*/
	}
	
	/**
	* Вывод монома в виде строки
	* Выводиться должно так:
	* (+-)27x1^3
	*
    * @return String - представление полинома в виде строки
	*
	* @version 1
	* @author
	*/
	@Override
	public String toString()
	{
		int i;
		String buffS = "";
		buffS += coef.toString();
		for(i = 0; i < powers.size(); i++)
			if(powers.get(i) != 0) buffS += "x"+(i+1)+"^"+powers.get(i);
		return buffS;
	}
}