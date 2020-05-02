package eltech.DM2020.BigNum;

import java.util.*;

/**
* Класс, который позволяет манипулировать с полиномами с рациональными коэффициентами
* @version 0.01
*/
public class BigPolinom
{
	private ArrayList<BigMonom> factors = new ArrayList<BigMonom>();

	private BigPolinom() {}

	/**
	* Конструктор, с помощью которого можно инициализировать полином
	* Если строка пустая или null, то бросит исключение
	* Может принять строку такого вида:
	* 27x_1^3 + 7x2 + 3
	*
	* @param String src - представление полинома в виде строки
	*
	* @version 1
	* @author 
	*/
	public BigPolinom(int amount, String src)
	{
		int i;
		String[] str;
		if(src == null)
			throw new IllegalArgumentException("Неверный аргумент: строка не может быть не инициализированной\n");
		if(src.equals(""))
			throw new IllegalArgumentException("Неверный аргумент: строка не может быть пустой\n");
		src = src.trim();
		src = src.replace("*", "");
		src = src.replace(")", "");
		src = src.replace("(", "");
		src = src.replace("-", "+-");
		src = src.replace("++", "+");
		src = src.replace("/+", "/");
		src = src.replace("_", "");
		if(src.indexOf("+") == 0)
			src = src.substring(1,src.length());
		str = src.split("[+]");
		for(i = 0; i < str.length; i++)
		{
			factors.add(new BigMonom(amount, str[i].trim()));
		}
		factors = this.sort();
	}

	/**
	* Вывод полинома в виде строки
	* Выводиться должно так:
	* 27x1^3 + 7x2 + 3
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
		for(i = 0; i < factors.size(); i++)
			buffS += factors.get(i).toString()+" + ";
		buffS = buffS.replace("+ -", "- ");
		return buffS.substring(0, buffS.length() - 3);
	}

	/**
	* Проверяет является ли многочлен нулём
	*
    * @return boolean - true, если числитель многочлен имеет степень 0 и коэффициент при нулевой степени равен нулю; иначе false
	*
	* @version 1
	* @author
	*/
	public boolean isZero()
	{
        return this.factors.get(factors.size()-1).isZero();
	}

    /**
    * Сравнение двух полиномов
    *
    * @param BigPolinom other - второй полином для сравнения с исходным
    * @return int - 0 если степени полиномов равны, -1 если степень исходного полинома меньше other, иначе 1
    *
    * @version 1
    * @author
    */
    public int compareTo(BigPolinom other)
    {
		return this.factors.get(0).compareTo( other.factors.get(0) );
    }

	/**
    * Клонирование объекта
	*
    * @return копию BigPolinom
    *
    * @version 1
    * @author
    */
	@Override
	public BigPolinom clone()
	{
		int i,n;
		BigPolinom result = new BigPolinom();
		n = this.factors.size();
		for(i = 0; i < n; i++)
			result.factors.add(this.factors.get(i).clone());
		return result;
	}

	/**
    * Сложение полиномов
	*
	* @param BigPolinom other - второй полином, который прибавляется
	*
    * @return BigPolinom result - результат сложения
    *
    * @version 1
    * @author 
    */
	public BigPolinom add(BigPolinom other)
	{
		BigPolinom result = new BigPolinom();
        return result;
	}

	/**
    * Вычитания полиномов
	*
	* @param BigPolinom other - второй полином, который вычитания
	*
    * @return BigPolinom result - результат вычитания
    *
    * @version 1
    * @author 
    */
	public BigPolinom subtract(BigPolinom other)
	{
        BigPolinom result = new BigPolinom();
        return result;
	}

	/**
    * Умножение полиномов
	*
	* @param BigPolinom other - второй полином, на который умножается исходный
	*
    * @return BigPolinom result - результат вычитания
    *
    * @version 1
    * @author 
    */
	public BigPolinom multiply(BigPolinom other)
	{
        BigPolinom result = new BigPolinom();
        return result;
	}
	
	/**
    * Проверка, есть ли моном в полиноме
	*
	* @param BigMonom other - моном, который мы ищем
	*
    * @return true - если есть, иначе false
    *
    * @version 1
    * @author 
    */
	public boolean hasMonom(BigMonom other)
	{
		int i;
		if(this.isZero() || other.isZero())
			return false;
		for(i = 0; i < this.factors.size(); i++)
			if(this.factors.get(i).getPowers().equals(other.getPowers()))
				return true;
		return false;
	}
	
	/**
    * Сортировка полинома
	*
    * @return Отсортированный полином
    *
    * @version 1
    * @author 
    */
	private ArrayList<BigMonom> sort()
	{
		int i;
		BigPolinom buffThis = this.clone();
		BigPolinom result = new BigPolinom();
		BigMonom buffMonom;
		while(buffThis.factors.size() > 0)
		{
			buffMonom = buffThis.factors.get(0);
			for(i = 1; i < buffThis.factors.size(); i++)
			{
				//System.out.println( buffMonom.compareTo( buffThis.factors.get(i) ));
				if(buffMonom.compareTo( buffThis.factors.get(i) ) < 0)
					buffMonom = buffThis.factors.get(i);
			}
			result.factors.add(buffMonom);
			buffThis.factors.remove(buffThis.factors.indexOf(buffMonom));
		}
		return result.factors;
	}
	
	public ArrayList<BigMonom> getFactors()
	{
		return factors;
	}
}
