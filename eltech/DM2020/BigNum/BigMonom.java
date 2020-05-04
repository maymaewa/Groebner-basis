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
	public BigMonom(int amount, String src) throws IllegalArgumentException
	{
		int i, power, index;
		String[] str;
		for(i = 0; i < amount; i++)
			powers.add(null);
		if(src.indexOf("x") == -1)
		{
			if(src.trim().equals("") || src.trim().equals("-"))
				src += "1";
			coef = new BigQ(src);
		}
		else
		{
			str = src.split("x");
			if(str[0].trim().equals("") || str[0].trim().equals("-"))
				str[0] += "1";
			coef = new BigQ(str[0]);
			for(i = 1; i < str.length; i++)
			{
				if(str[i].indexOf("^") != -1)
				{
					index = Integer.parseInt( str[i].substring(0,str[i].indexOf("^")) );
					if (index > amount)
						throw new IllegalArgumentException("Встречен индекс, превышающий кол-во неизвестных\n");
					power = Integer.parseInt( str[i].substring(str[i].indexOf("^")+1, str[i].length()) );
					powers.set(index-1, power);
				}
				else
				{
					index = Integer.parseInt( str[i] );
					if (index > amount)
						throw new IllegalArgumentException("Встречен индекс, превышающий кол-во неизвестных\n");
					powers.set(index-1, 1);
				}
			}
		}
		
		for(i = 0; i < powers.size(); i++)
			if(powers.get(i) == null)
				powers.set(i, 0);
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
			if(powers.get(i) != 0) 
				if(powers.get(i) != 1) 
					buffS += "*x"+(i+1)+"^"+powers.get(i);
				else
					buffS += "*x"+(i+1);
		return buffS;
	}
	
	/**
	* Проверка монома на нуль
	*
    * @return 1, если моном - нуль, иначе 0
	*
	* @version 1
	* @author
	*/
	public boolean isZero()
	{
		return this.coef.isZero() ? true : false;
	}
	
	/**
    * Клонирование объекта
	*
    * @return копию BigMonom
    *
    * @version 1
    * @author
    */
	@Override
	public BigMonom clone()
	{
		BigMonom result = new BigMonom();
		result.coef = this.coef.clone();
		result.powers = new ArrayList<Integer>(this.powers);
		return result;
	}
	
	/**
	* Сравнивание мономов
	*
    * @return 1 - сравниваемый моном имеет большую степень, 0 - мономы одной степени, -1 - сравниваемый моном имеет степень меньше
	*
	* @version 1
	* @author
	*/
	public int compareTo(BigMonom other)
	{
		int i;
		for(i = 0; i < this.powers.size(); i++)
			if(this.powers.get(i) > other.powers.get(i))
				return 1;
			else if(this.powers.get(i) < other.powers.get(i))
				return -1;
		return 0;
	}
	
	/**
    * Умножение мономов
	*
	* @param BigMonom other - второй моном, на который умножается исходный
	*
    * @return BigMonom result - результат вычитания
    *
    * @version 1
    * @author 
    */
	public BigMonom multiply(BigMonom other)
	{
        int i;
        BigMonom result = this.clone();
		BigMonom buffOther;
		buffOther = other.clone();
		result.coef = result.coef.multiply(buffOther.coef);
		for(i = 0; i < result.powers.size(); i++)
			result.powers.set(i, result.powers.get(i) + buffOther.powers.get(i) );
        return result;
	}
	
	/*public String getHighPower()
	{
		int i;
		String result = "0";
		for(i = 0; i < this.powers.size() && result.equals("0"); i++)
		{
			if(this.powers.get(i) != 0)
				result = "x"+(i+1)+"^"+this.powers.get(i).toString();
		}
		return result;
	}*/
	
	public ArrayList<Integer> getPowers()
	{
		return powers;
	}
	
	public void setPowers(ArrayList<Integer> newPowers)
	{
		powers = newPowers;
	}
	
	public BigQ getCoef()
	{
		return coef;
	}
	
	public void setCoef(BigQ num)
	{
		coef = num;
	}
}